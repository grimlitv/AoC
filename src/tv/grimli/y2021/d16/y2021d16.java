package tv.grimli.y2021.d16;

import tv.grimli.ReadFromFile;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class y2021d16 {
  private static int versionTotal = 0;
  private static String nextMath = "";

  public static BigInteger run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d16/sample.txt");
    String[] aInput = input.get(0).split("(?!^)");
    StringBuilder bInput = new StringBuilder();
    for (String in : aInput) {
      int convertMe = Integer.parseUnsignedInt(in, 16);
      StringBuilder pushMe = new StringBuilder(Integer.toBinaryString(convertMe));
      while (pushMe.length() < 4) {
        pushMe.insert(0, "0");
      }
      bInput.append(pushMe);
      System.out.println(pushMe);
    }
    String binaryInput = bInput.toString();
    List<BigInteger> packetValues = new ArrayList<>();
    System.out.println(binaryInput);
    int from = 0;
    int to = 3;
    List<BigInteger> mathBuffer = new ArrayList<>();
    int packetCap = Integer.MAX_VALUE / 2;
    readPacket(from, to, packetCap, binaryInput, packetValues, mathBuffer);
    BigInteger output = new BigInteger("0", 10);
    for (BigInteger oneVal : packetValues) {
      output = output.add(oneVal);
    }
    return output;
  }

  private static void readPacket(int from, int to, int packetCap, String binaryInput, List<BigInteger> packetValues, List<BigInteger> mathBuffer) {
    if (to > binaryInput.length() - 5) {
      // reached end
      return;
    } else if (to > packetCap) {
      packetCap = Integer.MAX_VALUE / 2;
      if (mathBuffer.size() > 1) {
        doMathOnBuffer(packetValues, mathBuffer);
      }
    }
    System.out.println("New readPacket call, examining:");
    System.out.println(binaryInput.substring(from, Math.min(from + 20, binaryInput.length() - 1)));
    String version = binaryInput.substring(from, to);
    from = to;
    to = from + 3;
    addVersions(version);
    String typeId = binaryInput.substring(from, to);
    from = to;
    to += 1;
    switch (typeId) {
      case "100" -> literalValue(from, to, packetCap, binaryInput, packetValues, mathBuffer);
      case "000" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "add");
      case "001" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "multiply");
      case "010" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "min");
      case "011" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "max");
      case "101" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "greater");
      case "110" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "less");
      case "111" -> readMorePackets(from, to, packetCap, binaryInput, packetValues, mathBuffer, "equal");
      default -> System.out.println("ERROR: Unhandled packet");
    }
  }

  private static void doMathOnBuffer(List<BigInteger> packetValues, List<BigInteger> mathBuffer) {
    BigInteger output = new BigInteger("0");
    switch (nextMath) {
      case "add" -> {
        for (BigInteger oneVal : mathBuffer) {
          output = output.add(oneVal);
        }
      }
      case "multiply" -> {
        for (BigInteger oneVal : mathBuffer) {
          output = output.multiply(oneVal);
        }
      }
      case "min" -> {
        for (BigInteger oneVal : mathBuffer) {
          output = output.min(oneVal);
        }
      }
      case "max" -> {
        for (BigInteger oneVal : mathBuffer) {
          output = output.max(oneVal);
        }
      }
      case "greater" -> {
        output = BigInteger.valueOf((0 < mathBuffer.get(0).compareTo(mathBuffer.get(1))) ? 1 : 0);
      }
      case "less" -> {
        output = BigInteger.valueOf((0 > mathBuffer.get(0).compareTo(mathBuffer.get(1))) ? 1 : 0);
      }
      case "equal" -> {
        output = BigInteger.valueOf((0 == mathBuffer.get(0).compareTo(mathBuffer.get(1))) ? 1 : 0);
      }
      default -> System.out.println("ERROR! bad math operation");
    }
    packetValues.add(output);
    mathBuffer.removeAll(mathBuffer);
  }

  private static void readMorePackets(int from, int to, int packetCap, String binaryInput, List<BigInteger> packetValues, List<BigInteger> mathBuffer, String operation) {
    nextMath = operation;
    if (binaryInput.substring(from, to).equals("0")) {
      // 0: parse 15 bits then figure out how many bits after that from that number
      from = to;
      to += 15;
      int howManyMore = Integer.parseInt(binaryInput.substring(from, to), 2);
      packetCap = to + howManyMore;
      from = to;
      System.out.println("it said to grab " + howManyMore + " bits");
      to += 3;
      readPacket(from, to, packetCap,binaryInput, packetValues, mathBuffer);
    } else {
      // 1: parse 11 bits then figure out how many sets of 11 from that number
      from = to;
      to += 11;
      int howManyMore = Integer.parseInt(binaryInput.substring(from, to), 2);
      packetCap = to + howManyMore;
      from = to;
      System.out.println("it said to grab " + howManyMore + " sets of 11 bits");
      to += 3;
      readPacket(from, to, packetCap,binaryInput, packetValues, mathBuffer);
    }
  }


  private static void literalValue(int from, int to, int packetCap, String binaryInput, List<BigInteger> packetValues, List<BigInteger> mathBuffer) {
    StringBuilder literalValue = new StringBuilder();
    while (binaryInput.substring(from, to).equals("1")) {
      literalValue.append(binaryInput.substring(to, (to + 4)));
      System.out.print("1" + binaryInput.substring(to, (to + 4)) + " + ");
      from += 5;
      to +=5;
    }
    System.out.println("0" + binaryInput.substring(to, (to + 4)) + " needs to be converted ");
    literalValue.append(binaryInput.substring(to, (to + 4)));
    BigInteger blah = new BigInteger(literalValue.toString(), 2);
    mathBuffer.add(blah);
    from += 5;
    to = from + 3;
    readPacket(from, to, packetCap, binaryInput, packetValues, mathBuffer);
    //literal packet, test for a 0 or 1, parse next 4 as a literal number
    //                has 1####1####0#### , 0 is last part, 1 means more
  }

  private static void addVersions(String version) {
    int convertedTotal = Integer.parseInt(version, 2);
    versionTotal += convertedTotal;
    System.out.println("converted " + version + " added " + convertedTotal + " total: " + versionTotal);
  }
}

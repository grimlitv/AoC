package tv.grimli.y2015.seven;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class y2015d7 {

  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2015/seven/input.txt");
    HashMap<String, String[]> wires = new HashMap<>();

    for (String in : input) {
      String[] instruction = in.split(" ");
      String key = instruction[instruction.length - 1];
      wires.put(key, instruction);
    }

    return recTime("a", wires.get("a"), wires);
  }

  private static int recTime(String key, String[] instruction, HashMap<String, String[]> wires) {
    String[] newIns = new String[3];
    if (instruction.length == 3) {
      return valueCheck(instruction[0], wires);
    } else if (instruction.length == 4) {
      int left = ~valueCheck(instruction[1], wires);
      newIns[0] = String.valueOf(left);
      wires.put(key, newIns);
      return left;
    } else {
      // instruction length 5
      int left = valueCheck(instruction[0], wires);
      int right = valueCheck(instruction[2], wires);
      switch (instruction[1]) {
        case("AND") -> {
          int out = left & right;
          newIns[0] = String.valueOf(out);
          wires.put(key, newIns);
          return out;
        }
        case("OR") -> {
          int out = (left | right);
          newIns[0] = String.valueOf(out);
          wires.put(key, newIns);
          return out;
        }
        case("LSHIFT") -> {
          int out = (left << right);
          newIns[0] = String.valueOf(out);
          wires.put(key, newIns);
          return out;
        }
        case("RSHIFT") -> {
          int out = (left >> right);
          newIns[0] = String.valueOf(out);
          wires.put(key, newIns);
          return out;
        }
        default -> {
          System.out.println();
          System.out.println("ERROR: bad case statement, ignore output!");
          System.out.println();
          return 0;
        }
      }
    }
  }

  private static int valueCheck(String value, HashMap<String, String[]> wires) {
    if (isInteger(value)) {
      return Integer.parseInt(value);
    } else {
      return recTime(value, wires.get(value), wires);
    }
  }

  public static boolean isInteger(String s) {
    if(s.isEmpty()) return false;
    for(int i = 0; i < s.length(); i++) {
      if(i == 0 && s.charAt(i) == '-') {
        if(s.length() == 1) return false;
        else continue;
      }
      if(Character.digit(s.charAt(i),10) < 0) return false;
    }
    return true;
  }
}
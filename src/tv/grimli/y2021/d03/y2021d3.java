package tv.grimli.y2021.d03;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2021d3 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/three/input.txt");

    int[] solve = new int[input.get(0).length()];
    for (int sol : solve) {
      sol = 0;
    }

    for (String in : input) {
      for (int i = 0; i < in.length(); i++) {
        if (in.charAt(i) == '0') {
          solve[i] -= 1;
        } else {
          solve[i] += 1;
        }
      }
    }
    StringBuilder gammaSB = new StringBuilder();
    StringBuilder epsiSB = new StringBuilder();
    for (int v : solve) {
      if (v > 0) {
        gammaSB.append("1");
        epsiSB.append("0");
      } else {
        gammaSB.append("0");
        epsiSB.append("1");
      }
    }
    String fg = gammaSB.toString();
    String fe = epsiSB.toString();

    System.out.println(fg + " " + fe);
    int gamma = Integer.parseInt(fg,2);
    int epsi = Integer.parseInt(fe,2);

    System.out.println(gamma + " " + epsi);

    return gamma * epsi;
  }


  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/three/input.txt");

    String fo = recLists(input, 0, true);
    String fc = recLists(input, 0, false);

    System.out.println(fo + " " + fc);
    int oxy = Integer.parseInt(fo,2);
    int co2 = Integer.parseInt(fc,2);

    System.out.println(oxy + " " + co2);

    return oxy * co2;
  }

  private static String recLists(List<String> input, int index, boolean isOxy) {
    String output = "";
    if (input.size() == 1) {
      return input.get(0);
    } else {
      List<String> zero = new ArrayList<>();
      List<String> one = new ArrayList<>();

      for (String in : input) {
        if (in.charAt(index) == '0') {
          zero.add(in);
        } else {
          one.add(in);
        }
      }
      if (isOxy) {
        if (zero.size() <= one.size()) {
          output = recLists(one, (index + 1), true);
        } else {
          output = recLists(zero, (index + 1), true);
        }
      } else {
        if (zero.size() <= one.size()) {
          output = recLists(zero, (index + 1), false);
        } else {
          output = recLists(one, (index + 1), false);
        }
      }
    }
    return output;
  }


  private static int[] makeEmpty(List<String> input) {
    int[] solve = new int[input.get(0).length()];
    for (int sol : solve) {
      sol = 0;
    }
    return solve;
  }
}

package tv.grimli.y2015.three;

import tv.grimli.ReadFromFile;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class y2015d3 {
  public static int run() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2015/three/input.txt").get(0);

    //generate a hashmap
    HashMap<Point, Integer> santaMap = new HashMap<>();
    Point loc = new Point (0, 0);
    santaMap.put(loc, 1);
    for (int i = 0; i < input.length(); i++) {
      switch(input.charAt(i)) {
        case ('<') -> {
          loc.translate(-1,0);
        }
        case ('>') -> {
          loc.translate(1,0);
        }
        case ('^') -> {
          loc.translate(0,1);
        }
        case ('v') -> {
          loc.translate(0,-1);
        }
        default -> {
          System.out.println();
          System.out.println("ERROR! Should not get here " + input.charAt(i));
          System.out.println();
          return 0;
        }
      }
      System.out.print(loc);
//      if (!santaMap.containsKey(loc)) {
        santaMap.put(loc, 1);
//        System.out.print("+1");
//      } else {
//        santaMap.put(loc, santaMap.get(loc) + 1);
//      }
      System.out.println();
    }
    return santaMap.size();
  }

  public static int runCustData() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2015/three/input.txt").get(0);

    //generate a hashmap
    HashMap<String, Integer> santaMap = new HashMap<>();
    santaMap.put("0.0", 1);
    int x = 0;
    int y = 0;
    for (int i = 0; i < input.length(); i++) {
      switch(input.charAt(i)) {
        case ('<') -> {
          x--;
        }
        case ('>') -> {
          x++;
        }
        case ('^') -> {
          y--;
        }
        case ('v') -> {
          y++;
        }
        default -> {
          System.out.println();
          System.out.println("ERROR! Should not get here " + input.charAt(i));
          System.out.println();
          return 0;
        }
      }
      if (!santaMap.containsKey(x + "." + y)) {
        santaMap.put(x + "." + y, 1);
        System.out.print("+1");
      } else {
        santaMap.put(x + "." + y, santaMap.get(x + "." + y) + 1);
      }
      System.out.println(x + "." + y);
    }
    return santaMap.size();
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2015/three/input.txt").get(0);

    //generate a hashmap
    HashMap<String, Integer> santaMap = new HashMap<>();
    santaMap.put("0.0", 1);
    int xs = 0;
    int ys = 0;
    boolean isSanta = true;
    int xr = 0;
    int yr = 0;

    for (int i = 0; i < input.length(); i++) {
      switch(input.charAt(i)) {
        case ('<') -> {
          if (isSanta) {
            xs--;
          } else {
            xr--;
          }

        }
        case ('>') -> {
          if (isSanta) {
            xs++;
          } else {
            xr++;
          }
        }
        case ('^') -> {
          if (isSanta) {
            ys++;
          } else {
            yr++;
          }
        }
        case ('v') -> {
          if (isSanta) {
            ys--;
          } else {
            yr--;
          }
        }
        default -> {
          System.out.println();
          System.out.println("ERROR! Should not get here " + input.charAt(i));
          System.out.println();
          return 0;
        }
      }
      if (isSanta) {
        santaMap.put(xs + "." + ys, 1);
        isSanta = false;
      } else {
        santaMap.put(xr + "." + yr, 1);
        isSanta = true;
      }
    }
    return santaMap.size();
  }
}

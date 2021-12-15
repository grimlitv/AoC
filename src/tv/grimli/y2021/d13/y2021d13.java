package tv.grimli.y2021.d13;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.List;

public class y2021d13 {

  private static int dotsX = 2000;
  private static int dotsY = 2000;

  public static int run() throws IOException {
    // read from the text file
    List<String> dotInput = ReadFromFile.stringStreams("src/tv/grimli/y2021/d13/input.txt");
    List<String> instructions = ReadFromFile.stringStreams("src/tv/grimli/y2021/d13/instructions.txt");
    int[][] dots = new int[dotsY][dotsX];
    for (String in : dotInput) {
      String[] coords = in.split(",");
      int x = Integer.parseInt(coords[0]);
      int y = Integer.parseInt(coords[1]);
      dots[y][x] = 1;
    }
    for (String folds : instructions) {
      String[] insLine = folds.split("=");
      int nextFold = Integer.parseInt(insLine[1]);
      String nextFoldAxis = insLine[0].substring(insLine[0].length() -1);
      fold(nextFold, nextFoldAxis, dots);
    }
    printDots(dots);
    return countDots(dots);
  }

  private static int countDots(int[][] dots) {
    int output = 0;
    for (int y = 0; y < dotsY; y++) {
      for (int x = 0; x < dotsX; x++) {
        if (dots[y][x] >= 1) {
          output++;
        }
      }
    }
    return output;
  }

  private static void fold(int nextFold, String axis, int[][] dots) {
    if (axis.equals("x")) {
      for (int y = 0; y < dotsY; y++) {
        int shifted = nextFold + 1;
        for (int x = nextFold; x <= nextFold * 2; x++) {
          shifted--;
          dots[y][shifted] = dots[y][shifted] + dots[y][x];
        }
      }
      dotsX = nextFold;
    } else {
      int shifted = nextFold + 1;
      for (int y = nextFold; y <= nextFold * 2; y++) {
        shifted--;
        for (int x = 0; x < dotsX; x++) {
          dots[shifted][x] = dots[shifted][x] + dots[y][x];
        }
      }
      dotsY = nextFold;
    }
  }

  private static void printDots(int[][] dots) {
    for (int y = 0; y < dotsY; y++) {
      System.out.println();
      for (int x = 0; x < dotsX; x++) {
        if (dots[y][x] == 0) {
          System.out.print(". ");
        } else {
          System.out.print("# ");
        }
      }
    }
    System.out.println();
  }
}
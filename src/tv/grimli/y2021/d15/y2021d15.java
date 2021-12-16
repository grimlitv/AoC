package tv.grimli.y2021.d15;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.List;

public class y2021d15 {
  private static int mSize;
  private static int factor = 5;
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d15/input.txt");
    mSize = input.get(0).length();
    int[][] og = new int[mSize][mSize];
    int[][] lowest = new int[mSize][mSize];

    for (int y = 0; y < mSize; y++) {
      for (int x = 0; x < mSize; x++) {
        String[] oneLine = input.get(y).split("(?!^)");
        og[y][x] = Integer.parseInt(oneLine[x]);
        lowest[y][x] = Integer.MAX_VALUE;
      }
    }
    lowest[0][0] = 0;
    for (int y = 0; y < mSize; y++) {
      for (int x = 0; x < mSize; x++) {
        checkCell(og, lowest, y, x);
      }
    }
    printMatrix(og);
    printMatrix(lowest);
    return lowest[mSize - 1][mSize - 1];
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d15/input.txt");
    mSize = input.get(0).length() * factor;
    int[][] og = new int[mSize][mSize];
    int[][] lowest = new int[mSize][mSize];
    fillOG(input, og, lowest);
    lowest[0][0] = 0;
    for (int y = 0; y < mSize; y++) {
      for (int x = 0; x < mSize; x++) {
        checkCell(og, lowest, y, x);
      }
    }
    return lowest[mSize - 1][mSize - 1];
  }

  private static void fillOG(List<String> input, int[][] og, int[][] lowest) {
    int ogSize = mSize / factor;
    for (int yplus = 0; yplus < factor; yplus++) {
      int yfactor = yplus * ogSize;
      for (int y = 0; y < ogSize; y++) {
        for (int xplus = 0; xplus < factor; xplus++) {
          int xfactor = xplus * ogSize;
          for (int x = 0; x < ogSize; x++) {
            String[] oneLine = input.get(y).split("(?!^)");
            int valToWrite = Integer.parseInt(oneLine[x]) + yplus + xplus;
            while (valToWrite >= 10) {
              valToWrite -= 9;
            }
            og[y + yfactor][x + xfactor] = valToWrite;
            lowest[y + yfactor][x + xfactor] = Integer.MAX_VALUE;
          }
        }
      }
    }
  }

  private static void printMatrix(int[][] lowest) {
    for (int y = 0; y < mSize; y++) {
      System.out.println();
      for (int x = 0; x < mSize; x++) {
        System.out.print(lowest[y][x] + " ");
      }
    }
    System.out.println("difference: " + Math.abs(lowest[mSize - 1][mSize - 1] - lowest[0][0]));
    System.out.println();
  }

  private static void checkCell(int[][] og, int[][] lowest, int y, int x) {
    if (y == 0 && x == 0) {
      // we are still at 0,0 don't do nuthin.
      return;
    }
    int oldlowest = lowest[y][x];
    int compare = Integer.MAX_VALUE;
    //check up
    if (y != 0) {
      compare = Math.min(compare, lowest[y - 1][x]);
    }
    //check down
    if (y != mSize - 1) {
      compare = Math.min(compare, lowest[y + 1][x]);
    }
    //check left
    if (x != 0) {
      compare = Math.min(compare, lowest[y][x - 1]);
    }
    //check right
    if (x != mSize - 1) {
      compare = Math.min(compare, lowest[y][x + 1]);
    }
    // grab lowest number from a calculated neighbor plus the current value, or the old value
    lowest[y][x] = Math.min((og[y][x] + compare), lowest[y][x]);

    // if we made moves, we should check neighbors for improvements.
    if (lowest[y][x] < oldlowest) {
      if ((y != 0) // if not a border
              && (lowest[y - 1][x] > lowest[y][x] + og[y - 1][x]) // considers if it is feasible to lower
              && (lowest[y - 1][x] != Integer.MAX_VALUE)) { // doesn't attempt to fill recursively
        checkCell(og, lowest, (y - 1), x); //call this again at the new location
      }
      if ((y != mSize - 1)
              && (lowest[y + 1][x] > lowest[y][x] + og[y + 1][x])
              && (lowest[y + 1][x] != Integer.MAX_VALUE)) {
        checkCell(og, lowest, (y + 1), x);
      }
      if ((x != 0)
              && (lowest[y][x - 1] > lowest[y][x] + og[y][x - 1])
              && (lowest[y][x - 1] != Integer.MAX_VALUE)) {
        checkCell(og, lowest, y, (x - 1));
      }
      if ((x != mSize - 1)
              && (lowest[y][x + 1] > lowest[y][x] + og[y][x + 1])
              && (lowest[y][x + 1] != Integer.MAX_VALUE)) {
        checkCell(og, lowest, y, (x + 1));
      }
    }
  }
}
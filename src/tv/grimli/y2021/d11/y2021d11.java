package tv.grimli.y2021.d11;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2021d11 {

  private static final int howManySteps = 500;
  private static int matrixWidth = 10;
  private static int matrixHeight = 10;
  private static int flashes = 0;
  private static int[] whenPrint = new int[]{0,1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100};

  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d11/input.txt");
    List<List<Integer>> kittenMap = buildKittenMap(input);
    for (int i = 0; i < howManySteps; i++) {
      for (int print : whenPrint) {
        if (i == print) {
          System.out.println("Step: " + i);
          showKittens(kittenMap);
        }
      }
      increase(kittenMap);
      checkKittens(kittenMap);
      kittenReset(kittenMap);
    }
    return flashes;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d11/input.txt");
    List<List<Integer>> kittenMap = buildKittenMap(input);
    for (int i = 0; i < howManySteps; i++) {
      for (int print : whenPrint) {
        if (i == print) {
          System.out.println("Step: " + i);
          showKittens(kittenMap);
        }
      }
      increase(kittenMap);
      checkKittens(kittenMap);
      if (kittenResetPartTwo(kittenMap)) {
        return (i + 1);
      }
    }
    return -1;
  }

  public static List<List<Integer>> increase(int[] from, int[] to, List<List<Integer>> kittenMap) {
    for (int y = from[0]; y <= to[0]; y++) {
      for (int x = from[1]; x <= to[1]; x++) {
        // toggle the map at this point
        kittenMap.get(y).set(x, kittenMap.get(y).get(x) + 1);
      }
    }
    return kittenMap;
  }

  public static List<List<Integer>> increase(List<List<Integer>> kittenMap) {
    int[] from = new int[]{0,0};
    int[] to = new int[]{matrixHeight - 1, matrixWidth -1};
    return increase(from, to, kittenMap);
  }

  public static List<List<Integer>> kittenIncrease(int[] whichKitten, List<List<Integer>> kittenMap) {
    int[] from = new int[]{Math.max(0, (whichKitten[0] - 1)),Math.max(0, (whichKitten[1] - 1))};
    int[] to = new int[]{Math.min((matrixHeight - 1), (whichKitten[0] + 1)), Math.min((matrixWidth -1), (whichKitten[1] + 1))};
    return increase(from, to, kittenMap);
  }

  public static void checkKittens(List<List<Integer>> kittenMap) {
    for (int y = 0; y < matrixHeight; y++) {
      for (int x = 0; x < matrixWidth; x++) {
        // toggle the map at this point
        if (kittenMap.get(y).get(x) > 9) {
          int[] kitten = new int[]{y, x};
          kittenMap.get(y).set(x, Integer.MIN_VALUE);
          flashes++;
          kittenIncrease(kitten, kittenMap);
          checkKittens(kittenMap);
        }
      }
    }
  }

  public static void showKittens(List<List<Integer>> kittenMap) {
    for (int y = 0; y < matrixHeight; y++) {
      System.out.println();
      for (int x = 0; x < matrixWidth; x++) {
        System.out.print(kittenMap.get(y).get(x) + " ");
      }
    }
  }

  public static void kittenReset(List<List<Integer>> kittenMap) {
    for (int y = 0; y < matrixHeight; y++) {
      for (int x = 0; x < matrixWidth; x++) {
        // toggle the map at this point
        if (kittenMap.get(y).get(x) < 0) {
          kittenMap.get(y).set(x, 0);
        }
      }
    }
  }

  public static boolean kittenResetPartTwo(List<List<Integer>> kittenMap) {
    int howManyFlash = 0;
    for (int y = 0; y < matrixHeight; y++) {
      for (int x = 0; x < matrixWidth; x++) {
        // toggle the map at this point
        if (kittenMap.get(y).get(x) < 0) {
          howManyFlash++;
          kittenMap.get(y).set(x, 0);
        }
      }
    }
    return (howManyFlash == (matrixHeight * matrixWidth));
  }

  public static List<List<Integer>> buildKittenMap(List<String> input) {
    List<List<Integer>> kittenMap = new ArrayList<>();

    for (int y = 0; y < matrixHeight; y++) {
      String[] insertable = input.get(y).split("(?!^)");
      List<Integer> insert = new ArrayList<>();
      for (int x = 0; x < matrixWidth; x++) {
        insert.add(Integer.parseInt(insertable[x]));
      }
      kittenMap.add(insert);
    }
    return kittenMap;
  }
}
package tv.grimli.y2021.d05;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class y2021d05 {

  private static int gridSize = 1000;

  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d05/input.txt");
    List<List<Integer>> lineMap = buildLightMap();
    for (String instruction : input) {
      String[] insSet = instruction.split(" ");
      int[] from = Arrays.stream(insSet[0].split(",")).mapToInt(Integer::parseInt).toArray();
      int[] to = Arrays.stream(insSet[2].split(",")).mapToInt(Integer::parseInt).toArray();
      on(from, to, lineMap);
    }

    return processGrid(lineMap);
  }

  public static List<List<Integer>> diagonal(int[] from, int[] to, List<List<Integer>> lineMap) {

    // NW to SE or SE to NW
    // both 1x1y < 2x2y  || 1x 1y > 2x2y
    if ((from[0] > to[0] && from[1] > to[1]) || (from[0] < to[0] && from[1] < to[1])) {
      int x1 = Math.min(from[0], to[0]);
      int x2 = Math.max(from[0], to[0]);
      int y = Math.min(from[1], to[1]);

      for (int x = x1; x <= x2; x++) {
        // toggle the map at this point
        lineMap.get(x).set(y, lineMap.get(x).get(y) + 1);
        y++;
      }
    } else {
      // SW to NE or NE to SW
      int x1 = Math.min(from[0], to[0]);
      int x2 = Math.max(from[0], to[0]);
      int y = Math.max(from[1], to[1]);

      for (int x = x1; x <= x2; x++) {
        // toggle the map at this point
        lineMap.get(x).set(y, lineMap.get(x).get(y) + 1);
        y--;
      }
    }
    return lineMap;
  }

  public static List<List<Integer>> on(int[] from, int[] to, List<List<Integer>> lineMap) {
    //remove any diagonal lines
    if ((from[0] != to[0]) && (from[1] != to[1])) {
        return diagonal(from, to, lineMap);
    }

    int x1 = Math.min(from[0], to[0]);
    int x2 = Math.max(from[0], to[0]);
    int y1 = Math.min(from[1], to[1]);
    int y2 = Math.max(from[1], to[1]);

    for (int x = x1; x <= x2; x++) {
      for (int y = y1; y <= y2; y++) {
        // toggle the map at this point
        lineMap.get(x).set(y, lineMap.get(x).get(y) + 1);
      }
    }
    return lineMap;
  }

  public static int processGrid(List<List<Integer>> lineMap) {
    int output = 0;
    for (int x = 0; x <= gridSize -1; x++) {
      System.out.println();
      for (int y = 0; y <= gridSize -1; y++) {
        System.out.print(lineMap.get(y).get(x) + " ");
        // toggle the map at this point
        if (lineMap.get(x).get(y) >= 2) {
          output++;
        }
      }
    }
    System.out.println();
    return output;
  }

  public static List<List<Integer>> buildLightMap() {
    List<List<Integer>> lightMap = new ArrayList<>();
    for (int x = 0; x < gridSize; x++) {
      List<Integer> insert = new ArrayList<>();
      for (int y = 0; y < gridSize; y++) {
        insert.add(0);
      }
      lightMap.add(insert);
    }
    return lightMap;
  }
}
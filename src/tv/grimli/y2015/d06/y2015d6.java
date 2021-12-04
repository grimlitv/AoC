package tv.grimli.y2015.d06;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class y2015d6 {

  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2015/d06/input.txt");
    List<List<Integer>> lightMap = buildLightMap();
    for (String instruction : input) {
      String[] insSet = instruction.split(" ");
      if (insSet[0].equals("toggle")) {
        int[] from = Arrays.stream(insSet[1].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] to = Arrays.stream(insSet[3].split(",")).mapToInt(Integer::parseInt).toArray();
        toggle(from, to, lightMap);
      } else {
        int[] from = Arrays.stream(insSet[2].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] to = Arrays.stream(insSet[4].split(",")).mapToInt(Integer::parseInt).toArray();
        if (insSet[1].equals("off")) {
          off(from, to, lightMap);
        } else {
          on(from, to, lightMap);
        }
      }
    }

    return countLights(lightMap);
  }
  
  public static List<List<Integer>> toggle(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        lightMap.get(x).set(y, lightMap.get(x).get(y) + 2);
      }
    }
    return lightMap;
  }

  public static List<List<Integer>> on(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        lightMap.get(x).set(y, lightMap.get(x).get(y) + 1);
      }
    }
    return lightMap;
  }

  public static List<List<Integer>> off(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        int curBright = lightMap.get(x).get(y);
        if (curBright >= 1) {
          lightMap.get(x).set(y, curBright - 1);
        }
      }
    }
    return lightMap;
  }

  public static int countLights(List<List<Integer>> lightMap) {
    int output = 0;
    for (int x = 0; x <= 999; x++) {
      for (int y = 0; y <= 999; y++) {
        // toggle the map at this point
        output += lightMap.get(x).get(y);
      }
    }
    return output;
  }

  public static List<List<Integer>> buildLightMap() {
    List<List<Integer>> lightMap = new ArrayList<>();;
    for (int x = 0; x < 1000; x++) {
      List<Integer> insert = new ArrayList<>();
      for (int y = 0; y < 1000; y++) {
        insert.add(0);
      }
      lightMap.add(insert);
    }
    return lightMap;
  }
}
package tv.grimli.y2015.d09;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.*;

public class y2015d09 {
  public static int run() throws IOException {
    // read from the text file
    ArrayList<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/d09/input.txt");

    HashMap<String, HashMap<String, Integer>> cityMap = new HashMap<>();

    for (String in : input) {
      String[] line = in.split(" ");
      buildMap(cityMap, line[0], line[2], Integer.parseInt(line[4]));
      buildMap(cityMap, line[2], line[0], Integer.parseInt(line[4]));
    }

    List<String> cityList = new ArrayList<>(cityMap.keySet());
    List<List<String>> outcomes = new ArrayList<>();
    // build list of outcomes
    permute(cityList, 0, outcomes);
    // then going to do the math on each,
    List<Integer> distances = new ArrayList<>();
    for (List<String> path : outcomes) {

      int distance = 0;
      for (int i = 0; i < path.size() - 1; i++) {
        distance += cityMap.get(path.get(i)).get(path.get(i+1));
      }
      distances.add(distance);
      System.out.println(path + " " + distance);
    }
    Collections.sort(distances);
    // return smallest number
    // part one: return distances.get(0);
    return distances.get(distances.size() - 1);
  }

  // from https://stackoverflow.com/questions/2920315/permutation-of-array
  static void permute(List<String> cityNames, int k, List<List<String>> outcomes){
    for(int i = k; i < cityNames.size(); i++){
      Collections.swap(cityNames, i, k);
      permute(cityNames, k + 1, outcomes);
      Collections.swap(cityNames, k, i);
    }
    if (k == cityNames.size() -1){
      List<String> mutatedCities = new ArrayList<>(cityNames);
      outcomes.add(mutatedCities);
    }
  }

  private static void buildMap(HashMap<String, HashMap<String, Integer>> cityMap, String fromCity, String toCity, int dist) {
    if (!cityMap.containsKey(fromCity)) {
      HashMap<String, Integer> newDist = new HashMap<>();
      newDist.put(toCity, dist);
      cityMap.put(fromCity, newDist);
    } else {
      cityMap.get(fromCity).put(toCity, dist);
    }
  }
}
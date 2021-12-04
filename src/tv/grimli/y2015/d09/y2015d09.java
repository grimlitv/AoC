package tv.grimli.y2015.d09;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class y2015d09 {
  public static int run() throws IOException {
    // read from the text file
    ArrayList<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/d09/sample.txt");

    //figure out what we workin with (list of cities)
    //build a map key is city, list of connecting cities based on distance

    //connect shortest to shortest, as many times as possible, work outward, get a guess of minimum, brute force until exhausted

    HashMap<String, List<CityDist>> cityMap = new HashMap<>();
    for (String in : input) {
      String[] line = in.split(" ");
      buildMap(cityMap, line[0], line[2], Integer.parseInt(line[4]));
      buildMap(cityMap, line[2], line[0], Integer.parseInt(line[4]));
    }
    sortMap(cityMap);
    List<String> cityList = new ArrayList<>();
    for (Map.Entry mapElement : cityMap.entrySet()) {
      cityList.add((String) mapElement.getKey());
    }

    int lowestDistance;
    int distance = 0;
    for (Map.Entry mapElement : cityMap.entrySet()) {
      String key = (String) mapElement.getKey();
      if (!usedCities.contains(key)) {
        usedCities.add(key);
        for (int i = 0; i < cityMap.get(key).size(); i++) {
          if (!usedCities.contains(cityMap.get(key).get(i).getCity())) {
            usedCities.add(cityMap.get(key).get(i).getCity());
            distance += cityMap.get(key).get(i).getDist();
            break;
          }
        }
      }
    }
  }

  //need to identify/account for not able to reach all stops from given starts
  //need to pass down the list of stops in order, with the distance for that path
  //maybe return a list instead of int immediately?
  //split path and distance up? figure out permutations, and figure out distance
  //maybe try removing from the CityDist array instead of hashmap tomrrow?

  // remember: You rock, you got this! future me is definitely cooler than ... this? me?
  //           I am told that you are loved. Funky said so.
  //                written with love,
  //                  -past grimli.

  private static int recFun(HashMap<String, List<CityDist>> mapInput, int curDist) {
    if (mapInput.size() == 1) {
      // can maybe do calculations here? pass back the distance from wherever we find ourselves once
      // the array is this short... maybe pass back the distance instead of passing it forward to pass it back.
      for (Map.Entry inputEle : mapInput.entrySet()) {
        String currentLoc = (String) inputEle.getKey();
        HashMap<String, List<CityDist>> moddedMap = new HashMap<>(mapInput);
        moddedMap.remove(currentLoc);
        for (CityDist nextLoc : mapInput.get(currentLoc)) {
          if (moddedMap.containsKey(nextLoc.getCity())) {
            int newDist = curDist + nextLoc.getDist();
            recFun(moddedMap, newDist);
          }
        }
      }
      int newDist = curDist + nextLoc.getDist();
      return newDist;
      //get the distance to the only spot left, and return
    } else {
      //pick a key from mapInput
      for (Map.Entry inputEle : mapInput.entrySet()) {
        String currentLoc = (String) inputEle.getKey();
        HashMap<String, List<CityDist>> moddedMap = new HashMap<>(mapInput);
        moddedMap.remove(currentLoc);
        for (CityDist nextLoc : mapInput.get(currentLoc)) {
          if (moddedMap.containsKey(nextLoc.getCity())) {
            int newDist = curDist + nextLoc.getDist();
            recFun(moddedMap, newDist);
          }
        }
      }
    }
  }



  private static void buildMap(HashMap<String, List<CityDist>> cityMap, String key, String city, int dist) {
    if (!cityMap.containsKey(key)) {
      CityDist newDist = new CityDist(city, dist);
      List<CityDist> newArr = new ArrayList<>();
      newArr.add(newDist);
      cityMap.put(key, newArr);
    } else {
      CityDist newDist = new CityDist(city, dist);
      cityMap.get(key).add(newDist);
    }
  }

  private static void sortMap(HashMap<String, List<CityDist>> cityMap) {
    for (Map.Entry mapElement : cityMap.entrySet()) {
      String key = (String) mapElement.getKey();
      cityMap.put(key, cityMap.get(key).stream().sorted(comparing(CityDist::getDist)).collect(toList()));
    }
  }
}

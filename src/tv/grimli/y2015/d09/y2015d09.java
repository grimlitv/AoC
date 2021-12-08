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

    HashMap<String, List<CityDist>> cityMap = new HashMap<>();

    for (String in : input) {
      String[] line = in.split(" ");
      buildMap(cityMap, line[0], line[2], Integer.parseInt(line[4]));
      buildMap(cityMap, line[2], line[0], Integer.parseInt(line[4]));
    }

    sortMap(cityMap);
    List<String> cityList = new ArrayList<>(cityMap.keySet());

    int numOfCities = cityList.size();
    int numOfPermutations = factorial(numOfCities);
    int whatFactorial = numOfCities;
    int howManyCitiesToFill = factorial(numOfCities - 1);
    int whatCity = 0;


    String[][] instSet = new String[numOfPermutations][numOfCities];


    for (int col = 0; col < numOfCities; col++){
      System.out.println();
      whatFactorial--;
      for (int row = 0; row < numOfPermutations; row++) {
        if (howManyCitiesToFill > 0) {
          howManyCitiesToFill--;
        } else {
          boolean cityAlreadyPosted = true;
          while (cityAlreadyPosted) {
            if (col == 0) {
              whatCity++;
            } else {
              for (int test = 0; test < col; test++) {
                if (instSet[row][test].equals(cityList.get(whatCity))) {
                  whatCity++;
                  if (whatCity > numOfCities) {
                    whatCity = 0;
                  }
                } else {
                  cityAlreadyPosted = false;
                }
              }
            }
          }
          howManyCitiesToFill = factorial(whatFactorial);
        }
        System.out.println(whatCity + " ");
        instSet[row][col] = cityList.get(whatCity);
      }
    }





    // going to build a list of all permutations above this

    // then going to do the math on each,
    // storing only the smallest (and the path),
    // MAYBE: skipping if it becomes higher than the smallest.

    // return smallest number
    return 0;
  }

  private static int factorial(int number) {
    int stop = number;
    int out = number;
    while(stop > 2) {
      out = out * (stop -1);
      stop--;
    }
    return out;
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
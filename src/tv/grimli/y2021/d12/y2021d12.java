package tv.grimli.y2021.d12;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.*;

public class y2021d12 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d12/input.txt");
    HashMap<String, List<String>> pathMap = new HashMap<>();
    for (String in : input) {
      String[] where = in.split("-");
      if (where[0].equals("end")) {
        buildMap(pathMap, where[1], where[0]);
      } else if (where[1].equals("end")) {
        buildMap(pathMap, where[0], where[1]);
      } else if (where[0].equals("start")) {
        buildMap(pathMap, where[0], where[1]);
      } else if (where[1].equals("start")) {
        buildMap(pathMap, where[1], where[0]);
      } else {
        buildMap(pathMap, where[0], where[1]);
        buildMap(pathMap, where[1], where[0]);
      }
    }
    printMap(pathMap);
    List<List<String>> allPaths = new ArrayList<>();
    for (String nextLoc : pathMap.get("start")) {
      List<String> thisPath = new ArrayList<>();
      thisPath.add("start");
      thisPath.add(nextLoc);
      //Part One
      //recFunPathBuilder(nextLoc, thisPath, pathMap, allPaths);
      //Part Two
      recFunPathBuilderPartTwo(nextLoc, thisPath, pathMap, allPaths, "");
    }

    System.out.println(pathMap.keySet());
    printMap(allPaths);
    return allPaths.size();
  }

  private static void recFunPathBuilder(String weHere, List<String> inputPath, HashMap<String, List<String>> pathMap, List<List<String>> allPaths) {
    if (weHere.equals("end")) {
      //add the current path to the path tree (end state, does nothing else)
      allPaths.add(inputPath);
    } else {
      // grab list of where we can go
      for (String nextLoc : pathMap.get(weHere)) {
        List<String> newPath = new ArrayList<>(inputPath);
        // if next location is lowercase
        if (nextLoc.equals(nextLoc.toLowerCase())) {
          // check previous locations to see if we have visited
          boolean goodLocation = true;
          for (String prevLoc : newPath) {
            // if we haven't been there continue, otherwise return
            if (prevLoc.equals(nextLoc)) {
              goodLocation = false;
              break;
            }
          }
          if (goodLocation) {
            newPath.add(nextLoc);
            recFunPathBuilder(nextLoc, newPath, pathMap, allPaths);
          }
        } else {
          newPath.add(nextLoc);
          recFunPathBuilder(nextLoc, newPath, pathMap, allPaths);
        }
      }
    }
  }

  private static void recFunPathBuilderPartTwo(String weHere, List<String> inputPath, HashMap<String, List<String>> pathMap, List<List<String>> allPaths, String smolCave) {
    if (weHere.equals("end")) {
      //add the current path to the path tree (end state, does nothing else)
      allPaths.add(inputPath);
    } else {
      // grab list of where we can go
      for (String nextLoc : pathMap.get(weHere)) {
        List<String> newPath = new ArrayList<>(inputPath);
        // if next location is lowercase (one visit only)
        if (nextLoc.equals(nextLoc.toLowerCase())) {
          // check previous locations to see if we have visited
          boolean goodLocation = true;
          String newSmolCave = smolCave;
          for (String prevLoc : newPath) {
            if (prevLoc.equals(nextLoc)) {
              if (smolCave.isEmpty()) {
                //can continue, just set the smolcave to the current location
                newSmolCave = nextLoc;
              } else {
                //bad location, flag it
                goodLocation = false;
              }
              break;
            }
          }
          if (goodLocation) {
            newPath.add(nextLoc);
            recFunPathBuilderPartTwo(nextLoc, newPath, pathMap, allPaths, newSmolCave);
          }
        } else {
          //Uppercase target
          newPath.add(nextLoc);
          recFunPathBuilderPartTwo(nextLoc, newPath, pathMap, allPaths, smolCave);
        }
      }
    }
  }


  private static void buildMap(HashMap<String, List<String>> pathMap, String from, String to) {
    if (!pathMap.containsKey(from)) {
      List<String> dest = new ArrayList<>();
      dest.add(to);
      pathMap.put(from, dest);
    } else {
      pathMap.get(from).add(to);
    }
  }

  private static void printMap(List<List<String>> allPaths) {
    System.out.println("Path List:");
    for (List<String> onePath : allPaths) {
      System.out.println(onePath);
    }
  }

  private static void printMap(HashMap<String, List<String>> pathMap) {
    System.out.println("Path Map:");
    for (Map.Entry<String, List<String>> set :
            pathMap.entrySet()) {
      // Printing all elements of a Map
      System.out.println(set.getKey() + " = "
              + set.getValue());
    }
  }


}

package tv.grimli.y2015.d12;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2015d12 {
  public static int run() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/d12/input.txt").get(0);
    String[] inputArray = input.split("[a-z\"\\'\\{\\}\\,\\:\\[\\]]");
    List<Integer> intArr = new ArrayList<>();
    for (int i = 0; i < inputArray.length; i++) {
      if (!inputArray[i].isEmpty()) {
        intArr.add(Integer.parseInt(inputArray[i]));
      }
    }
    int output = 0;
    for (int in: intArr) {
      output += in;
    }
    return output;
  }

//  public static int runPartTwo() throws IOException {
//    // read from the text file
//    String input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/d12/input.txt").get(0);
//    String[] inputArray = input.split("[abcfghijklmnopqstuvwxyz\"\\'\\,\\:]");
//    List<Integer> outputInts = new ArrayList<>();
//    List<List<Integer>> thingsToAdd = new ArrayList<>();
//    List<Boolean> containsRed = new ArrayList<>();
//    boolean inArray = false;
//    int bracket = -1;
//    for (int i = 0; i < inputArray.length; i++) {
//      System.out.print(inputArray[i]);
//      if (!inputArray[i].isEmpty()) {
//        switch (inputArray[i]) {
//          case ("{") -> {
//            bracket++;
//            containsRed.add(bracket, false);
//            List<Integer> addMeMaybe = new ArrayList<>();
//            thingsToAdd.add(bracket, addMeMaybe);
//          }
//          case ("}") -> {
//            System.out.println();
//            if (Boolean.FALSE.equals(containsRed.get(bracket))) {
//              for (int addThis : thingsToAdd.get(bracket)) {
//                outputInts.add(addThis);
//              }
//            }
//            thingsToAdd.remove(bracket);
//            containsRed.remove(bracket);
//            bracket--;
//          }
//          case ("[") -> inArray = true;
//          case ("]") -> inArray = false;
//          case ("red") -> {
//            if (!inArray) {
//              containsRed.set(bracket, true);
//            }
//          }
//          default -> {
//            inputArray[i] = inputArray[i].replaceAll("[red\\{\\}\\[\\]]", "");
//            if (!inputArray[i].isEmpty()) {
//              thingsToAdd.get(bracket).add(Integer.parseInt(inputArray[i]));
//            }
//          }
//        }
//      }
//    }
//    int output = 0;
//    for (int in: outputInts) {
//      output += in;
//    }
//    System.out.println();
//    return output;
//  }

  public static int runPartTwo() throws IOException {

  }
}

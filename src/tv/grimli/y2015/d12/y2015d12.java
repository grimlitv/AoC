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

  public static int runPartTwo() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/d12/input.txt").get(0);
    String[] inputArray = input.split("[abcfghijklmnopqstuvwxyz\"\\'\\{\\}\\,\\:\\[\\]]");
    List<Integer> intArr = new ArrayList<>();
    boolean flagNext = false;
    for (int i = 0; i < inputArray.length; i++) {
      System.out.println(inputArray[i]);
      if (!inputArray[i].isEmpty()) {
        if (inputArray[i].equals("red")) {
          flagNext = true;
        } else {
          inputArray[i] = inputArray[i].replaceAll("[red]", "");
          if (!inputArray[i].isEmpty()) {
            if (!flagNext) {
              intArr.add(Integer.parseInt(inputArray[i]));
            } else {
              flagNext = false;
            }
          }
        }
      }
    }
    int output = 0;
    for (int in: intArr) {
      output += in;
    }
    return output;
  }
}

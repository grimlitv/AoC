package tv.grimli.y2015.two;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class y2015d2 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2015/two/input.txt");

    int output = 0;
    // iterate through it
    for (String box : input) {
      String[] boxValues = box.split("x");
      ArrayList<Integer> valueString = new ArrayList<>();
      valueString.add(Integer.parseInt(boxValues[0]));
      valueString.add(Integer.parseInt(boxValues[1]));
      valueString.add(Integer.parseInt(boxValues[2]));
      Collections.sort(valueString);
      System.out.println(valueString);
      int l = valueString.get(0);
      int w = valueString.get(1);
      int h = valueString.get(2);
      output += (3*l*w) + (2*w*h) + (2*h*l);
    }
    return output;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2015/two/input.txt");

    int output = 0;
    // iterate through it
    for (String box : input) {
      String[] boxValues = box.split("x");
      ArrayList<Integer> valueString = new ArrayList<>();
      valueString.add(Integer.parseInt(boxValues[0]));
      valueString.add(Integer.parseInt(boxValues[1]));
      valueString.add(Integer.parseInt(boxValues[2]));
      Collections.sort(valueString);
      System.out.println(valueString);
      int l = valueString.get(0);
      int w = valueString.get(1);
      int h = valueString.get(2);
      output += ((2*l) + (2*w) + (l*w*h));
    }
    return output;
  }

}

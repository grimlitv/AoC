package tv.grimli.y2021.one;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class y2021d1 {
  // int[] input = new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263};
//  This report indicates that, scanning outward from the submarine,
//  the sonar sweep found depths of 199, 200, 208, 210, and so on.
//  The first order of business is to figure out how quickly the depth increases, just so you know what you're dealing with
//  - you never know if the keys will get carried into deeper water by an ocean current or a fish or something.
//  To do this, count the number of times a depth measurement increases from the previous measurement.
//  (There is no measurement before the first measurement.) In the example above, the changes are as follows:

  public static int run() throws IOException, URISyntaxException {
    // read from the text file
    ArrayList<Integer> input = ReadFromFile.intStreams("src/tv/grimli/day/one/input.txt");

    int output = 0;
    // iterate through it
    int old = input.get(0);

    for (int i = 1; i < input.size(); i++) {
      // storing value to compare
      int current = input.get(i);
      // compare value
      if (current > old) {
        // ++ output if bigger
        output++;
        System.out.println(current + " is bigger than " + old + " output: " + output);
      }
      // store value as old
      old = input.get(i);
    }
    return output;
  }


  // 1743 right answer
  public static int runPartTwo() throws IOException, URISyntaxException {
    // read from the text file
    ArrayList<Integer> input = ReadFromFile.intStreams("src/tv/grimli/day/one/input.txt");

    int output = 0;
    // inital values
    int old = (input.get(0) + input.get(1) + input.get(2));

    // iterate through it
    for (int i = 3; i < input.size(); i++) {
      // storing value to compare
      int current = (input.get(i) + input.get(i - 1) + input.get(i - 2));
      // compare value
      if (current > old) {
        // ++ output if bigger
        output++;
        System.out.println(current + " is bigger than " + old + " output: " + output);
      }
      // store value as old
      old = current;
    }
    return output;
  }

  public static int runPartTwoUpgrade() throws IOException, URISyntaxException {
    // read from the text file
    ArrayList<Integer> input = ReadFromFile.intStreams("src/tv/grimli/day/one/input.txt");

    int output = 0;
    // iterate through it
    for (int i = 0; i < (input.size() - 3); i++) {
      // compare value
      if (input.get(i + 3) > input.get(i)) {
        // ++ output if bigger
        output++;
      }
    }
    return output;
  }

}

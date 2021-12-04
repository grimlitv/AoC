package tv.grimli.y2015.d01;

import tv.grimli.ReadFromFile;

import java.io.IOException;

public class y2015d1 {
  public static int run() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/one/input.txt").get(0);

    while (input.contains("()") || input.contains(")(")) {
      input = input.replaceAll("\\(\\)", "");
      input = input.replaceAll("\\)\\(", "");
      System.out.print(input.length() + " ");
    }
    System.out.println();
    if (input.contains("(")) {
      return input.length();
    }
    return input.length() * -1;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/one/input.txt").get(0);
    int floor = 0;
    for (int i = 0; i < input.length(); i++) {

      if (input.charAt(i) == '(') {
        floor++;
      } else {
        floor--;
      }
      if (floor == -1) {
        return i + 1;
      }
    }
    System.out.println();
    System.out.println("Fail");
    return 0;
  }
}

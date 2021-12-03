package tv.grimli.y2015.five;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class y2015d5 {



  public static int run() throws IOException {
    // read from the text file
    Pattern find = Pattern.compile("([a-z])(?<double>\\1)");
    List<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/five/input.txt");
    int output = 0;
    for (String test : input) {
      System.out.println(test);
      // ab, cd, pq, or xy
      if (!test.contains("ab") && !test.contains("cd") && !test.contains("pq") && !test.contains("xy")) {
        System.out.println("no abcdpqxy");
        //([a-z])(?<double>\1)
        Matcher found = find.matcher(test);
        if (found.find()) {
          System.out.println("found doubles");
          //3 vowels
          int currSize = test.length();
          int newSize = test.replaceAll("[aeiou]", "").length();
          System.out.println(currSize + " size > " + newSize);
          if ((newSize + 3) <= currSize) {
            output++;
          }
        }
      }
      System.out.println();
    }
    return output;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    Pattern find = Pattern.compile("(?=.*([a-z]{2}).*(\\1))(?=.*([a-z]){1}.(\\3))");
    List<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/five/input.txt");
    int output = 0;
    for (String test : input) {
      Matcher found = find.matcher(test);
      if (found.find()) {
          output++;
      }
    }
    return output;
  }
}

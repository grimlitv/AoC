package tv.grimli.y2015.eight;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class y2015d8 {
  public static int run() throws IOException {
    // read from the text file
    ArrayList<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/eight/input.txt");

    int output = 0;
    Pattern find = Pattern.compile("(?<hexa>\\\\x[0-9a-fA-F]{2})|(?<slash>\\\\\\\\)|(?<quote>\\\\\\\")");

    for (int i = 0; i < input.size(); i++) {
      String adjustMe = input.get(i);
      int initLength = adjustMe.length();
      System.out.println(adjustMe);
      // strip leading and trailing "
      adjustMe = adjustMe.substring(1, adjustMe.length() - 1);
      System.out.println(adjustMe);
      Matcher found = find.matcher(adjustMe);
      if (found.find()) {
        // \x plus two hexadecimal characters (which represents a single character with that ASCII code)
        adjustMe = adjustMe.replaceAll("(?<hexa>\\\\x[0-9a-fA-F]{2})|(?<slash>\\\\\\\\)|(?<quote>\\\\\\\")", "z");
        System.out.println(adjustMe);
      }
      int finalLength = adjustMe.length();
      System.out.println(initLength + " - " + finalLength);
      output += (initLength - finalLength);
    }
    return output;
  }


  public static int runPartTwo() throws IOException {
    // read from the text file
    ArrayList<String> input = ReadFromFile.stringBufferedReader("src/tv/grimli/y2015/eight/input.txt");

    int output = 0;
    for (int i = 0; i < input.size(); i++) {
      String adjustMe = input.get(i);
      int initLength = adjustMe.length();
      adjustMe = adjustMe.replace("\"", "aa");
      adjustMe = adjustMe.replace("\\", "aa");
      int finalLength = adjustMe.length();
      System.out.println(finalLength + " - " + initLength);
      output += ((finalLength - initLength) + 2);
    }
    return output;
  }



}

package tv.grimli.y2021.d10;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class y2021d10 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d10/input.txt");
    int output = 0;
    for (String in : input) {
      while (in.contains("()") || in.contains("[]") || in.contains("{}") || in.contains("<>")) {
        in = in.replace("()", "")
               .replace("[]", "")
               .replace("{}", "")
               .replace("<>", "");
      }
      if (in.contains(")") || in.contains("]") || in.contains("}") || in.contains(">")) {
        boolean notFoundFirstBad = true;
        char[] testMe = in.toCharArray();
        int index = 0;
        while (notFoundFirstBad) {
          switch (testMe[index]) {
            case ')' -> {
              output += 3;
              notFoundFirstBad = false;
            }
            case ']' -> {
              output += 57;
              notFoundFirstBad = false;
            }
            case '}' -> {
              output += 1197;
              notFoundFirstBad = false;
            }
            case '>' -> {
              output += 25137;
              notFoundFirstBad = false;
            }
            default -> {index++;}
          }
        }
      }
    }
    return output;
  }

  public static long runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d10/input.txt");
    List<Long> outputScores = new ArrayList<>();
    for (String in : input) {
      while (in.contains("()") || in.contains("[]") || in.contains("{}") || in.contains("<>")) {
        in = in.replace("()", "")
                .replace("[]", "")
                .replace("{}", "")
                .replace("<>", "");
      }
      if (!in.contains(")") && !in.contains("]") && !in.contains("}") && !in.contains(">")) {
        List<Integer> scores = new ArrayList<>();
        char[] testMe = in.toCharArray();
        for (int i = testMe.length - 1; i >= 0; i--) {
          switch (testMe[i]) {
            case '(' -> scores.add(1);
            case '[' -> scores.add(2);
            case '{' -> scores.add(3);
            case '<' -> scores.add(4);
            default -> {}
          }
        }
        outputScores.add(scoreLine(scores));
      }
    }
    Collections.sort(outputScores);
    int middleIndex = (outputScores.size() / 2);
    return outputScores.get(middleIndex);
  }

  private static long scoreLine(List<Integer> scores) {
    long output = 0;
    for (int bracket : scores) {
      output *= 5;
      output += bracket;
    }
    return output;
  }
}
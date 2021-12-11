package tv.grimli.y2021.d10;

import tv.grimli.ReadFromFile;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.*;

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

  public static long runPartTwoAsStack() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d10/input.txt");
    List<Long> outputScores = new ArrayList<>();
    for (String in : input) {
      char[] stackInput = in.toCharArray();
      List<Character> stack = new ArrayList<>();
      boolean corrupted = false;
      int stackIndex = 0;
      for (int i = 0; i < stackInput.length; i++) {
        switch (stackInput[i]) {
          case '(' -> {
            stack.add(')');
            stackIndex++;
          }
          case '{' -> {
            stack.add('}');
            stackIndex++;
          }
          case '[' -> {
            stack.add(']');
            stackIndex++;
          }
          case '<' -> {
            stack.add('>');
            stackIndex++;
          }
          default -> {
            if (stack.get(stackIndex - 1) == stackInput[i]) {
              stackIndex--;
              stack.remove(stackIndex);
            } else {
              corrupted = true;
            }
          }
        }
      }
      if (!corrupted) {
        Collections.reverse(stack);
        outputScores.add(scoreLineStack(stack));
      }
    }
    Collections.sort(outputScores);
    System.out.println(outputScores);
    int middleIndex = (outputScores.size() / 2);
    return outputScores.get(middleIndex);
  }

  public static long runPartTwoAsJavaStack() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d10/input.txt");
    List<Long> outputScores = new ArrayList<>();
    for (String in : input) {
      char[] stackInput = in.toCharArray();
      Stack<Character> stack = new Stack<>();
      boolean corrupted = false;
      for (int i = 0; i < stackInput.length; i++) {
        switch (stackInput[i]) {
          case '(' -> stack.push(')');
          case '{' -> stack.push('}');
          case '[' -> stack.push(']');
          case '<' -> stack.push('>');
          default -> {
            if (stack.peek() == stackInput[i]) {
              stack.pop();
            } else {
              corrupted = true;
            }
          }
        }
      }
      if (!corrupted) {
        Collections.reverse(stack);
        outputScores.add(scoreLineStack(stack));
      }
    }
    Collections.sort(outputScores);
    System.out.println(outputScores);
    int middleIndex = (outputScores.size() / 2);
    return outputScores.get(middleIndex);
  }

  private static Long scoreLineStack(List<Character> stack) {
    long output = 0;
    for (char bracket : stack) {
      output = switch (bracket) {
        case ')' -> (output * 5) + 1;
        case ']' -> (output * 5) + 2;
        case '}' -> (output * 5) + 3;
        default -> (output * 5) + 4; // its a '>'
      };
    }
    return output;
  }
}
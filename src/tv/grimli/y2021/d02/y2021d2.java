package tv.grimli.y2021.d02;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class y2021d2 {
  public static int run() throws IOException, URISyntaxException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/day/two/input.txt");
//    List<String> input = new ArrayList<>();
//    input.add("forward 5");
//    input.add("down 5");
//    input.add("forward 8");
//    input.add("up 3");
//    input.add("down 8");
//    input.add("forward 2");

    int depth = 0;
    int distance = 0;
    // iterate through it

    for (int i = 0; i < input.size(); i++) {
      // split values
      String[] read = input.get(i).split(" ");
      // process directions
      switch (read[0]) {
        case ("forward") -> {
          System.out.print("F" + read[1] + " ");
          distance += Integer.parseInt(read[1]);
        }
        case ("down") -> {
          System.out.print("D" + read[1] + " ");
          depth += Integer.parseInt(read[1]);
        }
        case ("up") -> {
          System.out.print("U" + read[1] + " ");
          depth -= Integer.parseInt(read[1]);
        }
        default -> {
          System.out.println("");
          System.out.println("ERROR: couldn't read directions! Bad output");
          System.out.println("");
        }
      }
    }
    System.out.println("");
    System.out.println("Horizontal position of " + distance + " and a depth of " + depth);
    return depth * distance;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/day/two/input.txt");
//    List<String> input = new ArrayList<>();
//    input.add("forward 5");
//    input.add("down 5");
//    input.add("forward 8");
//    input.add("up 3");
//    input.add("down 8");
//    input.add("forward 2");

    int depth = 0;
    int distance = 0;
    int aim = 0;
    // iterate through it

    for (String s : input) {
      // split values
      String[] read = s.split(" ");
      int whatDo = Integer.parseInt(read[1]);
      // process directions
      switch (read[0]) {
        case ("forward") -> {
          distance += whatDo;
          depth += whatDo * aim;
        }
        case ("down") -> {
          aim += whatDo;
        }
        case ("up") -> {
          aim -= whatDo;
        }
        default -> {
          System.out.println("");
          System.out.println("ERROR: couldn't read directions! Bad output");
          System.out.println("");
        }
      }
    }
    System.out.println("");
    System.out.println("Horizontal position of " + distance + " and a depth of " + depth);
    return depth * distance;
  }
}

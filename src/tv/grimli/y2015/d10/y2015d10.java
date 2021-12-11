package tv.grimli.y2015.d10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2015d10 {
  public static int run() throws IOException {
    //char[] input = "1".toCharArray();
    char[] input = "3113322113".toCharArray();
    int howManyTimes = 50; // 5 for sample, 40 for part one

    List<Character> output = new ArrayList<>();
    for(char put : input) {
      output.add(put);
    }

    for (int i = 0; i < howManyTimes; i++) {
      output = procreate(output);
    }
    return output.size();
  }

  private static List<Character> procreate(List<Character> input) {
    List<Character> output = new ArrayList<>();
    char workingWith = input.get(0);
    int count = 1;
    for (int i = 1; i < input.size(); i++) {
      if (input.get(i) == workingWith) {
        count++;
      } else {
        for(char put : String.valueOf(count).toCharArray()) {
          output.add(put);
        }
        output.add(workingWith);
        workingWith = input.get(i);
        count = 1;
      }
    }
    for(char put : String.valueOf(count).toCharArray()) {
      output.add(put);
    }
    output.add(workingWith);
    System.out.println(output);
    return output;
  }
}

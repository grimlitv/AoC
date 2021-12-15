package tv.grimli.y2021.d14;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.*;

public class y2021d14 {

  private static int steps = 40;
  public static int run() throws IOException {
    // read from the text file
    List<String> dotInput = ReadFromFile.stringStreams("src/tv/grimli/y2021/d14/input.txt");

    String startVal = dotInput.get(0);
    HashMap<String, String> rules = new HashMap<>();

    for(int i = 2; i < dotInput.size(); i++) {
      String[] splitMe = dotInput.get(i).split(" -> ");
      rules.put(splitMe[0], splitMe[1]);
    }
    String state = startVal;
    System.out.println(startVal);
    for (int i = 0; i < steps; i++) {
      state = applyRules(state, rules);
    }

    return countElements(state);
  }

  private static int countElements(String state) {
    List<Integer> outputCounts = new ArrayList<>();
    String[] elementList = state.split("(?!^)");
    Set<String> elementSet = new HashSet<>();
    for (String element : elementList) {
      elementSet.add(element);
    }
    System.out.println(elementSet);
    for (String oneEle : elementSet) {
      int count = 0;
      for (String listItem : elementList) {
        if (oneEle.equals(listItem)) {
          count++;
        }
      }
      outputCounts.add(count);
    }
    Collections.sort(outputCounts);
    System.out.println(outputCounts);
    return outputCounts.get(outputCounts.size() - 1) - outputCounts.get(0);
  }

  private static String applyRules(String inputState, HashMap<String, String> rules) {
    List<String> insSet = new ArrayList<>();
    for (int i = 0; i < inputState.length() - 1; i++) {
      insSet.add(inputState.substring(i, i+2));
    }
    StringBuilder newState = new StringBuilder();
    for (String instruction : insSet) {
      newState.append(instruction.substring(0,1));
      newState.append(rules.getOrDefault(instruction, ""));
    }
    newState.append(inputState.substring(inputState.length() - 1));
    System.out.println();
    System.out.println(newState);
    return newState.toString();
  }
}

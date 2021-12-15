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

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> dotInput = ReadFromFile.stringStreams("src/tv/grimli/y2021/d14/input.txt");

    String startVal = dotInput.get(0);
    String[] elementList = startVal.split("(?!^)");
    Set<String> elementSet = new HashSet<>();
    for (String element : elementList) {
      elementSet.add(element);
    }
    HashMap<String, String> rules = new HashMap<>();
    HashMap<String, Integer> state = new HashMap<>();
    for(int i = 2; i < dotInput.size(); i++) {
      String[] splitMe = dotInput.get(i).split(" -> ");
      rules.put(splitMe[0], splitMe[1]);
      state.put(splitMe[0], 0);
      elementSet.add(splitMe[1]);
    }
    System.out.println(startVal);

    List<String> insSet = new ArrayList<>();
    for (int i = 0; i < startVal.length() - 1; i++) {
      insSet.add(startVal.substring(i, i+2));
    }
    for (String instruction : insSet) {
      state.put(instruction, state.get(instruction) + 1);
    }
    for (int i = 0; i < steps; i++) {
      state = applyRulesPartTwo(state, startVal, rules);
    }
    return countElementsPartTwo(state, startVal, elementSet, rules);
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

  private static HashMap<String, Integer> applyRulesPartTwo(HashMap<String, Integer> state, String startVal, HashMap<String, String> rules) {
    HashMap<String, Integer> outputState = new HashMap<>(state);
    for (Map.Entry<String, Integer> ins : state.entrySet()) {
      String rule = ins.getKey();
      int result = ins.getValue();
      if (result > 0) {
        String middleChar = rules.get(rule);
        String firstCall = rule.charAt(0) + middleChar;
        String secondCall = middleChar + rule.charAt(1);
        outputState.put(firstCall, outputState.get(firstCall) + 1);
        outputState.put(secondCall, outputState.get(secondCall) + 1);
      }
    }
    return outputState;
  }

  private static int countElementsPartTwo(HashMap<String, Integer> state, String startVal, Set<String> elementSet, HashMap<String, String> rules) {
    HashMap<String, Integer> outputCounts = new HashMap<>();
    for (Map.Entry<String, Integer> ins : state.entrySet()) {
      String rule = ins.getKey();
      int result = ins.getValue();
      String element = rules.get(rule);
      outputCounts.put(element, outputCounts.getOrDefault(element, 0) + result);
    }
    String lastChar = startVal.substring(startVal.length() - 1);
    outputCounts.put(lastChar, outputCounts.getOrDefault(lastChar, 0) + 1);
    List<Integer> outputNumbers = new ArrayList<>();
    for (Map.Entry<String, Integer> ins : outputCounts.entrySet()) {
      outputNumbers.add(ins.getValue());
    }
    Collections.sort(outputNumbers);
    System.out.println(outputNumbers);
    return outputNumbers.get(outputNumbers.size() - 1) - outputNumbers.get(0);

  }
}

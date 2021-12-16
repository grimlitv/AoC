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

  public static long runPartTwo() throws IOException {
    // read from the text file
    List<String> dotInput = ReadFromFile.stringStreams("src/tv/grimli/y2021/d14/input.txt");

    String startVal = dotInput.get(0);
    String[] elementList = startVal.split("(?!^)");
    Set<String> elementSet = new HashSet<>();
    for (String element : elementList) {
      elementSet.add(element);
    }
    HashMap<String, String> rules = new HashMap<>();
    HashMap<String, Long> state = new HashMap<>();
    for(int i = 2; i < dotInput.size(); i++) {
      String[] splitMe = dotInput.get(i).split(" -> ");
      rules.put(splitMe[0], splitMe[1]);
      state.put(splitMe[0], (long)0);
      elementSet.add(splitMe[1]);
    }
    System.out.println("Element Set:");
    System.out.println(elementSet);
    System.out.println("Start Value:");
    System.out.println(startVal);

    List<String> insSet = new ArrayList<>();
    for (int i = 0; i < startVal.length() - 1; i++) {
      insSet.add(startVal.substring(i, i+2));
    }
    for (String instruction : insSet) {
      state.put(instruction, state.get(instruction) + 1);
    }
    for (int i = 0; i < steps - 1; i++) {
      state = applyRulesPartTwo(state, startVal, rules);
    }
    return countElementsPartTwo(state, startVal, elementSet, rules);
  }

  private static int countElements(String state) {
    List<Long> outputCounts = new ArrayList<>();
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
      outputCounts.add((long)count);
    }
    Collections.sort(outputCounts);
    System.out.println(outputCounts);
    return (int) (outputCounts.get(outputCounts.size() - 1) - outputCounts.get(0));
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

  private static HashMap<String, Long> applyRulesPartTwo(HashMap<String, Long> state, String startVal, HashMap<String, String> rules) {
    HashMap<String, Long> outputState = new HashMap<>();
    for (Map.Entry<String, Long> ins : state.entrySet()) {
      String rule = ins.getKey();
      long result = ins.getValue();
      if (result > 0) {
        String middleChar = rules.get(rule);
        String firstCall = rule.charAt(0) + middleChar;
        String secondCall = middleChar + rule.charAt(1);
        outputState.put(firstCall, outputState.getOrDefault(firstCall, 0L) + result);
        outputState.put(secondCall, outputState.getOrDefault(secondCall, 0L) + result);
      }
    }
    return outputState;
  }

  private static long countElementsPartTwo(HashMap<String, Long> state, String startVal, Set<String> elementSet, HashMap<String, String> rules) {
    printState(state, rules);
    HashMap<String, Long> outputCounts = new HashMap<>();
    for (Map.Entry<String, Long> ins : state.entrySet()) {
      String rule = ins.getKey();
      long result = ins.getValue();
      String element = rules.get(rule);
      if (result > 0) {
        outputCounts.put(rule.substring(0,1), outputCounts.getOrDefault(rule.substring(0,1), (long)0) + result);
        System.out.println(rule.substring(0,1) + " + " + result + " (first part of rule)");
        outputCounts.put(element, outputCounts.getOrDefault(element, (long)0) + result);
        System.out.println(element + " + " + result);
      }
    }
    String lastChar = startVal.substring(startVal.length() - 1);
    outputCounts.put(lastChar, outputCounts.getOrDefault(lastChar, (long)0) + 1);
    System.out.println(lastChar + " + " + 1 + " (last char)");
    List<Long> outputNumbers = new ArrayList<>();
    for (Map.Entry<String, Long> ins : outputCounts.entrySet()) {
      outputNumbers.add(ins.getValue());
    }
    Collections.sort(outputNumbers);
    System.out.println(outputNumbers);
    return outputNumbers.get(outputNumbers.size() - 1) - outputNumbers.get(0);

  }

  private static void printState(HashMap<String, Long> state, HashMap<String, String> rules) {
    for (Map.Entry<String, Long> ins : state.entrySet()) {
      System.out.print("Key : " + ins.getKey() + " Value: " + ins.getValue());
      System.out.println(" Result: " + rules.get(ins.getKey()));
    }
    System.out.println();
  }

}

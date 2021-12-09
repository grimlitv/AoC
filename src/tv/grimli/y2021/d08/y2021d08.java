package tv.grimli.y2021.d08;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class y2021d08 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d08/input.txt");
    List<List<String>> puzzle = new ArrayList<>();

    for (String fSplit : input) {
      String split1 = fSplit.split("\\|")[1].strip();
      String[] split2 = split1.split(" ");

      List<String> putIn = new ArrayList<>(Arrays.asList(split2));
      puzzle.add(putIn);
    }
    int output = 0;
    for (List<String> oneLine : puzzle) {
      for (String letter : oneLine) {
        if ((letter.length() == 2)
                || (letter.length() == 3)
                || (letter.length() == 4)
                || (letter.length() == 7)) {
          output++;
        }
      }
    }
    return output;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d08/input.txt");
    List<List<String>> puzzle = new ArrayList<>();

    for (String fSplit : input) {
      String[] split1 = fSplit.split("[ \\|]+");

      for (int i = 0; i < split1.length; i++) {
        split1[i] = sortString(split1[i]);
      }

      List<String> putIn = new ArrayList<>(Arrays.asList(split1));
      puzzle.add(putIn);
    }
    int output = 0;
    for (List<String> oneLine : puzzle) {

      // ? ? ? ? ? ? ?
      // 0 1 2 3 4 5 6
      String[] solvedSegments = new String[7];
      Set<String> setPuzzleNumbers = new HashSet<>();
      String[] solvedNumbers = new String[10];

      for (String number : oneLine) {
        setPuzzleNumbers.add(number);
      }
      // segment locations
      //-0-
      //1-2
      //-3-
      //4-5
      //-6-
      findSegmentFive(setPuzzleNumbers, solvedSegments);
      findNumberByStringLength(setPuzzleNumbers, solvedNumbers, 2, 1);
      findSegmentTwo(setPuzzleNumbers, solvedNumbers, solvedSegments);
      findNumberByStringLength(setPuzzleNumbers, solvedNumbers, 4, 4);
      findNumberByStringLength(setPuzzleNumbers, solvedNumbers, 3, 7);
      findNumberByStringLength(setPuzzleNumbers, solvedNumbers, 7, 8);
      findNumberTwo(setPuzzleNumbers, solvedNumbers, solvedSegments);
      findSegmentOne(setPuzzleNumbers, solvedNumbers, solvedSegments);
      findNumbersThreeAndFive(setPuzzleNumbers, solvedNumbers, solvedSegments);
      findNumbersZeroSixAndNine(setPuzzleNumbers, solvedNumbers, solvedSegments);
      output += readLastFour(oneLine, solvedNumbers);
    }
    return output;
  }

  private static int readLastFour(List<String> oneLine, String[] solvedNumbers) {
    int rowLength = oneLine.size();
    StringBuilder assembleNumber = new StringBuilder();
    for (int row = rowLength - 4; row < rowLength; row++) {
      for (int num = 0; num < 10; num++) {
        if (oneLine.get(row).equals(solvedNumbers[num])) {
          assembleNumber.append(num);
        }
      }
    }
    return Integer.parseInt(assembleNumber.toString());
  }

  private static void findNumbersZeroSixAndNine(Set<String> setPuzzleNumbers, String[] solvedNumbers, String[] solvedSegments) {
    List<String> sixLengthPuzzlePieces = setPuzzleNumbers.stream().filter(s -> s.length() == 6).toList();
    String[] lettersInFive = solvedNumbers[5].split("(?!^)");
    for (String sixLength : sixLengthPuzzlePieces) {
      // if its number six:
      if (!sixLength.contains(solvedSegments[2])) {
        solvedNumbers[6] = sixLength;
      } else {
        // its 0 or 9
        boolean containsAllFromFive = true;
        // need to find number four
        for (String fiveLetter : lettersInFive) {
          if (!sixLength.contains(fiveLetter)) {
            containsAllFromFive = false;
          }
        }
        if (containsAllFromFive) {
          solvedNumbers[9] = sixLength;
        } else {
          solvedNumbers[0] = sixLength;
        }
      }
    }
  }

  private static void findNumbersThreeAndFive(Set<String> setPuzzleNumbers, String[] solvedNumbers, String[] solvedSegments) {
    List<String> fiveLengthPuzzlePieces = setPuzzleNumbers.stream().filter(s -> s.length() == 5).toList();

    for (String fiveLength : fiveLengthPuzzlePieces) {
      // if its already on the list, skip
      if (!fiveLength.equals(solvedNumbers[2])) {
        // if contains segment one, its a 5, if not, its a 3
        if (fiveLength.contains(solvedSegments[1])) {
          solvedNumbers[5] = fiveLength;
        } else {
          solvedNumbers[3] = fiveLength;
        }
      }
    }
  }

  private static void findSegmentOne(Set<String> setPuzzleNumbers, String[] solvedNumbers, String[] solvedSegments) {
    String[] lettersInTwo = solvedNumbers[2].split("(?!^)");

    // a b c d e f g
    // 0 1 2 3 4 5 6
    int[] alpha = new int[7];

    for (String letter : lettersInTwo) {
      if (letter.contains("a")) {
        alpha[0]++;
      }
      if (letter.contains("b")) {
        alpha[1]++;
      }
      if (letter.contains("c")) {
        alpha[2]++;
      }
      if (letter.contains("d")) {
        alpha[3]++;
      }
      if (letter.contains("e")) {
        alpha[4]++;
      }
      if (letter.contains("f")) {
        alpha[5]++;
      }
      if (letter.contains("g")) {
        alpha[6]++;
      }
    }
    int[] empties = new int[2];
    int index = 0;
    for (int i = 0; i < 7; i++) {
      if (alpha[i] < 1) {
        empties[index] = i;
        index++;
      }
    }
    String[] emptyVals = new String[2];
    emptyVals[0] = convertNumToLetter(empties[0]);
    emptyVals[1] = convertNumToLetter(empties[1]);

    if (emptyVals[0] == solvedSegments[5]) {
      solvedSegments[1] = emptyVals[1];
    } else {
      solvedSegments[1] = emptyVals[0];
    }
  }

  private static void findSegmentTwo(Set<String> numberSet, String[] solvedNumbers, String[] solvedSegments) {
    String[] letterArray = solvedNumbers[1].split("(?!^)");
    if (letterArray[1].equals(solvedSegments[5])) {
      solvedSegments[2] = letterArray[0];
    } else {
      solvedSegments[2] = letterArray[1];
    }
  }

  private static void findNumberTwo(Set<String> numberSet, String[] solvedNumbers, String[] solvedSegments) {
    for (String number : numberSet) {
      if (!number.contains(solvedSegments[5])) {
        solvedNumbers[2] = number;
      }
    }
  }


  private static void findNumberByStringLength(Set<String> numberSet, String[] solvedNumbers, int length, int whatNumber) {
    for (String number : numberSet) {
      if (number.length() == length) {
        solvedNumbers[whatNumber] = number;
        break;
      }
    }
  }


  private static void findSegmentFive(Set<String> numberSet, String[] solvedSegments) {

    // a b c d e f g
    // 0 1 2 3 4 5 6
    int[] alpha = new int[7];

    for (String letter : numberSet) {
      if (letter.contains("a")) {
        alpha[0]++;
      }
      if (letter.contains("b")) {
        alpha[1]++;
      }
      if (letter.contains("c")) {
        alpha[2]++;
      }
      if (letter.contains("d")) {
        alpha[3]++;
      }
      if (letter.contains("e")) {
        alpha[4]++;
      }
      if (letter.contains("f")) {
        alpha[5]++;
      }
      if (letter.contains("g")) {
        alpha[6]++;
      }
    }

    for (int compare = 0; compare < alpha.length; compare++) {
      if (alpha[compare] == (numberSet.size() - 1)) {
        // digit 5 is whatever index we have
        solvedSegments[5] = convertNumToLetter(compare);
      }
    }
  }

  private static String convertNumToLetter(int input) {
    if (input == 0) {
      return "a";
    } else if (input == 1) {
      return "b";
    } else if (input == 2) {
      return "c";
    } else if (input == 3) {
      return "d";
    } else if (input == 4) {
      return "e";
    } else if (input == 5) {
      return "f";
    } else {
      return "g";
    }
  }

  public static String sortString(String inputString)
  {
    // Converting input string to character array
    char[] tempArray = inputString.toCharArray();

    // Sorting temp array using
    Arrays.sort(tempArray);

    // Returning new sorted string
    return new String(tempArray);
  }
}
package tv.grimli.y2021.d04;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2021d4 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d04/input.txt");

    String callString = input.get(0);
    String[] callStringArray = callString.split(",");
    List<Integer> calls = new ArrayList<>();
    for (String s : callStringArray) {
      calls.add(Integer.parseInt(s));
    }

    int whichBingoBoard = -1;
    int whichRowBingo = 0;

    List<int[][]> bingoBoards = new ArrayList<>();
    List<int[]> bingoTracker = new ArrayList<>();
    // loop to fill boards in matrix
    for (String in : input) {
      if (in.isEmpty()) {
        // creating a board for the following five lines
        int[][] bingoBoard = new int[5][5];
        bingoBoards.add(bingoBoard);
        // creating the tracker to detect bingos
        int[] tracker = new int[10];
        bingoTracker.add(tracker);
        // index for which bingo board about to fill
        whichBingoBoard++;
        // index for which part of the matrix to fill
        whichRowBingo = 0;
      } else if (in.length() < 20) {
        in = in.replace("  ", " ").strip();
        String[] stringRow = in.split(" ");
        for (int cell = 0; cell < 5; cell++) {
          bingoBoards.get(whichBingoBoard)[whichRowBingo][cell] = Integer.parseInt(stringRow[cell]);
        }
        whichRowBingo++;
      }
    }
    int totalBoards = bingoBoards.size();
    int currentCall = -1;

    // loop to fill boards in matrix
    for(int call : calls) {
      //need this to multiply if bingo
      currentCall = call;

      for (int board = 0; board < bingoBoards.size(); board++) {
        for (int column = 0; column < 5; column++) {
          for (int row = 0; row < 5; row++) {
            if (bingoBoards.get(board)[column][row] == call) {
              bingoBoards.get(board)[column][row] = -1;

              // set row tracker
              bingoTracker.get(board)[row] = bingoTracker.get(board)[row] + 1;
              // set column tracker
              bingoTracker.get(board)[column + 5] = bingoTracker.get(board)[column + 5] + 1;
              if (checkForBingo(bingoTracker.get(board))) {
                for (int r = 0; r < 10; r++) {
                  bingoTracker.get(board)[r] = 10;
                }
                totalBoards--;
              }
              if (totalBoards == 0) {
                return bingo(currentCall, bingoBoards.get(board), bingoBoards);
              }
            }
          }
        }
      }
    }
    System.out.println("");
    System.out.println("Fail. Don't use this:");
    return currentCall;
  }

  private static int bingo(int currentCall, int[][] board, List<int[][]> bingoBoards) {
    int output = 0;
    printBingoBoards(bingoBoards);
    System.out.println("Call: " + currentCall);
    for (int column = 0; column < 5; column++) {
      for (int row = 0; row < 5; row++) {
        if (board[column][row] > 0) {
          output += board[column][row];
        }
      }
    }
    System.out.println("output: " + output);
    return output * currentCall;
  }

  private static void printBingoBoards(List<int[][]> boards) {
    for (int[][] bingoBoard : boards) {
      System.out.println();
      for (int column = 0; column < 5; column++) {
        System.out.println();
        for (int row = 0; row < 5; row++) {
          System.out.print(bingoBoard[column][row] + " ");
        }
      }

    }
    System.out.println();
  }

  private static boolean checkForBingo(int[] cellTracker) {
    for (int cell : cellTracker) {
      if (cell == 5) {
        return true;
      }
    }
    return false;
  }
}

package tv.grimli.y2021.d09;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class y2021d09 {
  public static int run() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d09/input.txt");
    int[][] heightMap = new int[input.size()][input.get(0).length()];
    for(int row = 0; row < input.size(); row++) {
      String[] line = input.get(row).split("(?!^)");
      for(int col = 0; col < line.length; col++) {
        heightMap[row][col] = Integer.parseInt(line[col]);
      }
    }
    int output = 0;
    for(int row = 0; row < heightMap.length; row++) {
      for(int col = 0; col < heightMap[row].length; col++) {
        boolean isLowest = true;
        //TODO unknown what to do if they are equal
        if ((row - 1) >= 0) {
          // check up
          if (heightMap[row][col] >= heightMap[row - 1][col]) {
            isLowest = false;
          }
        }
        if ((row + 1) < heightMap.length) {
          // check down
          if (heightMap[row][col] >= heightMap[row + 1][col]) {
            isLowest = false;
          }
        }
        if ((col - 1) >= 0) {
          // check left
          if (heightMap[row][col] >= heightMap[row][col - 1]) {
            isLowest = false;
          }
        }
        if ((col + 1) < heightMap[row].length) {
          // check right
          if (heightMap[row][col] >= heightMap[row][col + 1]) {
            isLowest = false;
          }
        }
        if (isLowest) {
          output += (heightMap[row][col] + 1);
        }
      }
    }
    return output;
  }

  public static int runPartTwo() throws IOException {
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d09/input.txt");
    int[][] heightMap = new int[input.size()][input.get(0).length()];
    for(int row = 0; row < input.size(); row++) {
      String[] line = input.get(row).split("(?!^)");
      for(int col = 0; col < line.length; col++) {
        heightMap[row][col] = Integer.parseInt(line[col]);
      }
    }
    List<Coords> lowestPoints = new ArrayList<>();

    for(int row = 0; row < heightMap.length; row++) {
      for(int col = 0; col < heightMap[row].length; col++) {
        boolean isLowest = true;
        //TODO unknown what to do if they are equal
        if ((row - 1) >= 0) {
          // check up
          if (heightMap[row][col] >= heightMap[row - 1][col]) {
            isLowest = false;
          }
        }
        if ((row + 1) < heightMap.length) {
          // check down
          if (heightMap[row][col] >= heightMap[row + 1][col]) {
            isLowest = false;
          }
        }
        if ((col - 1) >= 0) {
          // check left
          if (heightMap[row][col] >= heightMap[row][col - 1]) {
            isLowest = false;
          }
        }
        if ((col + 1) < heightMap[row].length) {
          // check right
          if (heightMap[row][col] >= heightMap[row][col + 1]) {
            isLowest = false;
          }
        }
        if (isLowest) {
          Coords foundLow = new Coords(row, col);
          lowestPoints.add(foundLow);
        }
      }
    }
    //going to need to sort this object inside recursive
    List<Integer> basinSizes = new ArrayList<>();
    for (Coords lowPoint : lowestPoints) {
      basinSizes.add(countSpaces(lowPoint, heightMap));
    }
    // sort basinsizes
    Collections.sort(basinSizes);
    System.out.println(basinSizes);

    // get the three biggest
    // muliply them to return output

    return (basinSizes.get(basinSizes.size() - 1)
            * basinSizes.get(basinSizes.size() - 2)
            * basinSizes.get(basinSizes.size() - 3));
  }

  private static Integer countSpaces(Coords lowPoint, int[][] heightMap) {
    heightMap[lowPoint.row()][lowPoint.col()] = 9;
    int output = 0;
    if ((lowPoint.row() - 1) >= 0) {
      // check up
      if (heightMap[lowPoint.row() - 1][lowPoint.col()] != 9) {
        Coords nextPoint = new Coords(lowPoint.row() - 1, lowPoint.col());
        output += countSpaces(nextPoint, heightMap);
      }
    }
    if ((lowPoint.row() + 1) < heightMap.length) {
      // check down
      if (heightMap[lowPoint.row() + 1][lowPoint.col()] != 9) {
        Coords nextPoint = new Coords(lowPoint.row() + 1, lowPoint.col());
        output += countSpaces(nextPoint, heightMap);
      }
    }
    if ((lowPoint.col() - 1) >= 0) {
      // check left
      if (heightMap[lowPoint.row()][lowPoint.col() - 1] != 9) {
        Coords nextPoint = new Coords(lowPoint.row(), lowPoint.col() - 1);
        output += countSpaces(nextPoint, heightMap);
      }
    }
    if ((lowPoint.col() + 1) < heightMap[lowPoint.row()].length) {
      // check right
      if (heightMap[lowPoint.row()][lowPoint.col() + 1] != 9) {
        Coords nextPoint = new Coords(lowPoint.row(), lowPoint.col() + 1);
        output += countSpaces(nextPoint, heightMap);
      }
    }
    return 1 + output;
  }
}
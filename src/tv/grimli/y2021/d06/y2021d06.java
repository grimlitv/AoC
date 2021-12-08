package tv.grimli.y2021.d06;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class y2021d06 {

  private static int days = 256;

  public static int run() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d06/sample.txt").get(0);
    String[] fishIn = input.split(",");
    List<Integer> fishArray = new ArrayList<>();
    for (String in : fishIn) {
      fishArray.add(Integer.parseInt(in));
    }
    System.out.println(fishArray);

    for (int day = 0; day < days; day++) {
      for (int whichFish = 0; whichFish < fishArray.size(); whichFish++) {
        if (fishArray.get(whichFish) == 0) {
          //TODO this is not quite what they asked for, maybe need to fix later
          fishArray.add(9);
          fishArray.set(whichFish, 6);
        } else {
          fishArray.set(whichFish, fishArray.get(whichFish) - 1);
        }
      }
    }

    return fishArray.size();
  }

  public static long runPartTwo() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d06/input.txt").get(0);
    String[] fishIn = input.split(",");
    List<Integer> fishArray = new ArrayList<>();
    for (String in : fishIn) {
      fishArray.add(Integer.parseInt(in));
    }
    System.out.println(fishArray);

    long[] fishAtAges = new long[9];

    // setup inital ages
    for (int whichFish : fishArray){
      fishAtAges[whichFish] += 1;
    }
    System.out.println(Arrays.toString(fishAtAges));

    for (int day = 0; day < days; day++) {
      long[] tempAgeCount = Arrays.copyOf(fishAtAges, 9);
      fishAtAges[8] = tempAgeCount[0];
      fishAtAges[7] = tempAgeCount[8];
      fishAtAges[6] = tempAgeCount[7] + tempAgeCount[0];
      fishAtAges[5] = tempAgeCount[6];
      fishAtAges[4] = tempAgeCount[5];
      fishAtAges[3] = tempAgeCount[4];
      fishAtAges[2] = tempAgeCount[3];
      fishAtAges[1] = tempAgeCount[2];
      fishAtAges[0] = tempAgeCount[1];
    }
    long output = 0;
    for (long out : fishAtAges) {
      output += out;
    }

    return output;
  }
}

package tv.grimli.y2021.d07;

import tv.grimli.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class y2021d07 {
  public static int run() throws IOException {
    // read from the text file
    String input = ReadFromFile.stringStreams("src/tv/grimli/y2021/d07/input.txt").get(0);
    String[] crabString = input.split(",");
    List<Integer> crabLocations = new ArrayList<>();
    for (String in : crabString) {
      crabLocations.add(Integer.parseInt(in));
    }
    int testLocation = 0;
    int leastFuel = Integer.MAX_VALUE;
    int trialFuel = 0;
    boolean haventFoundLeastFuel = true;
    while (haventFoundLeastFuel) {
      System.out.println("Trial: " + testLocation);
      trialFuel = 0;
      for (int crabToMove : crabLocations) {
        int howFar = Math.abs(crabToMove - testLocation);
        int oneCrabFuelConsumption = 0;
        for (int step = 1; step <= howFar; step++) {
          oneCrabFuelConsumption += step;
          //System.out.println("Consumed " + oneCrabFuelConsumption + " fuel");
        }
        trialFuel += oneCrabFuelConsumption;
      }
      System.out.println("Trial " + testLocation + " fuel: " + trialFuel);

      if (trialFuel < leastFuel) {
        leastFuel = trialFuel;
        testLocation++;
      } else {
        haventFoundLeastFuel = false;
      }
    }
    return leastFuel;
  }
}

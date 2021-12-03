package tv.grimli;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFromFile {
  public static ArrayList<Integer> intBufferedReader(String where) throws IOException {
    boolean looping = true;
    Path path = Paths.get(where);
    ArrayList<Integer> output = new ArrayList<>();
    BufferedReader reader = Files.newBufferedReader(path);
    while (looping) {
      String line = reader.readLine();
      if (line == null) {
        looping = false;
      } else {
        output.add(Integer.parseInt(line));
      }
    }
    reader.close();
    return output;
  }

  public static ArrayList<String> stringBufferedReader(String where) throws IOException {
    boolean looping = true;
    Path path = Paths.get(where);
    ArrayList<String> output = new ArrayList<>();
    BufferedReader reader = Files.newBufferedReader(path);
    while (looping) {
      String line = reader.readLine();
      if (line == null) {
        looping = false;
      } else {
        output.add(line);
      }
    }
    reader.close();
    return output;
  }

  public static List<String> stringStreams(String where) throws IOException {
    Path path = Paths.get(where);

    Stream<String> lines = Files.lines(path);
    List<String> stringOutput = lines.collect(Collectors.toList());
    lines.close();
    return stringOutput;
  }

  public static ArrayList<Integer> intStreams(String where) throws IOException {
    Path path = Paths.get(where);

    Stream<String> lines = Files.lines(path);
    List<String> stringOutput = lines.collect(Collectors.toList());
    lines.close();
    return convertStringToInt(stringOutput);
  }

  private static ArrayList<Integer> convertStringToInt(List<String> input) {
    ArrayList<Integer> output = new ArrayList<>();
    for(String in : input) {
      output.add(Integer.parseInt(in));
    }
    return output;
  }

}

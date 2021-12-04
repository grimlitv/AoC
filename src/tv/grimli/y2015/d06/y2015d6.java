package tv.grimli.y2015.d06;

import tv.grimli.ReadFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class y2015d6 {

  public static int run() throws IOException {
    var useUi = false;
    var userInterface = useUi ? UserInterface.generateUi() : null;
    
    // read from the text file
    List<String> input = ReadFromFile.stringStreams("src/tv/grimli/y2015/d06/input.txt");
    List<List<Integer>> lightMap = buildLightMap();
    for (String instruction : input) {
      String[] insSet = instruction.split(" ");
      if (insSet[0].equals("toggle")) {
        int[] from = Arrays.stream(insSet[1].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] to = Arrays.stream(insSet[3].split(",")).mapToInt(Integer::parseInt).toArray();
        toggle(from, to, lightMap);
      } else {
        int[] from = Arrays.stream(insSet[2].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] to = Arrays.stream(insSet[4].split(",")).mapToInt(Integer::parseInt).toArray();
        if (insSet[1].equals("off")) {
          off(from, to, lightMap);
        } else {
          on(from, to, lightMap);
        }
      }
      if (userInterface != null)
        userInterface.update(lightMap);
    }

    return countLights(lightMap);
  }
  
  public static List<List<Integer>> toggle(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        lightMap.get(x).set(y, lightMap.get(x).get(y) + 2);
      }
    }
    return lightMap;
  }

  public static List<List<Integer>> on(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        lightMap.get(x).set(y, lightMap.get(x).get(y) + 1);
      }
    }
    return lightMap;
  }

  public static List<List<Integer>> off(int[] from, int[] to, List<List<Integer>> lightMap) {
    for (int x = from[0]; x <= to[0]; x++) {
      for (int y = from[1]; y <= to[1]; y++) {
        // toggle the map at this point
        int curBright = lightMap.get(x).get(y);
        if (curBright >= 1) {
          lightMap.get(x).set(y, curBright - 1);
        }
      }
    }
    return lightMap;
  }

  public static int countLights(List<List<Integer>> lightMap) {
    int output = 0;
    for (int x = 0; x <= 999; x++) {
      for (int y = 0; y <= 999; y++) {
        // toggle the map at this point
        output += lightMap.get(x).get(y);
      }
    }
    return output;
  }

  public static List<List<Integer>> buildLightMap() {
    List<List<Integer>> lightMap = new ArrayList<>();;
    for (int x = 0; x < 1000; x++) {
      List<Integer> insert = new ArrayList<>();
      for (int y = 0; y < 1000; y++) {
        insert.add(0);
      }
      lightMap.add(insert);
    }
    return lightMap;
  }
  
  private static class UserInterface
  {
    private static final int initialDelay = 1_000;
    private static final int refreshDelay = 40;
    private static final int totalSize = 1_000;
    private static final int lightMultiplier = 5;
    
    public static UserInterface generateUi()
    {
      var frame = new JFrame("display");
      var contentPane = new ImagePanel();
      var size = new Dimension(totalSize, totalSize);
      contentPane.setMinimumSize(size);
      contentPane.setMaximumSize(size);
      contentPane.setPreferredSize(size);
      frame.setContentPane(contentPane);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      frame.setVisible(true);
      try
      {Thread.sleep(initialDelay);}
      catch (InterruptedException e)
      {}
      
      return new UserInterface(frame, contentPane);
    }
    
    private final JFrame frame;
    private final ImagePanel contentPane;
    
    private UserInterface(JFrame frame, ImagePanel contentPane)
    {
      this.frame = frame;
      this.contentPane = contentPane;
    }
    
    public void update(List<List<Integer>> lights)
    {
      if (frame.isVisible())
      {
        update(generateImage(lights));
      }
    }
    
    public void update(Image image)
    {
      if (frame.isVisible())
      {
        contentPane.setImage(image);
        frame.invalidate();
        frame.repaint();
        try
        {Thread.sleep(refreshDelay);}
        catch (InterruptedException e)
        {}
      }
    }
    
    private Image generateImage(List<List<Integer>> lights)
    {
      var image = new BufferedImage(totalSize, totalSize, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < totalSize; x++)
      {
        for (int y = 0; y < totalSize; y++)
        {
          var lightCode = Math.min(255, lights.get(x).get(y) * lightMultiplier);
          image.setRGB(x, y, new Color(lightCode, lightCode, lightCode).getRGB());
        }
      }
      return image;
    }
    
    private static class ImagePanel extends JPanel
    {
      private Image image;
      
      public void setImage(Image image)
      {
        this.image = image;
      }
      
      @Override
      public void paintComponent(Graphics graphics)
      {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, totalSize, totalSize);
        if (image != null)
        {
          graphics.drawImage(image, 0, 0, this);
        }
      }
    }
  }
}

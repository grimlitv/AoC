package tv.grimli.y2021.d17;

import java.io.IOException;


public class y2021d17 {
  //target area: x=117..164, y=-140..-89
  private static final int minX = 117;
  private static final int maxX = 164;
  private static final int minY = -140;
  private static int highestY = 0;
  private static final int maxY = -89;

  public static int run() throws IOException {
    int x = 0;
    int y = 0;
    int tryY = 0;
    for (int i = 130; i < 150; i++){
      System.out.println();
      System.out.println("i = " + i);
      y = 0;
      tryY = i;
      System.out.print("Y = 0 ");
      while (y > minY) {
        y = y + tryY;
        System.out.print(y + " ");
        tryY -= 1;
      }
      System.out.println();
    }
    //permute(x, y);
    return y;
  }
//
//  private static void permute(int x, int y) {
//    boolean lookingForSolution = true;
//    while (lookingForSolution) {
//      x++;
//      while (lookingForSolution) {
//        y++;
//        lookingForSolution = tryThis(x,y);
//      }
//    }
//  }
//
//  private static boolean tryThis(int x, int y) {
//    int currentX = x;
//    int currentY = y;
//    int tryX = x;
//    int tryY = y;
//
//    // while x outside of the target area &&
//    // while y is outside the target area
//    // let the missile continue moving (step it forward)
//    int missleX = 0;
//    int missleY = 0;
//    boolean outOfRange = false;
//    while (!outOfRange) {
//      missleX = currentX + tryX;
//      missleY = currentY + tryY;
//      tryY -= 1;
//      tryX -= 1;
//      if (missleX < minX) {
//        // can continue unless x = 0
//      }
//
//    }
//
//    // check to see which side of the target it is,
//    // if its too far or underneath, return
//    // if it hasn't gotten closer, return.
//  }

}
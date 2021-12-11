package tv.grimli.y2015.d11;

import java.io.IOException;

public class y2015d11 {

  private static int[] tries = new int[]{0,0,0,0,0,0,0,0};

  public static String run() throws IOException {
    // read from the text file
    String input = "abcdefgh"; // abcdffaa
    //String input = "ghijklmn"; // ghjaabcc
    //String input = "hxbxwxba"; // puzzle input
    int[] password = new int[8];
    char[] inchar = input.toCharArray();
    for(int i = 0; i < input.length(); i++) {
      password[i] = (int)inchar[i];
    }

    boolean needPass = true;
    while (needPass) {
      nextPassword(password);
      needPass = !checkPassword(password);
    }

    return getPassword(password);
  }

  private static boolean checkPassword(int[] password) {
    if (!checkForIOL(password)) {
      return false;
    } else if (!checkThreeInRow(password)) {
      return false;
    } else return checkForDoubles(password);
  }

  private static boolean checkForIOL(int[] password) {
    for (int i = 1; i < password.length; i++) {
      if ((password[i] == 105) || (password[i] == 111) || (password[i] == 108)) {
        //TODO maybe use this check to make it run less loops
        return false;
      }
    }
    return true;
  }

  private static boolean checkForDoubles(int[] password) {
    int numOfDoubles = 0;
    for (int i = 1; i < password.length; i++) {
      int first = password[i - 1];
      int second = password[i];
      if (((first) == second)) {
        numOfDoubles++;
        if (numOfDoubles == 2) {
          return true;
        }
        i++;
      }
    }
    return false;
  }

  private static boolean checkThreeInRow(int[] password) {
    for (int i = 2; i < password.length; i++) {
      int first = password[i - 2];
      int second = password[i - 1];
      int third = password[i];
      if (((first - 2) == third) && ((first - 1) == second)) {
        return true;
      }
    }
    return false;
  }

  private static void nextPassword(int[] password) {
    for(int i = tries.length - 1; i >= 0; i--) {
      tries[i]++;
      pushPassIndex(password, i);
      System.out.println(getPassword(password));
      if (tries[i] == 26) {
        tries[i] = 0;
      } else {
        return;
      }
    }
    //has tried every combination
  }

  private static String getPassword(int[] password) {
    StringBuilder output = new StringBuilder();
    for(int i = 0; i < password.length; i++) {
      output.append((char)password[i]);
    }
    return output.toString();
  }

  private static void pushPassIndex(int[] password, int i) {
    if (password[i] == 122) {
      password[i] = 97;
    } else {
      password[i] += 1;
    }
  }
}


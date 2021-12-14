package tv.grimli.y2015.d11;

import java.io.IOException;

public class y2015d11 {

  public static String run() throws IOException {
    // read from the text file
    //String input = "abcdefgh"; // abcdffaa
    //String input = "ghijklmn"; // ghjaabcc
    //String input = "hxbxwxba"; // puzzle input
    String input = "hxbxxyzz"; // part2 input
    int[] password = new int[8];
    char[] inchar = input.toCharArray();
    for(int i = 0; i < input.length(); i++) {
      password[i] = (int)inchar[i];
    }
    clearIOLOnce(password);
    boolean needPass = true;
    while (needPass) {
      nextPassword(password);
      needPass = checkPassword(password);
    }

    return getPassword(password);
  }

  private static boolean checkPassword(int[] password) {
    if (checkForRun(password)) {
      return checkForDoubles(password);
    }
    return true;
  }

  private static boolean checkForDoubles(int[] password) {
    //checks for doubles
    for (int i = 1; i < password.length; i++) {
      if (password[i - 1] == password[i]) {
        // we have one double, check for another at next index
        for (int j = i + 2; j < password.length; j++) {
          if (password[j - 1] == password[j]) {
            // we have two doubles
            return false;
          }
        }
      }
    }
    return true;
  }

  private static boolean checkForRun(int[] password) {
    for (int i = 2; i < password.length; i++) {
      int first = password[i - 2];
      int second = password[i - 1];
      int third = password[i];

      if (((first + 1) == second) && ((second + 1) == third)) {
        return true;
      }
    }
    return false;
  }

  private static void nextPassword(int[] password) {
    for(int i = password.length - 1; i >= 0; i--) {
      pushPassIndex(password, i);
      if (!(password[i] == 97)) {
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

  private static void clearIOLOnce(int[] password) {
    for(int i = 0; i < password.length; i++) {
      if ((password[i] == 105) || (password[i] == 111) || (password[i] == 108)) {
        password[i] += 1;
        for (int j = i; j < password.length; j++) {
          if (j + 1 < password.length) {
            password[j + 1] = 97;
          }
        }
        break;
      }
    }
  }

  private static void pushPassIndex(int[] password, int i) {
    if (password[i] == 122) {
      password[i] = 97;
    } else {
      password[i] += 1;
    }
    // NO I/O/L
    if ((password[i] == 105) || (password[i] == 111) || (password[i] == 108)) {
      password[i] += 1;
    }
  }
}


package tv.grimli.y2015.d04;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class y2015d4 {
  public static int run() throws IOException, NoSuchAlgorithmException {
    String input = "ckczppom";
    int trial = 0;
    while (true) {
      String a = input + trial;
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.reset();
      m.update(a.getBytes());
      byte[] digest = m.digest();
      BigInteger bigInt = new BigInteger(1, digest);
      String hashtext = bigInt.toString(16);
      if (hashtext.length() < 28) {
        return trial;
      }
      trial++;
    }
  }

  public static int runPartTwo() throws IOException, NoSuchAlgorithmException {
    String input = "ckczppom";
    int trial = 0;
    int count =0;
    while (true) {
      count++;
      String a = input + trial;
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.reset();
      m.update(a.getBytes());
      byte[] digest = m.digest();
      BigInteger bigInt = new BigInteger(1, digest);
      String hashtext = bigInt.toString(16);
      if (hashtext.length() < 27) {
        System.out.println();
        System.out.println(count);
        System.out.println();
        return trial;
      }
      trial++;
    }
  }
}

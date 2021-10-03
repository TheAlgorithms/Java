package Maths;

import java.util.Random;

public class AbsoluteValue {

  public static void main(String[] args) {
    Random random = new Random();

    /* test 1000 random numbers */
    for (int i = 1; i <= 1000; ++i) {
      int randomNumber = random.nextInt();
      assert absVal(randomNumber) == Math.abs(randomNumber);
    }
  }


  public static int absVal(int value) {
    return Math.abs(value);
  }
}

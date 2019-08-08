package Maths;

/**
 * @author PatOnTheBack
 */

 public class AbsoluteValue {

    public static void main(String[] args) {

        int value = -34;

        System.out.println("The absolute value of " + value + " is " + abs_val(value));

    }

    public static int abs_val(int value) {
      // If value is less than zero, make value positive.
      if (value < 0) {
        return -value;
      }

      return value;

    }

 }

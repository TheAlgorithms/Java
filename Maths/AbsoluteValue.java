package Maths;
import java.util.*;

/**
 * @author PatOnTheBack
 */

public class AbsoluteValue {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int value = scn.nextInt();
        System.out.println("The value of " + value + " is " + absVal(value));
    }

    /**
     * If value is less than zero, make value positive.
     *
     * @param value a number
     * @return the absolute value of a number
     */
    public static int absVal(int value) {
        return value < 0 ? -value : value;
    }

}

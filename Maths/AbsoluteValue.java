package Maths;
import java.util.Scanner;

/**
 * @author PatOnTheBack
 */

public class Algorithms {

    public static void main(String[] args) {
        //int value = -34; looks like it's useless
        Scanner f = new Scanner(System.in);
        System.out.println("Enter some value:  ");
        double value = f.nextDouble(); // greater capacity w 64 bit IEEE and it wil still work fine
        System.out.println("The absolute value of " + value + " is " + absVal(value)); // is this really an algorithm
    }

    /**
     * If value is less than zero, make value positive.
     *
     * @param value a number
     * @return the absolute value of a number
     */
    public static int absVal(int value) {
        return value > 0 ? value : -value;
    }

}

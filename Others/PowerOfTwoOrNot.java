package Others;

import java.util.Scanner;

/**
 * A utility to check if a given number is power of two or not.
 * For example 8,16 etc.
 */

public class PowerOfTwoOrNot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = sc.nextInt();
        boolean isPowerOfTwo = checkIfPowerOfTwoOrNot(num);
        if (isPowerOfTwo) {
            System.out.println("Number is a power of two");
        } else {
            System.out.println("Number is not a power of two");
        }
        sc.close();
    }


    /**
     * Checks whether given number is power of two or not.
     *
     * @param number
     * @return boolean
     */
    public static boolean checkIfPowerOfTwoOrNot(int number) {
        return number != 0 && ((number & (number - 1)) == 0);
    }
}

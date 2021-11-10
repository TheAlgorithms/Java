package com.thealgorithms.maths;

/**
 * A number is said to be an Automorphic, if it is present in the last digit(s)
 * of its square. Example- Let the number be 25, its square is 625. Since,
 * 25(The input number) is present in the last two digits of its square(625), it
 * is an Automorphic Number.
 */
import java.io.*;

public class AutomorphicNumber {

    //returns True if the number is a Automorphic number and False if it is not an Automorphic number
    public static boolean isAutomorphic(int n) {
        int m, c, r, p, k;
        c = 0;
        /**
         * m = Temporary variable to store a copy of the number entered by the
         * user. n = The number entered by the user c = Count the digits of the
         * number entered by user. p = To calculate the square of the number. k
         * = Support variable to count the digits of the number
         */
        double s;
        m = n;
        p = m * m; //Calculating square of the number
        do {
            k = n / 10;
            c = c + 1; //Counting the digits of the number entered by user.
            n = k;
        } while (n != 0);
        s = Math.pow(10, c);
        r = p % (int) s;
        if (m == r) //Checking if the original number entered is present at the end of the square
        {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if number is Automorphic Number or Not 1) Input - Enter a
     * Number: 25 Output - It is an Automorphic Number. 2) Input - Enter a
     * Number: 7 Output - It is not an Automorphic Number.
     */
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a Number: ");
        int n = Integer.parseInt(br.readLine());
        if (isAutomorphic(n)) {
            System.out.println("It is an Automorphic Number.");
        } else {
            System.out.println("It is not an Automorphic Number.");
        }
    }
}

/**
 * A number is said to be an Automorphic, if it is present in the last digit(s) of its square.
 * Example- Let the number be 25, its square is 625.
 *          Since, 25(The input number) is present in the last two digits of its square(625),
 *          it is an Automorphic Number.
 */
import java.util.*;
public class AutomorphicNumber
{
    /** Method to check if number is Automorphic Number or Not
     *  1) Input  - Enter a Number: 25
     *     Output - It is an Automorphic Number.
     *  2) Input  - Enter a Number: 7
     *     Output - It is not an Automorphic Number.
     */
    public static void main(String args[])
    {
        Scanner obj= new Scanner(System.in);
        int m, n, c, r, p, k; c = 0;
        /** m = Temporary variable to store a copy of the number entered by the user.
         * n = The number entered by the user
         * c = Count the digits of the number entered by user.
         * p = To calculate the square of the number.
         * k = Support variable to count the digits of the number
         */
        double s;
        System.out.println("Enter a Number:");
        n = obj.nextInt();
        m = n;
        p = m * m; //Calculating square of the number
        do
        {
            k = n / 10;
            c = c + 1; //Counting the digits of the number entered by user.
            n = k;
        }
        while(n != 0);
        s = Math.pow(10, c); 
        r = p %(int)s;
        if(m == r) //Checking if the original number entered is present at the end of the square
        {
            System.out.println("It is an Automorphic Number.");
        }
        else
        {
            System.out.println("It is not an Automorphic Number.");
        }
    }
}

package Maths;
import java.util.*; //for importing scanner

public class Factorial {
    public static void main(String[] args) { //main method
           int n = 1;
           Scanner sc= new Scanner(System.in);
           System.out.println("Enter Number");
           n=sc.nextInt();
           System.out.println(n + "! = " + factorial(n));
    }

    //Factorial = n! = n1 * (n-1) * (n-2)*...1

    /**
     * Calculate factorial N
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new ArithmeticException("n < 0");     //Dont work with less than 0
        }
        long fac = 1;
        for (int i = 1; i <= n; ++i) {
            fac *= i;
        }
        return fac; //Return factorial
    }
}

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
        // Using recursion
        try {
        	if (n == 0) {
				return 1; // if n = 0, return factorial of n;
			}else {
				return n*factorial(n-1); // While N is greater than 0, call the function again, passing n-1 (Principle of factoring);
			}
        }catch (ArithmeticException e) {
			System.out.println("Dont work with less than 0");
		}
		return n;
    }
}

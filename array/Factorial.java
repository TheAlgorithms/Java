package array;

import array.security.ProjectAlgorithmsException;
import array.security.ProjectAlgorithmsScanner;

public class Factorial {
    public static void main(String[] args) {
        //Prompt user to enter integer
        System.out.print("Enter a non-negative integer: ");

        int number;
        //Proceed with factorial calculation only if inputted number is not negative
        while (true) {
            try {
                number = ProjectAlgorithmsScanner.getIntegerWithException();
                break;
            } catch (ProjectAlgorithmsException ex) {
                System.err.println(ex.getMessage());
            }
        }

        //Output of factorial for any non-negative number
        System.out.println("The factorial of " + number + " will yield: " + factorial(number));

    }

    //Factorial method
    public static long factorial(int n) {

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }

    }
}

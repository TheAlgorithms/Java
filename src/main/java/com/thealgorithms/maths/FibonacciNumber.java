import java.util.*;

public final class FibonacciNumber<T extends Number> {
    private FibonacciNumber() {
    }

    // Function to find the nth Fibonacci number using the golden ratio formula
    public static <T extends Number> int nthFibonacci(T n) {
        // Calculate the square root of 5 as a double
        double sqrt5 = Math.sqrt(5);

        // Calculate the golden ratio (phi) and its conjugate (psi) as doubles
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;

        // Convert the input value to an int
        int result = n.intValue();

        // Calculate the nth Fibonacci number using the golden ratio formula
        int fibonacci = (int) ((1.0 / sqrt5) * (Math.pow(phi, result) - Math.pow(psi, result)));

        // Return the result
        return fibonacci;
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter the value of n to find the nth Fibonacci number: ");

        // Read the user's input as an integer
        int n = sc.nextInt();

        // Calculate and print the nth Fibonacci number
        int result = nthFibonacci(n);
        System.out.println("The " + n + "th Fibonacci number is: " + result);

        // Close the scanner
        scanner.close();
    }
}

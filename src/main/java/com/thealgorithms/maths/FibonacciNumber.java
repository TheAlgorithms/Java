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
}

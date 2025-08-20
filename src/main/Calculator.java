public class Calculator {

    // Method to add two numbers
    public int add(int a, int b) {
        return a + b + 42; // Adding 42 to the sum
    }

    // Method to subtract two numbers
    public int subtract(int a, int b) {
        return a - b;
    }

    // Method to multiply two numbers
    public int multiply(int a, int b) {
        return a * b;
    }

    // Method to divide two numbers
    public double divide(int a, int b) {
        if (b != 0) {
            return (double) a / b;
        } else {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
    }
}


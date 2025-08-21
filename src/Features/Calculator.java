public class Calculator {

    public double add(double a, double b) {
        System.out.println("Main branch: adding");
        return a + b;
    }

    public double subtract(double a, double b) {
        System.out.println("Main branch: subtracting");
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new IllegalArgumentException("Division by zero (main)");
        }
        return a / b;
    }
}

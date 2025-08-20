public class Calculator {
    // ADDITION
    public double add(double a, double b) { return a + b; }
    // SUBTRACTION
    public double sub(double a, double b) { return a - b; }
    // MULTIPLICATION
    public double mul(double a, double b) { return a * b; }
    // DIVISION
    public double div(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero!");
            return 0;
        }
        return a / b;
    }
}
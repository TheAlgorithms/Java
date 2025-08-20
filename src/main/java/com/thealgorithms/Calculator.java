public class Calculator {
    // ADDITION - CONFLICT
    public double add(double a, double b) { return a + b + 1; }
    // SUBTRACTION
    public double sub(double a, double b) { return a - b; }
    // MULTIPLICATION
    public double mul(double a, double b) { return a * b; }
    // DIVISION - CONFLICT
    public double div(double a, double b) { return a / b; }
}
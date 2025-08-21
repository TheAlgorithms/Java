public class Calculator {

    public double add(double a, double b) {
        System.out.println("Add: a=" + a + ", b=" + b);
        return a + b;
    }

    public double subtract(double a, double b) {
        System.out.println("Subtract: a=" + a + ", b=" + b);
        return a - b;
    }

    public double multiply(double a, double b) {
        System.out.println("Multiply: a=" + a + ", b=" + b);
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0.0) {
            throw new IllegalArgumentException("Division by zero");
        }
        System.out.println("Divide: a=" + a + ", b=" + b);
        return a / b;
    }
}
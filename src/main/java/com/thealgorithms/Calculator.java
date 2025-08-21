package com.thealgorithms.misc;

public class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
<<<<<<< HEAD
    public double divide(int a, int b) {
    if (b == 0) throw new ArithmeticException("Divide by zero!"); 
    return (double) a / b;
}
=======
    public double divide(int a, int b) { return (b != 0) ? (double)a / b : 0; }
>>>>>>> feature/calculator
}

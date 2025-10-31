/*
 * MathToolkit.java
 * A simple single-file Java project that provides a command-line math toolkit.
 * All user-facing text is in English as requested.
 *
 * Features:
 *  - Basic arithmetic (addition, subtraction, multiplication, division)
 *  - GCD and LCM
 *  - Factorial (uses BigInteger for large results)
 *  - Fibonacci (n-th, uses BigInteger)
 *  - Prime check (BigInteger.isProbablePrime)
 *  - Quadratic equation solver (real and complex roots)
 *
 * Usage:
 *  Compile: javac MathToolkit.java
 *  Run:     java MathToolkit
 *
 * Author: ChatGPT
 */

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MathToolkit {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to MathToolkit — a simple Java math project.");
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option (1-9) or 0 to exit: ");
            int choice = readInt();
            System.out.println();
            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    basicArithmetic();
                    break;
                case 2:
                    gcdLcm();
                    break;
                case 3:
                    factorial();
                    break;
                case 4:
                    fibonacci();
                    break;
                case 5:
                    primeCheck();
                    break;
                case 6:
                    quadraticSolver();
                    break;
                case 7:
                    integerOperationsDemo();
                    break;
                case 8:
                    about();
                    break;
                case 9:
                    help();
                    break;
                default:
                    System.out.println("Invalid option — please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== MathToolkit Menu ===");
        System.out.println("1) Basic arithmetic ( +, -, *, / )");
        System.out.println("2) GCD and LCM");
        System.out.println("3) Factorial (BigInteger)");
        System.out.println("4) Fibonacci (n-th term, BigInteger)");
        System.out.println("5) Prime check");
        System.out.println("6) Quadratic equation solver");
        System.out.println("7) Integer operations demo (safe long handling)");
        System.out.println("8) About this project");
        System.out.println("9) Help (short usage)");
        System.out.println("0) Exit");
    }

    private static void basicArithmetic() {
        System.out.println("-- Basic arithmetic --");
        System.out.print("Enter first number: ");
        double a = readDouble();
        System.out.print("Enter second number: ");
        double b = readDouble();
        System.out.println("Choose operator: +  -  *  /");
        System.out.print("Operator: ");
        String op = scanner.next();
        double result;
        switch (op) {
            case "+":
                result = a + b;
                System.out.printf("Result: %.10g\n", result);
                break;
            case "-":
                result = a - b;
                System.out.printf("Result: %.10g\n", result);
                break;
            case "*":
                result = a * b;
                System.out.printf("Result: %.10g\n", result);
                break;
            case "/":
                if (b == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                } else {
                    result = a / b;
                    System.out.printf("Result: %.10g\n", result);
                }
                break;
            default:
                System.out.println("Unknown operator.");
        }
    }

    private static void gcdLcm() {
        System.out.println("-- GCD and LCM --");
        System.out.print("Enter first integer (long): ");
        long a = readLong();
        System.out.print("Enter second integer (long): ");
        long b = readLong();
        long g = gcd(a, b);
        long l = lcm(a, b);
        System.out.println("GCD(" + a + ", " + b + ") = " + g);
        System.out.println("LCM(" + a + ", " + b + ") = " + l);
    }

    private static void factorial() {
        System.out.println("-- Factorial --");
        System.out.print("Enter a non-negative integer (n): ");
        int n = readNonNegativeInt();
        BigInteger fact = factorialBig(n);
        System.out.println(n + "! = " + fact.toString());
    }

    private static void fibonacci() {
        System.out.println("-- Fibonacci (n-th) --");
        System.out.print("Enter n (0-based index, non-negative): ");
        int n = readNonNegativeInt();
        BigInteger fib = fibonacciBig(n);
        System.out.println("Fibonacci(" + n + ") = " + fib.toString());
    }

    private static void primeCheck() {
        System.out.println("-- Prime check --");
        System.out.print("Enter integer to check (positive): ");
        long n = readLongPositive();
        BigInteger big = BigInteger.valueOf(n);
        boolean probablePrime = big.isProbablePrime(12); // 12 -> sufficiently small error probability
        if (probablePrime) {
            System.out.println(n + " is probably prime.");
        } else {
            System.out.println(n + " is composite (not prime).");
        }
    }

    private static void quadraticSolver() {
        System.out.println("-- Quadratic equation solver: ax^2 + bx + c = 0 --");
        System.out.print("Enter a (non-zero): ");
        double a = readDoubleNonZero();
        System.out.print("Enter b: ");
        double b = readDouble();
        System.out.print("Enter c: ");
        double c = readDouble();
        double disc = b * b - 4 * a * c;
        if (disc > 0) {
            double r1 = (-b + Math.sqrt(disc)) / (2 * a);
            double r2 = (-b - Math.sqrt(disc)) / (2 * a);
            System.out.printf("Two real roots: x1 = %.10g, x2 = %.10g\n", r1, r2);
        } else if (disc == 0) {
            double r = -b / (2 * a);
            System.out.printf("One real root: x = %.10g\n", r);
        } else {
            double real = -b / (2 * a);
            double imag = Math.sqrt(-disc) / (2 * a);
            System.out.printf("Two complex roots: x1 = %.10g + %.10gi, x2 = %.10g - %.10gi\n", real, imag, real, imag);
        }
    }

    private static void integerOperationsDemo() {
        System.out.println("-- Integer operations demo --");
        System.out.println("This demonstrates safe handling with long and BigInteger when needed.");
        System.out.print("Enter a long integer: ");
        long a = readLong();
        System.out.print("Enter another long integer: ");
        long b = readLong();
        System.out.println("Sum as long: " + (a + b));
        BigInteger bigSum = BigInteger.valueOf(a).add(BigInteger.valueOf(b));
        System.out.println("Sum as BigInteger: " + bigSum.toString());
    }

    private static void about() {
        System.out.println("MathToolkit — single-file Java project. All text is English.");
        System.out.println("Feel free to request additional features or a multi-file project structure.");
    }

    private static void help() {
        System.out.println("Help: Use options from the menu, input numbers when asked.");
        System.out.println("For large factorial or Fibonacci values, the program uses BigInteger.");
    }

    // --- Utility math functions ---

    private static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return Math.abs(a);
    }

    private static long lcm(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0 || b == 0) return 0;
        return Math.abs(a / gcd(a, b) * b);
    }

    private static BigInteger factorialBig(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    private static BigInteger fibonacciBig(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        BigInteger prev = BigInteger.ZERO, curr = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger next = prev.add(curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    // --- Robust input helpers ---

    private static int readInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Invalid integer — please enter again: ");
                scanner.next();
            }
        }
    }

    private static int readNonNegativeInt() {
        while (true) {
            int v = readInt();
            if (v < 0) System.out.print("Please enter a non-negative integer: ");
            else return v;
        }
    }

    private static long readLong() {
        while (true) {
            try {
                return scanner.nextLong();
            } catch (InputMismatchException ex) {
                System.out.print("Invalid long — please enter again: ");
                scanner.next();
            }
        }
    }

    private static long readLongPositive() {
        while (true) {
            long v = readLong();
            if (v <= 0) System.out.print("Please enter a positive integer: ");
            else return v;
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.print("Invalid number — please enter again: ");
                scanner.next();
            }
        }
    }

    private static double readDoubleNonZero() {
        while (true) {
            double v = readDouble();
            if (v == 0.0) System.out.print("Value cannot be zero — please enter again: ");
            else return v;
        }
    }
}

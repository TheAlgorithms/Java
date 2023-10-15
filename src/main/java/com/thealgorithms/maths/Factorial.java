package com.thealgorithms.maths;

public final class Factorial {
    public static void main(String[] args){
        System.out.println("Enter a Number to calculate Factorial :-> ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long fact = factorial(n);
        System.out.println("Factorial of "+n+" is "+fact);
    }

    /**
     * Calculate factorial N using iteration
     *
     * @param n the number
     * @return the factorial of {@code n}
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number cannot be negative");
        }
        long factorial = 1;
        for (int i = 1; i <= n; ++i) {
            factorial *= i;
        }
        return factorial;
    }
}

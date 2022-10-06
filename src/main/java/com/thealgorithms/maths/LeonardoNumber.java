package com.thealgorithms.maths;

public class LeonardoNumber {

    public static int leonardoNumber(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return (leonardoNumber(n - 1) + leonardoNumber(n - 2) + 1);
    }

    public static void main(String args[]) {
        for (int i = 0; i < 20; i++) {
            System.out.print(leonardoNumber(i) + " ");
        }
    }
}

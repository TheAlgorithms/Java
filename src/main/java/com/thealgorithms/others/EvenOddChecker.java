package com.thealgorithms.others;

public class EvenOddChecker {
    public static String checkEvenOdd(int number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }

    public static void main(String[] args) {
        int test = 7;
        System.out.println(test + " is " + checkEvenOdd(test));
    }
}

//package com.thealgorithms.bitmanipulation;

/**
 * Converts any Octal Number to a Binary Number
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsEven {
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }

public class Main {
    public static void main(String[] args) {
        int n = 10; // Replace 10 with the number you want to check
        boolean res = IsEven.isEven(n);

        if (res) {
            System.out.println(n + " is even.");
        } else {
            System.out.println(n + " is odd.");
        }
    }
}

}

package com.thealgorithms.bitmanipulation;

/**
 * Checks whether a number is even
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsEven {
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
    public static void main(String[] args) {
        int num = 42; // You can change this number to test other values
        if (isEven(num)) {
            System.out.println(num + " is even.");
        } 
        else {
            System.out.println(num + " is odd.");
        }
    }
}

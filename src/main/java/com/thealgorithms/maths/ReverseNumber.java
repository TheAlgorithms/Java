package com.thealgorithms.maths;

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReverseNumber {

    public static void main(String[] args) {
        int number;
        int reverse = 0;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter a number:");
            number = sc.nextInt();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("ERROR: Invalid input");
            return;
        }

        while (number != 0) {
            int remainder = number % 10;

            reverse = reverse * 10 + remainder;
            number = number / 10;
        }

        System.out.println("The reverse of the given number is: " + reverse);
    }
}

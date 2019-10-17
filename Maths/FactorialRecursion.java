package Maths;

import java.util.Scanner;

public class FactorialRecursion {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter an integer");
        System.out.println("Factorial is " + factorial(keyboard.nextInt()));
    }


    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else return n * factorial(n - 1);
    }
}

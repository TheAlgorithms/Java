package com.thealgorithms.maths;

import java.util.Scanner;

public class NeonNumber {

    /**
     * Check if a number is Neon Number.
     * A neon number is a number where a sum of digits of its square equals the number itself.
     * Example : 9--> 9^2 = 81  --> 8+1 = 9
     *
     * @param number the number to check
     * @return true if neon number, else --> false
      */

    public static boolean isNeon(int number)
    {
        int square = number*number;
        int digitSum = 0;
        while(square>0){
            digitSum += square % 10;
            square/= 10;
        }
        return digitSum == number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();

        if(isNeon(number)){
            System.out.println(number + " is a Neon Numner ");
        } else {
            System.out.println(number + " is not a Neon Number ");
        }
        scanner.close();
    }
}

 //
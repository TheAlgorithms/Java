package com.thealgorithms.bitmanipulation;

/**
 * Checks whether a number is even
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public final class IsEven {
    private IsEven() {
    }
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}

/**
 * Take input from user 
 * Checks whether a number is even
 * @Sarthak Bhushan
 */
import java.util.Scanner;
public static int void(String[] args){
    Scanner sc= new Scanner(System.in);
    System.out.print("Enter a number:");
    int number= sc.nextInt();
    if(number%2=0){
        System.out.print("Number is Even");
    }else{
        System.out.print("Test Fails");
    }
}

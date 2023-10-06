package com.thealgorithms.maths;

/**
 * This class checks whether a given number is an Armstrong number or not.
 * An Armstrong number is a number that is equal to the sum of its own digits,
 * each raised to the power of the number of digits.
 *
 * For example, 370 is an Armstrong number because 3^3 + 7^3 + 0^3 = 370.
 * 1634 is an Armstrong number because 1^4 + 6^4 + 3^4 + 4^4 = 1634.
 * An Armstrong number is often called a Narcissistic number.
 *
 * @author madhavagarwal3012
 */
public class Armstrong {

    /**
     * Checks whether a given number is an Armstrong number or not.
     *
     * @param number the number to check
     * @return {@code true} if the given number is an Armstrong number, {@code false} otherwise
     */
    public static void checkArmstrong(int n){
        int originalNumber=n;
        int length=0;
        while(n>0){
            length++;
            n=n/10;
        }
        n=originalNumber;
        int start=0;
        int sum=0;
        int digit=0;
        while(start<length){
            int cube=1;
            digit=n%10;
            int sub_start=0;
            while(sub_start<length){
                cube=digit*cube;
                sub_start++;
            }
            sum=sum+cube;
            n=n/10;
            start++;
        }
        if(sum==originalNumber){
            System.out.println("Its An Armstrong Number");
        }
        else{
            System.out.println("Its Not An Armstrong Number");
        }
        
    }
}

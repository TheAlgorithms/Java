import java.util.*;
package Maths;

/**
 * Armstrong number also known as Narcissistic number, Plus Perfect number
 * A positive integer number will be said to be an Armstrong number 
 * if the sum of each digit raised to the power of total digits present in the number
 * equlas to the original number.
 *
 * example: 153 = 1^3 + 5^3 + 3^3 
 *
 * @param number : user input
 */

class ArmstrongNumber {
    public static void main (String args[]) {
        Scanner sc = new Scanner (System.in);
        System.out.print("Enter number: ");
        long number = sc.nextLong();
        int countDigits = (int) Math.floor(Math.log10(number) + 1); // counting of digits present in the number
        long digitSum = 0, duplicate = number;
        
        while(duplicate > 0)
        {
            int n = (int) duplicate % 10;
            digitSum += Math.pow(n, countDigits);
            duplicate /= 10;  
        }

        if(number == digitSum)
            System.out.println(number + " is an Armstrong Number");
        else
            System.out.println(number + " is not an Armstrong Number");

        sc.close();
    }
}
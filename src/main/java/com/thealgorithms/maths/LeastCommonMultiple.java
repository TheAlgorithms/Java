package com.thealgorithms.maths;

/**
 * Find least common multiple of two numbers
 */

public class LeastCommonMultiple {
    public static void main(String args[]){
        
        int num1, num2, num3, high;
        int cmv = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter first number >> ");
        num1 = input.nextInt();
        num2 = input.nextInt();

        if (num1 > num2){
            high = num3 = num1;
        }
        else{
            high = num3 = num2;
        }

        while(num1 != 0){
            if(high % num1 == 0 && high % num2 == 0){
                cmv = high;
                break;
            }
            high += num3;
        }
        System.out.println("The greatest common divisor of two numbers is >> " + cmv);
    }
    
}

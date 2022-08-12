package com.thealgorithms.maths;

/**
 * Find least common multiple of two numbers
 * 
 * @author LauKinHoong
 */

public class LeastCommonMultiple {
    public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
        System.out.println("Please enter first number >> ");
        int num1 = input.nextInt();
        System.out.println("Please enter second number >> ");
        int num2 = input.nextInt();
        System.out.println("The least common multiple of two numbers is >> " + lcm(num1,num2));

	}
	
	public static int lcm (int num1, int num2){
        int high, num3;
        int cmv = 0;
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
        return cmv;
    }
}

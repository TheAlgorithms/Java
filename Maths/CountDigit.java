package Maths;

import java.util.*;

// count the number of digits in a number
public class CountDigit {
    public static void main(String args[]) {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = sc.nextInt();
	int digits = 0;
	if(number == 0){
	    System.out.println("The number of digits present in the number: 1");
	}
	else
	{
	    digits = (int)Math.floor(Math.log10(Math.abs(number)) + 1);
	    System.out.println("The number of digits present in the number: " + digits);
	}
    }
}

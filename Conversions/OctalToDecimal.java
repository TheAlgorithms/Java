package Conversions;

import java.util.Scanner;

/**
 * Converts any Octal Number to a Decimal Number
 * 
 * @author Zachary Jones
 *
 */
public class OctalToDecimal {

	/**
	 * Main method
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
        int num;
        System.out.print("Octal number: ");
        num = sc.nextInt();
        if(isOctal(num)) {
        	System.out.println("Decimal equivalent:" + octalTodecimal(num));
        }
        else {
        	System.out.println("Enter a valid octal number");
        }
        sc.close();
    }
    public static boolean isOctal(int number) {
        boolean isOctal = false;
        while (number > 0) {
            if (number % 10 <= 7) {
                isOctal = true;
             } else {
                 isOctal = false;
                 break;
              }
            number /= 10;
        }
        return isOctal;
    }
    public static int octalTodecimal(int num) {
    	int d, dec = 0, power = 0;
        while (num != 0) {
            d = num % 10;
            dec += d * (int) Math.pow(8, power++);
            num /= 10;
        }
        return dec;
    }
}
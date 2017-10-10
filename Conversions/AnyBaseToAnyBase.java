import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for converting from any base to any other base, though it's unclear how digits greater than
 * 36 would be represented in bases >36.
 * 
 * @author Michael Rolland
 * @version 2017.09.29
 *
 */
public class AnyBaseToAnyBase {
	
	// Driver
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number: ");
		String n = in.nextLine();
		int b1=0,b2=0;
		while (true) {
			try {
				System.out.print("Enter beginning base: ");
				b1 = in.nextInt();
				System.out.print("Enter end base: ");
				b2 = in.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				in.next();
			}
		}
		System.out.println(base2base(n, b1, b2));
	}
	
	/**
	 * Method to convert any integer from base b1 to base b2. Works by converting from b1 to decimal,
	 * then decimal to b2.
	 * @param n The integer to be converted.
	 * @param b1 Beginning base.
	 * @param b2 End base.
	 * @return n in base b2.
	 */
	public static String base2base(String n, int b1, int b2) {
		// Declare variables: decimal value of n, 
		// character of base b1, character of base b2,
		// and the string that will be returned.
		int decimalValue = 0, charB2;
		char charB1;
		String output="";
		// Go through every character of n
		for (int i=0; i<n.length(); i++) {
			// store the character in charB1
			charB1 = n.charAt(i);
			// if it is a non-number, convert it to a decimal value >9 and store it in charB2
			if (charB1 >= 'A' && charB1 <= 'Z') 
				charB2 = 10 + (charB1 - 'A');
			// Else, store the integer value in charB2
			else 
				charB2 = charB1 - '0';
			// Convert the digit to decimal and add it to the
			// decimalValue of n
			decimalValue = decimalValue * b1 + charB2;
		}
		
		// Converting the decimal value to base b2:
		// A number is converted from decimal to another base
		// by continuously dividing by the base and recording 
		// the remainder until the quotient is zero. The number in the
		// new base is the remainders, with the last remainder
		// being the left-most digit.
		
		// While the quotient is NOT zero:
		while (decimalValue != 0) {
			// If the remainder is a digit < 10, simply add it to
			// the left side of the new number.
			if (decimalValue % b2 < 10) 
				output = Integer.toString(decimalValue % b2) + output;
			// If the remainder is >= 10, add a character with the
			// corresponding value to the new number. (A = 10, B = 11, C = 12, ...)
			else
				output = (char)((decimalValue % b2)+55) + output;
			// Divide by the new base again
			decimalValue /= b2;
		}
		return output;
	}
}

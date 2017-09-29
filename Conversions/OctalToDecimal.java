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
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int o = sc.nextInt();
		System.out.println("Decimal equivalent: " + convertOctalToDecimal(o));
		sc.close();
	}
	
	/**
	 * This method converts an octal number to
	 * a decimal number.
	 * 
	 * @param o The octal number
	 * @return The decimal number
	 */
	public static int convertOctalToDecimal(int o) {
		System.out.print("Octal Input: ");
		// Read the input from the console which we are expecting as an octal number:
		Scanner s = new Scanner(System.in);
		String inputHex = s.nextLine();
		try{
			// Actual conversion of Octal to Decimal:
			Integer outputDecimal = Integer.parseInt(inputHex, 8);
			System.out.println("Decimal Equivalent : " + outputDecimal);
		}
		catch(NumberFormatException ne){
			// Printing a warning message if the input is not a valid octal number:
			System.out.println("Invalid Input, Expecting octal number 0-7");
		}
		finally{
			s.close();
		}
	}
}
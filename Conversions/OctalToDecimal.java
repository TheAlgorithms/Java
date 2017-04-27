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
		
	}

}

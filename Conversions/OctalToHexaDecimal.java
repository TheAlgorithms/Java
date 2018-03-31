import java.util.Scanner;

/**
 * Converts any Octal number to a HexaDecimal number
 * 
 * @author Nathan Fei
 *
 */
public class OctalToHexaDecimal {

	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		Scanner scan;
		System.out.println("Octal to HexaDecimal");
		scan = new Scanner(System.in);
		// Entering the needed number
		System.out.println("\nEnter the number : ");
		int num = Integer.parseInt(scan.nextLine(), 8);
		scan.close();
		// Converting the number to hexadecimal
		String out = toHexString(num);
		System.out.println("Hexadecimal equivalent : " + out);
	}
}

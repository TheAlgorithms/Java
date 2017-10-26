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
		Scanner sc = new Scanner(System.in);
		int octal = sc.nextInt();
		System.out.println("Binary equivalent: " + octToHex(octal));
		sc.close();
	}
	
	/**
	 * This method converts an octal number
	 * to a hexadecimal number.
	 * 
	 * @param octal The octal number
	 * @return The hexadecimal number
	 */
	public static int octToHex(int octal) {
		Scanner scan;
	  System.out.println("Octal to HexaDecimal");
		scan = new Scanner(System.in);
		// Entering the needed number
		System.out.println("\nEnter the number : ");
		int num = Integer.parseInt(scan.nextLine(), 8);
    scan.close();
    // Converting the number to binary
    String out = toHexString(num);
    System.out.println("Hexadecimal equivalent : " + out);
	}
}

import java.util.Scanner;

/**
 * Converts any Binary number to an Octal Number
 * 
 * @author Zachary Jones
 *
 */
public class BinaryToOctal {
	
	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int b = sc.nextInt();
		System.out.println("Octal equivalent: " + convertBinaryToOctal(b));
		sc.close();
		
	}
	
	/**
	 * This method converts a binary number to
	 * an octal number.
	 * 
	 * @param b The binary number
	 * @return The octal number
	 */
	public static int convertBinaryToOctal(int b) {
		
	}

}

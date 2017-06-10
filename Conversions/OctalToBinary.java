import java.util.Scanner;

/**
 * Converts any Octal number to a Binary number
 * 
 * @author Zachary Jones
 *
 */
public class OctalToBinary {

	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int o = sc.nextInt();
		System.out.println("Binary equivalent: " + convertOctalToBinary(o));
		sc.close();
	}
	
	/**
	 * This method converts an octal number
	 * to a binary number.
	 * 
	 * @param o The octal number
	 * @return The binary number
	 */
	public static int convertOctalToBinary(int o) {
		Scanner scan;
		int num;
	
		void getVal() {
		
			System.out.println("Octal to Binary");
			scan = new Scanner(System.in);
			// Entering the needed number
			System.out.println("\nEnter the number : ");
			num = Integer.parseInt(scan.nextLine(), 8);
		}
		
		void convert() {
		
			String binary = Integer.toBinaryString(num);		
			System.out.println("Binary Value is : " + binary);
		}
	}
}
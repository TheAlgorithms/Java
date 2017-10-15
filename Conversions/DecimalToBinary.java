import java.util.Scanner;

/**
	* This class converts a Decimal number to a Binary number
	* 
	* @author Spandamn (https://github.com/Spandamn)
	*
 */
class DecimalToBinary
{
	/**
	 * Main Method
	 * 
	 * @param args Command Line Arguments
	 */
	public static void main (String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n, k;
		String s = "";
		System.out.print("Decimal number: ");
		n = sc.nextInt();
		k = n;
		while(k != 0)
		{
			s = (k % 2) + s;
			k /= 2;
		} // converting decimal to binary
		int bin = Integer.parseInt(s); // Converts the String to an Integer
		System.out.println("Binary equivalent: " + bin);
		sc.close();
	}
}

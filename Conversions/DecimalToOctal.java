import java.util.Scanner;

/**
 * This class converts a Decimal number to a Binary number
 * 
 * @author Spandamn (https://github.com/Spandamn)
 *
 */
class Decimal_Octal
{
	/**
	 * Main Method
	 * 
	 * @param args Command line Arguments
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
			s = (k % 8) + s;
			k /= 8;
		} // converting decimal to octal
		int oct = Integer.parseInt(s); // Converts the String to an Integer
		System.out.println("Octal equivalent: " + oct);
		sc.close();
	}
}

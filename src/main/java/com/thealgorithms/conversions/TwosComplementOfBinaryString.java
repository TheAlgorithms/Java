// An efficient Java program to find 2's complement
import java.util.Scanner;
class Test
{
	// Method to find two's complement
	static String findTwoscomplement(StringBuffer str)
	{
		int n = str.length();
	
		// Traverse the string to get first '1' from
		// the last of string
		int i;
		for (i = n-1 ; i >= 0 ; i--)
			if (str.charAt(i) == '1')
				break;
	
		// If there exists no '1' concat 1 at the
		// starting of string
		if (i == -1)
			return "1" + str;
	
		// Continue traversal after the position of
		// first '1'
		for (int k = i-1 ; k >= 0; k--)
		{
			//Just flip the values
			if (str.charAt(k) == '1')
				str.replace(k, k+1, "0");
			else
				str.replace(k, k+1, "1");
		}
	
		// return the modified string
		return str.toString();
	}
	
	// Driver method
	public static void main(String[] args)
	{
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    		System.out.println("Enter binary");
    		String binary = myObj.nextLine();
		
		StringBuffer str = new StringBuffer(binary);
		System.out.println(findTwoscomplement(str));
	}
}

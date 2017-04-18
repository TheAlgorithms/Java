import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This method produces a reversed version of a string
 * 
 * @author Unknown
 *
 */
class ReverseString
{
	
	/**
	 * This method reverses the string str and returns it
	 * @param str String to be reversed
	 * @return Reversed string
	 */
	static String reverseString(String str)
	{
		String reverse=" ";
		if(str.length()==1)
		{
			return str;
		}
		else
		{
			reverse=reverse+str.charAt(str.length()-1)+reverseString(str.substring(0,str.length()-1));
			return reverse;
		}
	}
	
	/**
	 * Main Method
	 * 
	 * @param args Command line arguments
	 * @throws IOException Exception thrown because of BufferedReader
	 */
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the string");
		String srr=br.readLine();
		System.out.println("Reverse="+reverseString(srr));
	}
}
		
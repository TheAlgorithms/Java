import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This method produces a reversed version of a string
 * 
 * @author Unknown
 *
 */
class ReverseString{
	
	/**
	 * This method reverses the string str and returns it
	 * @param str String to be reversed
	 * @return Reversed string
	 */
	
        //Reverse using StringBuider
	public static String reverseString(String str){
	  if(str.isEmpty() || str == null) return str;
		
	  StringBuilder sb = new StringBuilder(str);
          return sb.reverse().toString();
	}
	
	
	
	/**
	 * Main Method
	 * 
	 * @param args Command line arguments
	 * @throws IOException Exception thrown because of BufferedReader
	 */
	public static void main(String args[]) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the string");
		String srr=br.readLine();
		System.out.println("Reverse="+reverseString(srr));
		br.close();
	}
}
		

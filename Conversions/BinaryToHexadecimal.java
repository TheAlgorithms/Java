package one;

import java.util.*;

public class BinaryToHexadecimal {
	
  /*
	 * program to covert a binary number to it's hexadecimal equivalent
   *
	 * @author Nishita Aggarwal
	 */
   
  // function that takes a number as binary input and returns the hexadecimal code for that number 
	static String bintoHex(int binary)
	{
		HashMap<Integer,String> hm=new HashMap<>();	//hm to store hexadecimal codes for binary numbers within range: 0000 to 1111 i.e. for decimal numbers 0 to 15
		
		String hex="";//for hexadecimal code
		int i;
		for(i=0;i<10;i++)
		{
			hm.put(i, String.valueOf(i));
		}
		for(i=10;i<16;i++)	hm.put(i,String.valueOf((char)('A'+i-10)));
		int currbit;
		while(binary!=0)
		{
			int code4=0;	//to store decimal equivalent of number formed by 4 decimal digits
			for(i=0;i<4;i++)
			{
				currbit=binary%10;
				binary=binary/10;
				code4+=currbit*Math.pow(2, i);
			}
			hex=hm.get(code4)+hex;
		}
		return hex; 
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter binary number:");
		int binary=sc.nextInt();
		String hex=bintoHex(binary);
		System.out.println("Hexadecimal Code:" + hex);
		sc.close();
	}
}

/**
Author : FAHRI YARDIMCI

A Java implementation of Caesar Cipher.
/It is a type of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. /
**/
import java.util.Scanner;
public class Caesar {
public static String encode (String message,int shift)
{
	String encoded = "";
	for(int i = 0 ; i<message.length() ;i++)
	{
		int current = message.charAt(i); //using char to shift characters because ascii is in-order latin alphabet
		if(current==32)
		{
			encoded += " ";
			continue;

		}
		else if (current>=65 && current<= 90)
		{
			int numAlphabet = message.charAt(i);
			if(shift + numAlphabet > 90)
			{
				int j = 90 - numAlphabet;
				char nextKey = (char)(65 + (shift - j - 1));
				encoded += nextKey;
				
			}
			else
			{
				char nextKey = (char)(current + shift);
				encoded += nextKey;
			}
		}
		else if (current>=97 && current <= 122)
		{
			int numAlphabet = message.charAt(i);
			if(shift + numAlphabet > 122)
			{
				int j = 122 - numAlphabet;
				char nextKey = (char)(97 + (shift - j - 1));
				encoded += nextKey;
			}
			else
			{
				char nextKey = (char)(current + shift);
				encoded += nextKey;
			}
		}
	}
	return encoded;
}
public static String decode (String message,int shift)
{
	String decoded = "";
	for(int i = 0 ; i<message.length() ;i++)
	{
		int current = message.charAt(i);
		if(current==32)
		{
			decoded += " ";
			continue;

		}
		else if (current>=65 && current<= 90)
		{
			int numAlphabet = message.charAt(i);
			if(numAlphabet - shift < 65)
			{
				int j = numAlphabet - 65;
				char nextKey = (char)(90 - (shift - j - 1));
				decoded += nextKey;
				
			}
			else
			{
				char nextKey = (char)(current - shift);
				decoded += nextKey;
			}
		}
		else if (current>=97 && current <= 122)
		{
			int numAlphabet = message.charAt(i);
			if(numAlphabet - shift < 97)
			{
				int j = numAlphabet - 97;
				char nextKey = (char)(122 - (shift - j - 1));
				decoded += nextKey;
			}
			else
			{
				char nextKey = (char)(current - shift);
				decoded += nextKey;
			}
		}
	}
	return decoded;
}
public static void main(String[] args)
{
	Scanner input = new Scanner(System.in);
	System.out.println("Please enter the message (Latin Alphabet)");
	String message = input.nextLine();
	System.out.println(message);
	System.out.println("Please enter the shift number");
	int shift = input.nextInt() % 26;
	System.out.println("(E)ncode or (D)ecode ?");
	char choice = input.next().charAt(0);
	if(choice == 'E' || choice=='e')
		System.out.println("ENCODED MESSAGE IS \n" + encode(message,shift)); //send our function to handle
	if(choice =='D' || choice =='d')
		System.out.println("DECODED MESSAGE IS \n" + decode(message,shift));
}

}
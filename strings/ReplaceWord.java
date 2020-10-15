package strings;
/* 
Accept a string and replace a chosen word with the given word.
*/

import java.util.*;
public class ReplaceWord
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
	
		String st,wd="", wd1="";
		System.out.println("Enter line ending with a space and terminated with(.):");
		st=in.nextLine();
		System.out.println("Enter the replaciong word and the word to be replaced");
		wd=in.nextLine();
		wd1="in.nextLine();
		while(true)
		{
			st=in.next();
			if(st.equals(wd))
			{
				st=wd;
				System.out.println(st+" ");
				continue;
			}
			System.out.println(st+" ");
			if(st.equals("."))
				break;
		}
	}
}
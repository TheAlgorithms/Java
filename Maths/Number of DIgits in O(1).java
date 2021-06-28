import java.util.*;
import java.lang.*;
import java.io.*;


class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		int result;
		
		if(n==0)
		result=1;
		else
		result=(int)Math.floor(Math.log10(n))+1;
		
		System.out.println(result);
	}
}

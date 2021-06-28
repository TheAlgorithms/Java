package practise;

import java.util.Scanner;

public class Automorphic {
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter number: ");
		long n=sc.nextLong();
		long sq=n*n;
		String a=String.valueOf(n);
		String b=String.valueOf(sq);
		if(b.endsWith(a))
			System.out.println("Automorphic number");
		else
			System.out.println("Not an Automorphic number");
	}
}

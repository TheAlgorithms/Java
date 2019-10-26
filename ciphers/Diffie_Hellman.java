/*
  Diffie–Hellman key exchange is a method of securely exchanging cryptographic keys over a public channel.
  It is an extremely important algorithm for transferring keys in ciphers and other cryptographic algorithms

  https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange
**/
import java.util.Scanner;

public class Diffie_Hellman
{
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[])
	{
		
		Diffie_Hellman object1 = new Diffie_Hellman(); 
		
		System.out.println("Enter modulo(p)");
		int p=sc.nextInt(); 
		
		System.out.println("Enter primitive root of modulo "+p);
		int g=sc.nextInt(); 
		
		int a=object1.AliceNumbChosen();
		int b=object1.BobNumbChosen();
		
		int A = object1.Calculate(g,a,p);
		int B = object1.Calculate(g,b,p);
		
		int S_A =object1.Calculate(B,a,p);
		int S_B =object1.Calculate(A,b,p);	
		
		if(S_A == S_B)
		{
			System.out.println("The key transfer has been sucessful between Alice and Bob");
			System.out.println("The shared Key is : "+S_A);
					
		}
		
		else
		{
			System.out.println("The key transfer failed");
		}
	}
	public int AliceNumbChosen()	
	{
		System.out.println("Choose 1st secret no(Alice)");
		int a=sc.nextInt();
		return a;
	}
	public int BobNumbChosen()
	{
		System.out.println("Choose 2nd secret no(BOB)");
		int b=sc.nextInt();
		return b;
	}
	public int Calculate(int g , int a, int p)
	{
        return (int)Math.pow(g,a)%p; 
    }   
	
}
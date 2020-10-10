//With Time Complexity O(n)

import java.util.Vector; 
import java.util.Scanner;
class SieveOfEratosthenes1
{ 
	static final int MAX_SIZE = 1000001; 
	static Vector<Boolean>isprime = new Vector<>(MAX_SIZE); 
	static Vector<Integer>prime = new Vector<>(); 
	static Vector<Integer>SPF = new Vector<>(MAX_SIZE); 
	static void manipulated_seive(int N) 
	{ 
		isprime.set(0, false); 
		isprime.set(1, false); 
		for (int i=2; i<N ; i++) 
		{ 
			if (isprime.get(i)) 
			{ 
				prime.add(i);  
				SPF.set(i,i); 
			} 
			for (int j=0; 
				j < prime.size() && 
				i*prime.get(j) < N && prime.get(j) <= SPF.get(i); 
				j++) 
			{ 
				isprime.set(i*prime.get(j),false); 
				SPF.set(i*prime.get(j),prime.get(j)) ; 
			} 
		} 
	} 
	public static void main(String args[]) 
	{
      	Scanner sc=new Scanner(System.in);
		int N = sc.nextInt();  
		for (int i = 0; i < MAX_SIZE; i++){ 
			isprime.add(true); 
			SPF.add(2); 
		} 
		manipulated_seive(N); 
		for (int i=0; i<prime.size() && prime.get(i) <= N ; i++) 
			System.out.print(prime.get(i) + " "); 
	} 
} 

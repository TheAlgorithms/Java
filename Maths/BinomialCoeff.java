package Maths;

import java.util.Scanner; 

class BinomialCoeff { 
	
	// Returns value of Binomial Coefficient C(n, k) 
	static int binomialCoefficient(int n, int k) 
	{ 
		if (k == 0 || k == n) 
			return 1; 
		
		return binomialCoefficient(n - 1, k - 1) + 
					binomialCoefficient(n - 1, k); 
	} 
	
	public static void main(String[] args) 
	{ 
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int k = scn.nextInt();
		int ans = binomialCoefficient(n, k);
		System.out.println("Value of " + n + "C" + k + ": " + ans);
	} 
} 


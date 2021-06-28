package Maths;

import java.util.Scanner;

class Catalan { 

	// to obtain nth catalan number
	public static void main(String[] args) { 
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int ans = catalan(n);
		System.out.println(ans);
	} 

	int catalan(int n) { 
		int res = 0; 
		
		if (n <= 1) { 
			return 1; 
		} 
		for (int i = 0; i < n; i++) { 
			res += catalan(i) * catalan(n - i - 1); 
		} 
		return res; 
	} 
} 

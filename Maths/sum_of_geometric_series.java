// A naive solution for calculating sum of 
// geometric series. 
import java.io.*; 

class GFG{ 
	
	// function to calculate sum of 
	// geometric series 
	static float sumOfGP(float a, float r, int n) 
	{ 
		float sum = 0; 
		for (int i = 0; i < n; i++) 
		{ 
			sum = sum + a; 
			a = a * r; 
		} 
		return sum; 
	} 

	// driver function 
	public static void main(String args[]) 
	{ 
		int a = 1; // first term 
		float r = (float)(1/2.0) ;// common ratio 
		int n = 10 ; // number of terms 
		
		System.out.printf("%.5f",(sumOfGP(a, r, n))); 
	} 
	
} 

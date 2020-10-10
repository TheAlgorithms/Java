package Maths;

import java.util.*;

public class PerfectSquare {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			double x=sc.nextInt();//enter the number you want 
			
			if (isPerfectSquare(x)) 
			    System.out.print("Yes"); 
			else
			    System.out.print("No");
		}

	}
	static boolean isPerfectSquare(double x)  
    { 
          
        // Find floating point value of 
        // square root of x. 
        double sr = Math.sqrt(x); 
      
        // If square root is an integer 
        return ((sr - Math.floor(sr)) == 0); 
    } 

}
//If the number is a perfect square it will return yes other wise it will return no.
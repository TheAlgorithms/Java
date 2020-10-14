/*
An automorphic number is a number whose square "ends" in the same digits as the number itself.
For example, 5^2 = 25, 6^2 = 36, 76^2 = 5776, and 890625^2 = 793212890625,
so 5, 6, 76 and 890625 are all automorphic numbers.
*/
import java.util.Scanner;
public class AutomorphicNumber 
{

    public static void main(String args[])
       {
	        Scanner sc = new Scanner(System.in);
        	System.out.print("Input a number : ");
        	int num = sc.nextInt();
        	int sq_num = num*num;  
 
	        String str_num = Integer.toString(num);  
        	String square = Integer.toString(sq_num);  
 
 	       if(square.endsWith(str_num))  
        	    System.out.println("Automorphic Number.");
        	else
            	System.out.println("Not an Automorphic Number.");
    	}
}
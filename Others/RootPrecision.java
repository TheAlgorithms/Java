import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RootPrecision {

    public static void main(String[] args) {
	//take input    
      Scanner scn = new Scanner(System.in);
      
      int N = scn.nextInt(); //N is the input number
      int P = scn.nextInt(); //P is precision value for eg - P is 3 in 2.564 and 5 in 3.80870.
      
      System.out.println(squareRoot(N, P));
   }
   
   public static double squareRoot(int N, int P) {
        double rv = 0;  //rv means return value
	   
        double root = Math.pow(N, 0.5);
	
	//calculate precision to power of 10 and then multiply it with root value.
	int precision = (int) Math.pow(10, P);
	root = root * precision;
	 /*typecast it into integer then divide by precision and again typecast into double 
	   so as to have decimal points upto P precision */ 
	   
	rv =  (int)root;
      return (double)rv/precision;
   }
}

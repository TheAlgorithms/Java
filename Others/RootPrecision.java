import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RootPrecision {

    public static void main(String[] args) {
	//take input    
      Scanner scn = new Scanner(System.in);
      System.out.println("Enter a number that you wish to get the squareroot from: \n>");
      int Number = scn.nextInt(); //N is the input number
      System.out.println("How many decimals do you want in the answer? \n>");
      int Precision = scn.nextInt(); //Precision for eg - 3 in 2.564 and 5 in 3.80870.
      scn.close();
      System.out.println(squareRoot(Number, Precision));
   }
   
   public static double squareRoot(int Number, int Precision) {
        double rv = 0;  //rv means return value
	   
        double root = Math.pow(Number, 0.5);
	
	//calculate precision to power of 10 and then multiply it with root value.
	int precision = (int) Math.pow(10, Precision);
	root = root * precision;
	 /*typecast it into integer then divide by precision and again typecast into double 
	   so as to have decimal points upto P precision */ 
	   
	rv =  (int)root;
      return (double)rv/precision;
   }
}
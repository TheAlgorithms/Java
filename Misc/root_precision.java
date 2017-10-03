import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      
      int N = scn.nextInt();
      int P = scn.nextInt();
      
      System.out.println(squareRoot(N, P));
   }
   
   public static double squareRoot(int N, int P) {
      double sqrt = 0;;
      
        // Write your code here
       double root = Math.pow(N, 0.5);
		int pre = (int) Math.pow(10, P);
		root = root * pre;
	   sqrt =  (int)root;
      return (double)sqrt/pre;
   }
}

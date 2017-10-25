// Java program for implementation of Horner Method 
// for Polynomial Evaluation
import java.io.*;
 
class HornerPolynomial
{
    // Function that returns value of poly[0]x(n-1) +
    // poly[1]x(n-2) + .. + poly[n-1]
    static int horner(int poly[], int n, int x)
    {
        // Initialize result
        int result = poly[0];  
  
        // Evaluate value of polynomial using Horner's method
        for (int i=1; i<n; i++)
            result = result*x + poly[i];
  
        return result;
    }
     
    // Driver program
    public static void main (String[] args) 
    {
        // Let us evaluate value of 2x3 - 6x2 + 2x - 1 for x = 3
        int[] poly = {2, -6, 2, -1};
        int x = 3;
        int n = poly.length;
        System.out.println("Value of polynomial is "
                                        + horner(poly,n,x));
    }
}
 

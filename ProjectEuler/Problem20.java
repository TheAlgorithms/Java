import java.math.*;
//In here we imported required classes
/*

n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!

link: https://projecteuler.net/problem=20


*/

//main class
public class Problem20{  
    
    //main method to run code
    public static void main(String[] args) {
        //In here we called our methods to get the answer
        System.out.println("The value of 100! is "+getSum(getFactorial(100)));
        
    }

    //the method to calculate factorial.In here we use BigInteger because integer and long datatypes overflow and return 0 or negetive number
    public static BigInteger getFactorial(int x) {

        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i <= 100; i++)
        {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
        
    }

    //In here we calculate sum of the factorial by using convert it to string
    public static int getSum(BigInteger y) {
        int tot=0;
        String temp=y.toString();
        for(int i=0;i<temp.length();i++)
        {
            tot+=Integer.parseInt(String.valueOf(temp.charAt(i)));
            
        }
        return tot;
        
    }

   }  
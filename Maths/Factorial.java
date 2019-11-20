import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static void LongFactorials(int n) {
        if(n>=20)
        {
        int arr[]=new int[51561];
        
        BigInteger temp=new BigInteger("1");
        for(int i=1;i<=n;i++)
        temp=temp.multiply(BigInteger.valueOf(i));
        System.out.println(temp);
        }
        else if(n<20 && n>=0) 
        {
          int sum=1;
            for(int i=0;i<n;i++)
            {
                sum*=i;
            }
            System.out.println(sum);
            
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        extraLongFactorials(n);

        scanner.close();
    }
}

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static long maxPrime(long n){
        long maxPrime = -1 ;
        while(n % 2 == 0){
            maxPrime = 2;
            n =n/2;
        }
        for(int i =3; i*i<=n ; i+=2){
            while(n%i == 0){
                maxPrime = i;
                n= (long)Math.sqrt(n)+ 2;
            }
        }
        if(n>2){
            maxPrime = n;
            

        }
        return maxPrime;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            long n = in.nextLong();
            System.out.println(maxPrime(n));
        }
    }
}

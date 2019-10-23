import java.io.*; 
import java.util.*; 
import java.math.*; 
import java.math.BigInteger;
class fib
{ 
    static BigInteger fib(int n) 
    {  
    if (n<=1)
    { 
       return n;
    }
    else
    {
    return fib(n-1) + fib(n-2); 
    }
    
}        
    public static void main (String args[]) 
    { 
     
    int n = 50; 
    long s=System.nanoTime();
    System.out.println(fib(n));
    long e=System.nanoTime();
    System.out.println((e-s)+"ns");
    } 
} 
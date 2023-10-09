package src.test.java.com.thealgorithms.maths;
import java.util.*;

//A deficient number is a number for which the sum of its proper divisors is less than n.
//This  program  takes an integer n as input and outputs "Yes" if it is a deficient number, and "No" otherwise.
public class DeficientNumberTest {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number:");
        long n = in.nextLong();
        long t = n;
        long sum =1;
        int sqr = (int)Math.sqrt(n);
        for(int i=2;i<=sqr;i++){
            if(n%i==0)
            sum+=i+n/i;
        }
        if(sqr*sqr==n)
        sum-=sqr;
        System.out.println(sum<t?"Yes":"No");
    }
    
}

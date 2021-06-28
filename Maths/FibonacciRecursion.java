import java.util.Scanner;
import java.util.Arrays;

public class FibonacciRecursion {
    public static int Fib(int n) {
        if ( n <= 1 ) {
            return n;
        }
        else {
            return (Fib(n-1) + Fib(n-2));
        }
        
    }
    public static void main(String args[]) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter a positive number: ");
        int n = inp.nextInt();
        
        int[] sequence = new int[n];
        for ( int i=0; i<n; i++ ) {
            sequence[i] = Fib(i);
        }
        
        System.out.printf("The first %d terms of the Fibonacci sequence are: ",n);
        System.out.println(Arrays.toString(sequence));
    }
}


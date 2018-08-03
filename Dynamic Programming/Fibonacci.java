import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class Fibonacci {

    private static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    private static int fib_b = 0; //for fibShift

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(fibMemo(n)); // Returns 8 for n = 6
        System.out.println(fibBotUp(n)); // Returns 8 for n = 6
        System.out.println(fibShift(n)); // Returns 8 for n = 6
    }

    /**
     * This method finds the nth fibonacci number using memoization technique
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     **/

    private static int fibMemo(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        int f;

        if (n <= 2) {
            f = 1;
        }
        else {
            f = fibMemo(n-1) + fibMemo(n-2);
            map.put(n,f);
        }

        return f;
    }

    /**
     * This method finds the nth fibonacci number using bottom up
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     **/

    private static int fibBotUp(int n) {

        Map<Integer,Integer> fib = new HashMap<Integer,Integer>();

        for (int i=1;i<n+1;i++) {
            int f = 1;
            if (i<=2) {
                f = 1;
            }
            else {
                f = fib.get(i-1) + fib.get(i-2);
            }
            fib.put(i, f);
        }

        return fib.get(n);
    }

    /**
     * This method finds the nth fibonacci number using Shifting property
     * f(b+a) = f(b) * f(a+1) + f(b-1) * f(a)
     * stores f(a) and f(b) for each recursion
     *
     * running time = O(log(N))
     *
     *@author Bram Lim (https://github.com/Darkdra771)
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     **/

     private static int fibShift(int n){
      //let b = a + 1  if n is odd such that
      //f(b+a) = f(b) * f(b) + f(a) * f(a)
      //let a = b if n is even such that
      //f(b+a) = f(a) * f(a+1) + [f(a+1) - f(a)] * f(a)

        //base cases
        if( n == 1){
          fib_b = 1;
          return 1;
        }
        else if ( n == 2){
          fib_b = 2;
          return 1;
        }
        else{
          int a = (int)Math.floor((double)n/2.0);

          int fib_a = fibShift(a); //this will also sets fib_b
          int b = fib_b;

          if( n % 2 == 0){ //n is even
           fib_b = fib_b * fib_b + fib_a * fib_a;
           return fib_a * b + (b - fib_a) * fib_a;
          }
          else{ //n is odd
           fib_b = fib_b * (fib_a + fib_b) + fib_a *  fib_b;
           return b * b + fib_a * fib_a;
         }
       }

     }


}

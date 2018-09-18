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

    public static void main(String[] args) throws Exception {

        var br = new BufferedReader(new InputStreamReader(System.in));
        var n = Integer.parseInt(br.readLine());

        System.out.println(fibMemo(n)); // Returns 8 for n = 6
        System.out.println(fibBotUp(n)); // Returns 8 for n = 6
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

        if (n <= 2) {
            return 1;
        }

        var fibonacciNumber = fibMemo(n - 1) + fibMemo(n - 2);
        map.put(n, fibonacciNumber);
        return fibonacciNumber;
    }

    /**
     * This method finds the nth fibonacci number using bottom up
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     **/

    private static int fibBotUp(int n) {
        var fib = new HashMap<Integer, Integer>();
        
        for (int i = 1; i < n + 1; i++) {
            var f = i <= 2 ? 1 : fib.get(i - 1) + fib.get(i - 2);

            fib.put(i, f);
        }

        return fib.get(n);
    }
}


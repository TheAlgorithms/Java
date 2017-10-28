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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

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
}


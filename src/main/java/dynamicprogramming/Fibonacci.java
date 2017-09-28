package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

public class Fibonacci {

    private final static Map<Integer, Integer> map = new HashMap<>();

    /**
     * This method finds the nth fibonacci number using memoization technique
     *
     * @param n The input n for which we have to determine the fibonacci number
     *          Outputs the nth fibonacci number
     **/
    public static int fibMemo(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int f;
        if (n <= 2) {
            f = 1;
        } else {
            f = fibMemo(n - 1) + fibMemo(n - 2);
            map.put(n, f);
        }
        return f;
    }

    /**
     * This method finds the nth fibonacci number using bottom up
     *
     * @param n The input n for which we have to determine the fibonacci number
     *          Outputs the nth fibonacci number
     **/
    public static int fibBotUp(int n) {
        Map<Integer, Integer> fib = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            int f = 1;
            if (i <= 2) {
                f = 1;
            } else {
                f = fib.get(i - 1) + fib.get(i - 2);
            }
            fib.put(i, f);
        }
        return fib.get(n);
    }
}


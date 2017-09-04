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

    public static Map<Integer,Integer> map = new HashMap<Integer,Integer>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(fib(n)); // Returns 8 for n = 6
    }

    /**
     * This method finds the nth fibonacci number using memoization technique
     *
     * @param n The input n for which we have to determine the fibonacci number
     * Outputs the nth fibonacci number
     **/

    public static int fib(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        int f;

        if (n <= 2) {
            f = 1;
        }
        else {
            f = fib(n-1) + fib(n-2);
            map.put(n,f);
        }

        return f;
    }
}


package src.main.java.com.Others;

import java.math.BigInteger;

/**
 * We may calculate power with loops, but what if the index is too large ?
 * FastPower aims to calculate quickly in this circumstances with time complexity O(log k),
 * where k is the index.
 *
 * @author DDullahan
 */
public class FastPower {
    public static BigInteger calculate(BigInteger n, BigInteger k, BigInteger mod) {
        BigInteger ans = BigInteger.ONE;
        while (!k.equals(BigInteger.ZERO)) {
            int odd = k.mod(new BigInteger("2")).compareTo(BigInteger.ZERO);
            if(odd == 1){
                ans = ans.multiply(n).mod(mod);
            }
            k = k.divide(new BigInteger("2"));
            n = n.multiply(n).mod(mod);
        }
        return ans.mod(mod);
    }

    public static long calculate(long n, long k, long mod) {
        long ans = 1;
        while (k != 0) {
            if (k % 2 == 1) {
                ans = (ans * n) % mod;
            }
            k >>= 1;
            n = (n * n) % mod;
        }
        return ans % mod;
    }
}

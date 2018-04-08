package Others;

import java.util.Arrays;
import java.util.List;

public class Prime {
    private static class Pair<T, S>{
        public T first;
        public S second;

        public Pair(T first, S second) {
            this.first = first;
            this.second = second;
        }
    }
    private static List<Long> numbers =  Arrays.asList(2L, 325L, 9375L, 28178L, 450775L, 9780504L, 1795265022L);
    private static long power(long x, long y, long mod){
        long result = 1;
        while(y != 0){
            if((y&1) == 1){
                result = (result * x) % mod;
            }
            x = (x * x) % mod;
            y /= 2;
        }
        return result;
    }

    static Pair<Long, Integer> Compute(long x){
        int maxPowerOf2 = 0;
        while((x & 1) == 0){
            ++maxPowerOf2;
            x /= 2;
        }
        return new Pair<Long, Integer>(x, maxPowerOf2);
    }

    public static Boolean IsPrime(long t){
        if(t <= 3)
            return t > 1;

        if((t&1) == 0)
            return false;

        Pair<Long, Integer> p = Compute(t - 1);
        for(long number: numbers){
            number = number % (t - 1);
            if(number == 0)
                continue;

            if(power(number, t - 1, t) != 1)
                return false;

            long l = power(number, p.first, t);
            if(l == 1 || l == t - 1)
                continue;

            boolean result = false;
            for(int i=0; i<p.second - 1; ++i){
                l = (l * l) % t;
                if(l == 1)
                    return false;

                if(l == t - 1){
                    result = true;
                    break;
                }
            }
            if(result)
                continue;
            return false;
        }
        return true;
    }
}

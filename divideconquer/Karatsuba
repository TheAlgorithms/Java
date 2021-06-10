package divideconquer;


/**
 * The Karatsuba algorithm is a fast multiplication algorithm.
 * It reduces the multiplication of two n-digit numbers to at most n^(1.58).
 */

class Karatsuba{


    public static void main(String[] args) {
        System.out.println(multiply(1234,5678));
    }


    /**
     *
     * Calculate multiply of a list of inputs
     *
     *
     * How it works :
     *
     * n = length(x , y)
     * x = a * (10 ^ (n / 2)) + b
     * y = c * (10 ^ (n / 2)) + d
     *
     * x * y    = (a * (10 ^ (n / 2)) + b) * (c * (10 ^ (n / 2)) + d)
     *          = ac * (10 ^ n) + (ad + bc) * (10 ^ (n / 2)) + bd
     *
     * this break a n-digits multiply to 4 multiply with n/2-digits
     *
     * but karatsuba make it better
     * As we know :
     * (a + b)(c + d) = ac + bd + (bc + ad)
     * In otherwise:
     * (bc + ad) = (a + b)(c + d) - ac - bd
     *
     * so
     * x * y = = ac * (10 ^ n) + ((a + b)(c + d) - ac - bd) * (10 ^ (n / 2)) + bd
     *
     * in this condition the multiplication divided to 3 n/2-digits multiplications
     *
     * @param x
     * @param y
     * @return x * y
     */
    private static long multiply(long x, long y) {

        if(x < 10 || y < 10) return x * y;

        int exp = Math.max(Long.toString(x).length(),Long.toString(y).length());//n/2

        long a = Math.floorDiv(x, (long) Math.pow(10, exp));
        long b = Math.floorMod(x, (long) Math.pow(10, exp));
        long c = Math.floorDiv(y, (long) Math.pow(10, exp));
        long d = Math.floorMod(y, (long) Math.pow(10, exp));


        /*
         * Recursively compute the three multiplications:
         *     ac
         *     bd
         *     (a + b)(c + d) = ac + bd + (bc + ad)
         */
        long ac = multiply(a, c);
        long bd = multiply(b, d);
        long ad_bc = multiply(a+b, c+d) - ac - bd;

        /*Return ac * (10 ^ n) + (ad + bc) * (10 ^ (n / 2)) + bd*/
        return (long) (ac*(Math.pow(10, exp*2)) + ad_bc*(Math.pow(10, exp)) + bd);

    }
}

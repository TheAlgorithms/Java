/**
* Binary Exponentiation for MULTIPLICATION
* Method to find a*b in O(log(b)) time complexity.
* Also useful in finding solution to (a*b)%c, where a, b, c are WAY OVER THE INTEGER LIMITS.
*
*
* Wondering how it works ?!
* Well, it's quite simple.
* RULE 1 :- a*b = (a+a) * (b/2)
* RULE 2 :- IF b is ODD THEN -- a*b = a + (a * (b - 1)) :: where (b - 1) will be EVEN. NOW apply RULE 1
* Repeat RULE 1, 2 till b = 0 OR b = 1, because a * 0 = 0 AND a * 1 = a
*
* As far as the MOD is concerned,
* RULE 3 :- (a+b) % c = ((a%c) + (b%c)) % c
* Now, apply RULE 1 or 2, whichever is required.
*/
public class binary_expo_II
{
    public static int b_expo(int a, int b)
    {
        /*
         * iterative solution
         */
        int res;
        for (res = 1; b > 0; a += a, b >>= 1) {
            if ((b&1) == 1) {
                res += a;
            }
        }
        return res;
        
        /*
         * recursive solution
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if ((b & 1) == 1) {
            return a + b_expo(a+a, b >> 1);
        } else {
            return b_expo (a+a, b >> 1);
        }
        */
    }
    
    public static long b_expo(long a, long b, long c)
    {
        /*
         * iterative solution
         */
        long res;
        for (res = 1l; b > 0; a += a, b >>= 1) {
            if ((b&1) == 1) {
                res = ((res%c) + (a%c)) % c;
            }
        }
        return res;
        
        /*
         * recursive solution
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if ((b & 1) == 1) {
            return ((a%c) + (b_expo(a+a, b >> 1)%c))%c;
        } else {
            return b_expo (a+a, b >> 1)%c;
        }
        */
    }
}

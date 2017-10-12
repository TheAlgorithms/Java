/**
 * Brian Kernighan's algorithm for finding number of set bits in a number.
 * Time Complexity -- O(logn)
 */
public class set_bits
{
    public static int function(int a)
    {
        int count;
        for (count = 0; a > 0; ++count) {
            a = a & (a - 1);
        }
        return count;
    }
}

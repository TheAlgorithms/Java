package com.thealgorithms.bitmanipulation;

public class CountSetBits {

    /**
     * The below algorithm is called as Brian Kernighan's algorithm
     * We can use Brian Kernighan’s algorithm to improve the above naive algorithm’s performance.
     The idea is to only consider the set bits of an integer by turning off its rightmost set bit
     (after counting it), so the next iteration of the loop considers the next rightmost bit.

        The expression n & (n-1) can be used to turn off the rightmost set bit of a number n. This
     works as the expression n-1 flips all the bits after the rightmost set bit of n, including the
     rightmost set bit itself. Therefore, n & (n-1) results in the last bit flipped of n.

        For example, consider number 52, which is 00110100 in binary, and has a total 3 bits set.

        1st iteration of the loop: n = 52

        00110100    &               (n)
        00110011                    (n-1)
        ~~~~~~~~
        00110000


        2nd iteration of the loop: n = 48

        00110000    &               (n)
        00101111                    (n-1)
        ~~~~~~~~
        00100000


        3rd iteration of the loop: n = 32

        00100000    &               (n)
        00011111                    (n-1)
        ~~~~~~~~
        00000000                    (n = 0)

     * @param num takes Long number whose number of set bit is to be found
     * @return the count of set bits in the binary equivalent
    */
    public long countSetBits(long num) {
        long cnt = 0;
        while (num > 0) {
            cnt++;
            num &= (num - 1);
        }
        return cnt;
    }

    /**
     * This approach takes O(1) running time to count the set bits, but requires a pre-processing.
     *
     * So, we divide our 32-bit input into 8-bit chunks, with four chunks. We have 8 bits in each chunk.
     *
     * Then the range is from 0-255 (0 to 2^7).
     * So, we may need to count set bits from 0 to 255 in individual chunks.
     *
     * @param num takes a long number
     * @return the count of set bits in the binary equivalent
     */
    public int lookupApproach(int num) {
        int[] table = new int[256];
        table[0] = 0;

        for (int i = 1; i < 256; i++) {
            table[i] = (i & 1) + table[i >> 1]; // i >> 1 equals to i/2
        }

        int res = 0;
        for (int i = 0; i < 4; i++) {
            res += table[num & 0xff];
            num >>= 8;
        }

        return res;
    }
}

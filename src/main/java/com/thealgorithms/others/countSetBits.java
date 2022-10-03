package com.thealgorithms.others;

public class countSetBits {

    /** 
     * The below algorithm is called as Brian Kernighan's algorithm 
     * We can use Brian Kernighan’s algorithm to improve the above naive algorithm’s performance. The idea is to only consider the set bits of an integer by turning off its rightmost set bit (after counting it), so the next iteration of the loop considers the next rightmost bit.

        The expression n & (n-1) can be used to turn off the rightmost set bit of a number n. This works as the expression n-1 flips all the bits after the rightmost set bit of n, including the rightmost set bit itself. Therefore, n & (n-1) results in the last bit flipped of n.

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
    public long countsetBits(long num) {
        long cnt = 0;
        while (num > 0) {
            cnt++;
            num &= (num - 1);
        }
        return cnt;
    }
}

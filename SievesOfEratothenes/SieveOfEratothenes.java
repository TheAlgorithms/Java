Skip to content
Search or jump to…

Pull requests
Issues
Marketplace
Explore
 
@DeathNaughT-GitHub 
1
00DeathNaughT-GitHub/AlgoLib
 Code Issues 0 Pull requests 0 Projects 0 Wiki Security Insights Settings
AlgoLib/NthPrime.java
@DeathNaughT-GitHub DeathNaughT-GitHub "Nth Prime Added"
3d91e29 3 days ago
91 lines (87 sloc)  2.49 KB
  
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            System.out.println(nthPrime(n));
        }
    }
    // Count number of set bits in an int
    public static int popCount(int n) {
        n -= (n >>> 1) & 0x55555555;
        n = ((n >>> 2) & 0x33333333) + (n & 0x33333333);
        n = ((n >> 4) & 0x0F0F0F0F) + (n & 0x0F0F0F0F);
        return (n * 0x01010101) >> 24;
    }

    // Speed up counting by counting the primes per
    // array slot and not individually. This yields
    // another factor of about 1.24 or so.
    public static int nthPrime(int n) {
        if (n < 2) return 2;
        if (n == 2) return 3;
        if (n == 3) return 5;
        int limit, root, count = 2;
        limit = (int)(n*(Math.log(n) + Math.log(Math.log(n)))) + 3;
        root = (int)Math.sqrt(limit);
        switch(limit%6) {
            case 0:
                limit = 2*(limit/6) - 1;
                break;
            case 5:
                limit = 2*(limit/6) + 1;
                break;
            default:
                limit = 2*(limit/6);
        }
        switch(root%6) {
            case 0:
                root = 2*(root/6) - 1;
                break;
            case 5:
                root = 2*(root/6) + 1;
                break;
            default:
                root = 2*(root/6);
        }
        int dim = (limit+31) >> 5;
        int[] sieve = new int[dim];
        for(int i = 0; i < root; ++i) {
            if ((sieve[i >> 5] & (1 << (i&31))) == 0) {
                int start, s1, s2;
             if ((i & 1) == 1) {
                start = i*(3*i+8)+4;
                s1 = 4*i+5;
                s2 = 2*i+3;
               }
            else {
                start = i*(3*i+10)+7;
                s1 = 2*i+3;
                s2 = 4*i+7;
            }
            for(int j = start; j < limit; j += s2) {
                sieve[j >> 5] |= 1 << (j&31);
                j += s1;
                if (j >= limit) break;
                sieve[j >> 5] |= 1 << (j&31);
            }
        }
    }
    int i;
    for(i = 0; count < n; ++i) {
        count += popCount(~sieve[i]);
    }
    --i;
    int mask = ~sieve[i];
    int p;
    for(p = 31; count >= n; --p) {
        count -= (mask >> p) & 1;
    }
    return 3*(p+(i<<5))+7+(p&1);
}
}
© 2019 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About

// github.com/RodneyShag

import java.util.Scanner;
import java.math.BigInteger;

// Case 1: If N is not a power of 2, reduce the counter by the largest power of 2 less than N
//
// This is equivalent to turning the most significant 1 in N to 0. This operation will keep
// repeating until we reach Case 2. To count the number of times we have to do Case 1's operation 
// we can count the number of 1s in our original number (except for the least significant 1). 

// Case 2: If N is a power of 2, we must "reduce the counter by half of N".
//
// This is equivalent of doing a right shift. This operation keeps repeating until the game ends.
// The number of right shifts we do depends on the number of trailing 0s.

// Additional optimization:
//
// Instead of counting the number of times Case 1 and Case 2 happen separately, we can just
// calculate the number of 1s in N-1. This is because subtracting 1 from our number will turn
// all of the trailing 0s (which we wanted to count) into 1s that we can count (for example:
// 10000 would become 01111)

// Example:
//
// 10110100  Case 1
//   110100  Case 1
//    10100  Case 1
//      100  Case 2
//       10  Case 2
//        1  Done
//
// We had 5 operations total. Directly applying our algorithm would look like this:
//
// Original Number: 10110100
//     Number - 1 : 10110011   and the number of 1s is 5

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while (T-- > 0) {
            BigInteger N = new BigInteger(scan.next());
            N = N.subtract(BigInteger.ONE);
            System.out.println(N.bitCount() % 2 == 0 ? "Richard" : "Louise");
        }
        scan.close();
    }
}

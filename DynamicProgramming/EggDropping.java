package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * DynamicProgramming solution for the Egg Dropping Puzzle
 */
public class EggDropping {

    // min trials with n eggs and m floors 
    @SuppressWarnings({"cast.unsafe" , "expression.unparsable.type.invalid", "array.access.unsafe.high"})
    private static int minTrials(@Positive int n, @Positive int m) {
        /* The below Line gives cast.unsafe warning because the compiler is unable to statically verify that the length of the array "eggFloor" and "eggFloor[i]" is greater than 1*/
        int @MinLen(2) [] @MinLen(2) [] eggFloor = (int @MinLen(2) [] @MinLen(2) [])new int[n + 1][m + 1];
        int result, x;

        for (int i = 1; i <= n; i++) {
            eggFloor[i][0] = 0;   // Zero trial for zero floor.
            eggFloor[i][1] = 1;   // One trial for one floor 
        }

        // j trials for only 1 egg

        for (int j = 1; j <= m; j++)
            eggFloor[1][(@IndexFor("eggFloor[1]") int)j] = j;

        // Using bottom-up approach in DP

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                eggFloor[i][(@IndexFor("eggFloor[i]") int)j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    /* This Line gives array.access.unsafe.high, expression.unparsable.type.invalid warning because the compiler is unable to statically verify that i-1 is less than length of the array "eggFloor"*/
                    result = 1 + Math.max(eggFloor[i - 1][(@IndexFor("eggFloor[i-1]") @NonNegative int)(x - 1)], eggFloor[i][(@IndexFor("eggFloor[i]") @NonNegative int)(j - x)]);

                    // choose min of all values for particular x
                    if (result < eggFloor[i][(@IndexFor("eggFloor[i]") int)j])
                        eggFloor[i][(@IndexFor("eggFloor[i]") int)j] = result;
                }
            }
        }

        return eggFloor[n][(@IndexFor("eggFloor[n]") int)m];
    }

    public static void main(String args[]) {
        int n = 2, m = 4;
        // result outputs min no. of trials in worst case for n eggs and m floors
        int result = minTrials(n, m);
        System.out.println(result);
    }
}

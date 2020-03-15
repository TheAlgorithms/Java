package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * A DynamicProgramming based solution for 0-1 Knapsack problem
 */

public class Knapsack {
    @SuppressWarnings({"cast.unsafe", "array.access.unsafe.high"})
        /* Cast unsafe arrises whenever the compiler is unable to prove the code statically.*/
    // array.access.unsafe.high arrises whenever the compiler finds an expression used to index and cannot prove it statically whether the index is valid or not.
    private static int knapSack(@Positive int W,@Positive int wt[],@Positive @SameLen("#2") int val[], @Positive @SameLen("#2") int n) throws IllegalArgumentException {
        if(wt == null || val == null)
            throw new IllegalArgumentException();
        int i, w;
        int rv[][] = (int @MinLen(1) [] @MinLen(1) [])new int[n + 1][W + 1];    //rv means return value

        // Build table rv[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    rv[i][(@LTLengthOf("rv[i]") int )w] = 0;
                else if (wt[(@IndexFor("wt") int)(i - 1)] <= w)
                    rv[i][(@LTLengthOf("rv[i]") int )w] = Math.max(val[(@IndexFor("val") int)(i - 1)] + rv[i - 1][(@Positive int)(w - wt[(@NonNegative @IndexFor("wt") int)(i - 1)])], rv[i - 1][w]);
                else
                    rv[i][(@LTLengthOf("rv[i]") int )w] = rv[i - 1][w];
            }
        }

        return rv[n][(@IndexFor("rv[n]") int)W];
    }

    @SuppressWarnings({"assignment.type.incompatible", "cast.unsafe"})
        /* Cast unsafe arrises whenever the compiler is unable to prove the code statically.*/
    // array.access.unsafe.high arrises whenever the compiler finds an expression used to index and cannot prove it statically whether the index is valid or not.
    // Driver program to test above function
    public static void main(String args[]) {
        @Positive int val @ArrayLen(3) [] = new int[]{50, 100, 130};
        @Positive int wt @ArrayLen(3) [] = new int[]{10, 20, 40};
        int W = 50;
        @SameLen("wt") int n = val.length;
        System.out.println(knapSack(W, wt, (@Positive  @SameLen("wt") int[]) val, n));
    }
}

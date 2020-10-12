import java.util.Scanner;

// A good problem to solve by Dynamic Programming is Placing Parentheses problem.
// Input: A sequence of digits d1, . . . , dn and a sequence of operations
// op1,…, op(n−1) ∈ {+,−,×}.
// Output: An order of applying these operations that maximizes the
// value of the expression. d1 op1 d2 op2 ··· op(n−1) dn .
// Example:
// How to place parentheses in an expression 1 + 2 — 3 x 4 — 5 to maximize its value?
// The answer: ((1 + 2) − (3 × (4 − 5))) = 6.
// To find out the right recurrences of this problem. We assume that the last operation in an optimal parenthesizing of expression 5 − 8 + 7 × 4 − 8 + 9 is x.
// So the goal is finding out the maximum of (5−8+7)×(4−8+9)
// We can see the subproblems now, we have 2 sub-expression: s1, s2. To find maximum of s1 x s2, we will find max{ min(s1) x min(s2), min(s1) x max(s2), max(s1) x min(s2), max(s1) x max(s2) }. Base on that, we can compute the maximum of (5 − 8 + 7) × (4 − 8 + 9)
// min(5−8+7) = (5−(8+7)) = −10
// max(5−8+7) = ((5−8)+7) = 4
// min(4−8+9) = (4−(8+9)) = −13
// max(4 − 8 + 9) = ((4 − 8) + 9) = 5
// Clearly, max((5 − 8 + 7) × (4 − 8 + 9)) = 130


public class PlacingParentheses {
    private static long getMaximValue(String exp) {
      //write your code here
      return 0;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}
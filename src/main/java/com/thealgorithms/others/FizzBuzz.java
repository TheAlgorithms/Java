package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Classic FizzBuzz: for numbers 1 to n, returns "FizzBuzz" if divisible by
 * both 3 and 5, "Fizz" if divisible by 3, "Buzz" if divisible by 5, or the
 * number as a string otherwise.
 *
 * <p>Time complexity: O(n), Space complexity: O(n)
 *
 * @see <a href="https://leetcode.com/problems/fizz-buzz/">LeetCode #412</a>
 */
public final class FizzBuzz {
    private FizzBuzz() {
    }

    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}

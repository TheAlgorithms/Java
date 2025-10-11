package com.thealgorithms.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public final class ValidParentheses {

    private ValidParentheses() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    private static final Map<Character, Character> PAIRS = Map.of(')', '(', '}', '{', ']', '[');

    public static boolean isValid(final String s) {
        if (s == null) {
            throw new NullPointerException("Input cannot be null");
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (PAIRS.containsValue(ch)) { // opening bracket
                stack.push(ch);
            } else if (PAIRS.containsKey(ch)) { // closing bracket
                // Split logic to satisfy PMD
                if (stack.isEmpty()) {
                    return false;
                }
                Character top = stack.pop();
                if (top != PAIRS.get(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

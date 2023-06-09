package com.thealgorithms.strings;
//        Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine
//        if the input string is valid. An input string is valid if: Open brackets must be closed by
//        the same type of brackets. Open brackets must be closed in the correct order. Every close
//        bracket has a corresponding open bracket of the same type.

public class ValidParentheses {
    public static boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
            case '{':
            case '[':
            case '(':
                stack[head++] = c;
                break;
            case '}':
                if (head == 0 || stack[--head] != '{') return false;
                break;
            case ')':
                if (head == 0 || stack[--head] != '(') return false;
                break;
            case ']':
                if (head == 0 || stack[--head] != '[') return false;
                break;
            }
        }
        return head == 0;
    }
}

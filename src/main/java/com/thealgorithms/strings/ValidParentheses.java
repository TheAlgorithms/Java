package com.thealgorithms.strings;
//        Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//        An input string is valid if:
//        Open brackets must be closed by the same type of brackets.
//        Open brackets must be closed in the correct order.
//        Every close bracket has a corresponding open bracket of the same type.


public class ValidParentheses {
	public static boolean isValid(String s) {
		char[] stack = new char[s.length()];
		int head = 0;
		for (char c : s.toCharArray()) {
			if (OpenBracket(c)) {
				stack[head++] = c;
			} else if (CloseBracket(c)) {
				if (head == 0 || !matches(stack[--head], c)) {
					return false;
				}
			}
		}
		return head == 0;
	}

	private static boolean OpenBracket(char c1){
		return c1 == '(' || c1 == '{' || c1 == '[';
	}

	private static boolean CloseBracket(char c2){
		return c2 == ')' || c2 == '}' || c2 == ']';
	}

	private static boolean matches(char c1, char c2) {
		return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
	}
}

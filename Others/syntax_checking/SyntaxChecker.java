package syntax_checking;

import java.util.Stack;

public class SyntaxChecker {
	public boolean syntaxChecker(String array) {
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0; i < array.length(); i++) {
			if(array.charAt(i) == '<' || array.charAt(i) == '[') {
				stack.push(array.charAt(i));
			} else {
				if((array.charAt(i) == '>' && stack.peek() == '<') || (array.charAt(i) == ']' && stack.peek() == '[')) {
					stack.pop();
				} else {
					stack.push(array.charAt(i));
				}
			}
		}
		
		return stack.empty();
	}
}

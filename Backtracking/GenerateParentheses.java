package challengesBackTracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class GenerateParentheses {
	public static ArrayList<String> aa = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		solve(2 * n, "");
		Collections.sort(aa, Collections.reverseOrder());
		for(String ss:aa) {
			System.out.println(ss);
		}
	}

	public static void solve(int n, String ans) {
		if (ans.length() > n) {
			return;
		}
		if (ans.length() == n) {
			if (isBalanced(ans)) {
				aa.add(ans);
			} else {
				return;
			}
		}
		solve(n, '(' + ans);
		solve(n, ')' + ans);
		return;
	}

	public static boolean isBalanced(String s) {
		Stack<Character> stack = new Stack<>();
		boolean balanced = true;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(s.charAt(i));
			} else if (s.charAt(i) == ')') {
				if (stack.isEmpty()) {
					balanced = false;
					break;
				} else {
					if (s.charAt(i) == ')' && stack.peek() == '(') {
						stack.pop();
					} else {
						balanced = false;
					}
				}
			}
		}
		if (balanced && stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}

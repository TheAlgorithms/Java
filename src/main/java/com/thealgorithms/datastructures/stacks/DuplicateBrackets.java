package com.thealgorithms.datastructures.stacks;

//  1. You are given a string exp representing an expression.
// 2. Assume that the expression is balanced  i.e. the opening and closing brackets match with each other.
// 3. But, some of the pair of brackets maybe extra/needless.
// 4. You are required to print true if you detect extra brackets and false otherwise.
// e.g.'
// ((a + b) + (c + d)) -> false
// (a + b) + ((c + d)) -> true
import java.util.*;

public class DuplicateBrackets {

    public static boolean check(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                if (st.peek() == '(') {
                    return true;
                } else {
                    while (st.size() > 0 && st.peek() != '(') {
                        st.pop();
                    }
                    st.pop();
                }
            } else {
                st.push(ch);
            }
            // System.out.println(st);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(check(str));
        sc.close();
    }
}

// Problem Link: https://leetcode.com/problems/generate-parentheses/ 
// Title: 22. Generate Parentheses      
// This Java program generates all valid combinations of n pairs of parentheses.
// It uses a recursive approach with backtracking to explore and construct valid combinations.

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    // Helper function to generate valid parentheses
    static void solve(List<String> ans, int n, int open, int close, StringBuilder output) {
        // Base case: If both open and close parentheses are used up, add the current output to the result list
        if (open == 0 && close == 0) {
            ans.add(output.toString());
            return;
        }

        // Add an open parenthesis if there are remaining open parentheses
        if (open > 0) {
            output.append('(');
            solve(ans, n, open - 1, close, output);
            output.deleteCharAt(output.length() - 1);
        }

        // Add a close parenthesis if there are more open than close parentheses
        if (close > open) {
            output.append(')');
            solve(ans, n, open, close - 1, output);
            output.deleteCharAt(output.length() - 1);
        }
    }

    // Main function to generate valid parentheses of length n
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        int open = n;
        int close = n;
        StringBuilder output = new StringBuilder("");
        solve(ans, n, open, close, output); // Call the recursive solver
        return ans; // Return the list of valid parentheses
    }

    public static void main(String[] args) {
        int n = 3; // Example input
        List<String> parentheses = generateParenthesis(n);

        // Display the generated valid parentheses
        System.out.println("Valid parentheses of length " + n + ":");
        for (String parenthesis : parentheses) {
            System.out.println(parenthesis);
        }
    }
}

// Problem Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Title : 17. Letter Combinations of a Phone Number

// This Java program generates letter combinations for a given set of digits
// using the mapping of digits to letters on a phone keypad.
// It employs a recursive approach with backtracking to explore and construct the combinations.
// Example input: "23" produces combinations like "ad," "ae," "af," "bd," "be," "bf," "cd," "ce," "cf."


import java.util.ArrayList;
import java.util.List;

public class PhoneNumber {
    // Helper function to generate letter combinations
    static void solve(List<String> ans, String digits, String[] mapping, int index, StringBuilder output) {
        // Base case: If we have processed all digits, add the current output to the result list
        if (index == digits.length()) {
            ans.add(output.toString());
            return;
        }

        // Get the current digit and its corresponding letters
        int digit = digits.charAt(index) - '0';
        String value = mapping[digit];

        // Iterate through the letters for the current digit
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            
            // Append the current letter to the output
            output.append(ch);
            
            // Recursively move to the next digit
            solve(ans, digits, mapping, index + 1, output);
            
            // Backtrack by removing the last character to explore other combinations
            output.deleteCharAt(output.length() - 1);
        }
    }

    // Main function to generate letter combinations for a given set of digits
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        
        // Check if the input digits are empty
        if (digits.length() == 0) {
            return ans;
        }
        
        // Initialize an empty StringBuilder for the output
        StringBuilder output = new StringBuilder("");
        
        // Mapping of digits to letters on a phone keypad
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        int index = 0; // Start with the first digit
        solve(ans, digits, mapping, index, output); // Call the recursive solver
        return ans; // Return the list of letter combinations
    }

    public static void main(String[] args) {
        // Example input: "23"
        String digits = "23";
        PhoneNumber phoneNumber = new PhoneNumber();
        List<String> combinations = phoneNumber.letterCombinations(digits);

        // Display the generated letter combinations
        System.out.println("Letter combinations for digits " + digits + ":");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }
}

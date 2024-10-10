import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    // Memoization map to store results of subproblems
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isMatch(String s, String p) {
        // Check if the result is already computed
        String key = s + "," + p;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Base case: if the pattern is empty, the string must also be empty
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // Check if the first characters match
        boolean firstMatch = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        boolean result;
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // Handle the '*' case (zero or more occurrences of the preceding character)
            result = (isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p)));
        } else {
            // Handle the normal case (no '*')
            result = firstMatch && isMatch(s.substring(1), p.substring(1));
        }

        // Memoize the result
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input string: ");
        String inputString = scanner.nextLine();

        System.out.print("Enter the pattern: ");
        String pattern = scanner.nextLine();

        boolean isMatch = solution.isMatch(inputString, pattern);

        if (isMatch) {
            System.out.println("The string matches the pattern.");
        } else {
            System.out.println("The string does not match the pattern.");
        }

        scanner.close();
    }
}


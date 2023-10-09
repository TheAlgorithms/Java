package com.thealgorithms.others;

/**
 * @author Prateek Kumar Oraon (https://github.com/prateekKrOraon)
 */
import java.util.Scanner;

// An implementation of Rabin-Karp string matching algorithm
// Program will simply end if there is no match
public class RabinKarp {

    public static Scanner scanner = null;
    public static final int d = 256;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Enter String");
        String text = scanner.nextLine();
        System.out.println("Enter pattern");
        String pattern = scanner.nextLine();

        int q = 101;
        searchPat(text, pattern, q);
    }

    private static void searchPat(String text, String pattern, int q) {
        int m = pattern.length();
        int n = text.length();
        int t = 0;
        int p = 0;
        int h = 1;
        int j = 0;
        int i = 0;

        h = (int) Math.pow(d, m - 1) % q;

        for (i = 0; i < m; i++) {
            // hash value is calculated for each character and then added with the hash value of the
            // next character for pattern as well as the text for length equal to the length of
            // pattern
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= n - m; i++) {
            // if the calculated hash value of the pattern and text matches then
            // all the characters of the pattern is matched with the text of length equal to length
            // of the pattern if all matches then pattern exist in string if not then the hash value
            // of the first character of the text is subtracted and hash value of the next character
            // after the end of the evaluated characters is added
            if (p == t) {
                // if hash value matches then the individual characters are matched
                for (j = 0; j < m; j++) {
                    // if not matched then break out of the loop
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                // if all characters are matched then pattern exist in the string
                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // if i<n-m then hash value of the first character of the text is subtracted and hash
            // value of the next character after the end of the evaluated characters is added to get
            // the hash value of the next window of characters in the text
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // if hash value becomes less than zero than q is added to make it positive
                if (t < 0) {
                    t = (t + q);
                }
            }
        }
    }
}

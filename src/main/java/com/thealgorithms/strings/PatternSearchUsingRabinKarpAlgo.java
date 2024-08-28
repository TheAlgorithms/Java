package com.thealgorithms.strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    @author Lohit M Kudlannavar (https://github.com/Lohit-pro)

    https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
    The Rabin-Karp algorithm calculates a hash value for the pattern and a hash value for a sliding window
    of text in the same length as the pattern. If the hash values match, it checks character by character
    to confirm that it's an exact match.
*/

public class PatternSearchUsingRabinKarpAlgo {

    private PatternSearchUsingRabinKarpAlgo() {
    }

    // I'm using Rabin-Karp algorithm that uses hashing to find pattern strings in a text.
    public static List<String> search(String text, String pattern) {
        List<String> result = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();
        int prime = 101; // A prime number to mod hash values

        int patternHash = 0; // Hash value for pattern
        int textHash = 0; // Hash value for text window
        int h = 1;

        // The value of h would be "pow(d, m-1) % prime"
        for (int i = 0; i < m - 1; i++) {
            h = h * 256 % prime;
        }

        // Calculating the hash value of pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (256 * patternHash + pattern.charAt(i)) % prime;
            textHash = (256 * textHash + text.charAt(i)) % prime;
        }

        // Iterating on pattern by single char again and again..
        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {
                // If the hash values match, then only checking for next char's
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }

                // If patternHash == textHash and pattern[0...m-1] == text[i...i+m-1]
                if (j == m) {
                    result.add("Start: " + i + ", End: " + (i + m - 1) + ", Substring: " + text.substring(i, i + m));
                }
            }

            // Calculating hash value for next window of text: Remove leading digit, add trailing digit
            if (i < n - m) {
                textHash = (256 * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;

                // We might get negative value of textHash,so converting it to positive
                if (textHash < 0) {
                    textHash = (textHash + prime);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String text = in.next();
        //        String text = "ABCCDDAEFG";  test

        System.out.print("Enter the searching string: ");
        String pattern = in.next();
        //        String pattern = "CDD";    testt

        List<String> result = search(text.toLowerCase(), pattern.toLowerCase());

        if (result.isEmpty()) {
            System.out.println("Pattern not found in the given text.");
        } else {
            System.out.println("Pattern found: " + result);
        }

        in.close();
    }
}

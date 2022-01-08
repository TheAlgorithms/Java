import java.io.*;
public class LongestHappyString {
        /*
         *@author Akanksha Dube
         *@date 2022.01.08
         *
         *A string s is called happy if it satisfies the following conditions:s only contains the letters 'a', 'b', and 'c'.
         *s does not contain any of "aaa", "bbb", or "ccc" as a substring.
         *s contains at most a occurrences of the letter 'a'.
         *s contains at most b occurrences of the letter 'b'.
         *s contains at most c occurrences of the letter 'c' .
         */

        static void computeLPSArray(String str, int M,int lps[])
        {
            // length of the previous
            // longest prefix suffix
            int len = 0;

            int i;

            lps[0] = 0; // lps[0] is always 0
            i = 1;

            // the loop calculates lps[i]
            // for i = 1 to M-1
            while (i < M)
            {
                if (str.charAt(i) == str.charAt(len))
                {
                    len++;
                    lps[i] = len;
                    i++;
                }
                else // (pat[i] != pat[len])
                {
                    if (len != 0)
                    {
                        // This is tricky. Consider the
                        // example AAACAAAA and i = 7.
                        len = lps[len-1];

                        // Also, note that we do
                        // not increment i here
                    }
                    else // if (len == 0)
                    {
                        lps[i] = 0;
                        i++;
                    }
                }
            }
        }

        // Returns true if str is repetition of
// one of its substrings else return false.
        static boolean isRepeat(String str)
        {
            // Find length of string and create
            // an array to store lps values used in KMP
            int n = str.length();
            int lps[] = new int[n];

            // Preprocess the pattern (calculate lps[] array)
            computeLPSArray(str, n, lps);

            // Find length of longest suffix
            // which is also prefix of str.
            int len = lps[n-1];

            return (len > 0 && n%(n-len) == 0)? true: false;
        }

        // Driver program to test above function
        public static void main(String[] args)
        {
            String s[] = {"ABCABC", "ABABAB", "ABCDABCD",
                    "GEEKSFORGEEKS", "GEEKGEEK",
                    "AAAACAAAAC", "ABCDABC"};
            int n = s.length;
            for (int i = 0; i < n; i++) {
                if(isRepeat(s[i]) == true)
                    System.out.println("True");
                else
                    System.out.println("False");
            }
        }
    }

package com.thealgorithms.searches;

class KMPSearch {

    int kmpSearch(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int[] lps = new int[m];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, m, lps);

        int i = 0; // index for txt[]
        while ((n - i) >= (m - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                System.out.println("Found pattern "
                    + "at index " + (i - j));
                int index = (i - j);
                j = lps[j - 1];
                return index;
            }
            // mismatch after j matches
            else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        System.out.println("No pattern found");
        return -1;
    }

    void computeLPSArray(String pat, int m, int[] lps) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to m-1
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { // (pat[i] != pat[len])
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
                    // Also, note that we do not increment
                    // i here
                } else { // if (len == 0)
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
// This code has been contributed by Amit Khandelwal.

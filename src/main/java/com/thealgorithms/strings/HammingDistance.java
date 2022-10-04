package com.thealgorithms.strings;

/* In information theory, the Hamming distance between two strings of equal length 
is the number of positions at which the corresponding symbols are different. 
https://en.wikipedia.org/wiki/Hamming_distance
*/
public class HammingDistance {

    /**
     * calculate the hamming distance between two strings of equal length
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return {@code int} hamming distance
     * @throws Exception
     */
    public static int calculateHammingDistance(String s1, String s2)
        throws Exception {
        if (s1.length() != s2.length()) {
            throw new Exception("String lengths must be equal");
        }

        int stringLength = s1.length();
        int counter = 0;

        for (int i = 0; i < stringLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }
}

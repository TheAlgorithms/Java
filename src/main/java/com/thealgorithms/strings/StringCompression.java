package com.thealgorithms.strings;
/*  References : https://en.wikipedia.org/wiki/Run-length_encoding
 * String compression algorithm deals with encoding the string, that is, shortening the size of the
 * string
 * @author Swarga-codes (https://github.com/Swarga-codes)
 */
public class StringCompression {
    /**
     * Returns the compressed or encoded string
     *
     * @param ch character array that contains the group of characters to be encoded
     * @return the compressed character array as string
     */
    public static String compress(String input) {
        // Keeping the count as 1 since every element present will have atleast a count
        // of 1
        int count = 1;
        String compressedString = "";
        // Base condition to check whether the array is of size 1, if it is then we
        // return the array
        if (input.length() == 1) {
            return "" + input.charAt(0);
        }
        // If the array has a length greater than 1 we move into this loop
        for (int i = 0; i < input.length() - 1; i++) {
            // here we check for similarity of the adjacent elements and change the count
            // accordingly
            if (input.charAt(i) == input.charAt(i + 1)) {
                count = count + 1;
            }
            if ((i + 1) == input.length() - 1 && input.charAt(i + 1) == input.charAt(i)) {
                compressedString = appendCount(compressedString, count, input.charAt(i));
                break;
            } else if (input.charAt(i) != input.charAt(i + 1)) {
                if ((i + 1) == input.length() - 1) {
                    compressedString = appendCount(compressedString, count, input.charAt(i)) + input.charAt(i + 1);
                    break;
                } else {
                    compressedString = appendCount(compressedString, count, input.charAt(i));
                    count = 1;
                }
            }
        }
        return compressedString;
    }
    /**
     * @param res   the resulting string
     * @param count current count
     * @param ch    the character at a particular index
     * @return the res string appended with the count
     */
    public static String appendCount(String res, int count, char ch) {
        if (count > 1) {
            res += ch + "" + count;
            count = 1;
        } else {
            res += ch + "";
        }
        return res;
    }
}
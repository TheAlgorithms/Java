package com.thealgorithms.strings;

/**
 * Reverse String using Recursion
 */

public class ReverseStringRecursive {
    /**
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse(String str)
    {
        if(str.isEmpty()){
            return str;
        } else {
            return reverse(str.substring(1))+str.charAt(0);
        }
    }
}

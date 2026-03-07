/**
 * Moves all '#' characters to the end of the string.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Inspired by Move Zeroes problem:
 * https://leetcode.com/problems/move-zeroes/
 *
 * @author Shyam Chavda
 */

package com.thealgorithms.twopointer;

public class MoveHash {

    /** default constructor  */
    public MoveHash() {}

    /**
     * Place all hash to end of the String and return new String.
     *
     * @param s the input string.
     * @return the new string where hash place at the end.
     * returns null if the input string is null.
     */

    public static String movehashtoend(String s) {
        /** converts string into character array
          for example.,
               string = roman,
               character array = ['r','o','m','a','n']
         */

        char[] c = s.toCharArray();

        /** return null if inputed string is null */
        if (s == null) {
            return "null";
        }

        /** j works like a tracker the hash position */
        int j = 0;

        /** i traverse whole character array.
         * if character is non-hash then easily swap with tracker(j) position
         *
         * for example.,
         * input string = a#bc;
         * at first traverse current non-hash character(a) swap with j position.
         *
         * string becomes #abc.
         * this continuos untill i reaches to end of string.
         */

        for (int i=0; i<c.length; i++) {
            if (c[i] != '#') {
                swap(i, j, c);
                j++;
            }
        }

        /** converts the character array to String at a time and returns. */
        return new String(c);
    }

    /** simple swapping logic with third variable. */
    public static void swap(int a, int b, char[] c) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    public static void main(String[] args) {
        /** input part. */
        String input = "h#e#l###l#o";

        /** output catches through the function.  */
        String output = movehashtoend(input);

        /** display appropriate output. */
        System.out.println(output);
    }
}

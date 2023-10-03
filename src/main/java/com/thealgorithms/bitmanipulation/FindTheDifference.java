package com.thealgorithms.bitmanipulation;

/**
 * Given two strings, str1 and str2, find the index of the extra character that
is present in only one of the strings.
 * Constraints:
 * 0 ≤ str1.length, str2.length ≤≤1000
 * Either str2.length == str1.length + 1, or, str1.length == str2.length + 1
The strings consist of lowercase English letters.

 * @author Anjita Gargi Chandora (<a
href="https://github.com/anjitagargi">Git-anjitagargi</a>)
 */

public class FindTheDifference {

  public static int extraCharcterIndex(String str1, String str2) {
    int result = 0;
    int index = 0;
    int str1Length = str1.length();
    int str2Length = str2.length();

    for (int i = 0; i < str1Length; i++) {
      result ^= str1.charAt(i);
    }

    for (int i = 0; i < str2Length; i++) {
      result ^= str2.charAt(i);
    }

    if (str1.length() > str2.length()) {
      index = str1.indexOf((char)(result));
      return index;
    }

    else {
      index = str2.indexOf((char)(result));
      return index;
    }
  }

  public static void main(String[] args) {
    // Driver code
    // Example - 1
    String[] string1 = {"wxyz", "cbda", "jlkmn", "courae", "hello"};
    String[] string2 = {"zwxgy", "abc", "klmn", "couearg", "helo"};
    for (int i = 0; i < string1.length; i++) {
      System.out.println(i + 1 + ".\tString1 = " + string1[i] +
                         " \n\tString2 = " + string2[i]);
      System.out.println("\n\tThe extra character is at index: " +
                         extraCharcterIndex(string1[i], string2[i]));
    }
  }
}

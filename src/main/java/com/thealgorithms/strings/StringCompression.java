package com.thealgorithms.strings;
/*  Link to the problem : https://leetcode.com/problems/string-compression/description/
 * String compression algorithm deals with encoding the string, that is, shortening the size of the string
 * @author Swarga-codes (https://github.com/Swarga-codes)
*/
public class StringCompression {
  /**
   * Returns the compressed or encoded string
   * 
   * @param ch character array that contains the group of characters to be encoded
   * @return the compressed character array as string
   */
  public static String compress(char[] ch) {
    // Keeping the count as 1 since every element present will have atleast a count
    // of 1
    int count = 1;
    String res = "";
    // Base condition to check whether the array is of size 1, if it is then we
    // return the array
    if (ch.length == 1) {
      return "" + ch[0];
    }
    // If the array has a length greater than 1 we move into this loop
    for (int i = 0; i < ch.length - 1; i++) {
      // here we check for similarity of the adjacent elements and change the count
      // accordingly
      if (ch[i] == ch[i + 1]) {
        count = count + 1;
      }
      if ((i + 1) == ch.length - 1 && ch[i + 1] == ch[i]) {
        res = checkCount(res, count, ch, i);
        break;
      } else if (ch[i] != ch[i + 1]) {
        if ((i + 1) == ch.length - 1) {
          res = checkCount(res, count, ch, i) + ch[i + 1];
          break;
        } else {
          res = checkCount(res, count, ch, i);
          count = 1;
        }
      }
    }
    // This loop is not necessary unless you are doing it for leetcode submission
    for (int i = 0; i < res.length(); i++) {
      ch[i] = res.charAt(i);
    }
    return res;
  }
  /**
   * @param res   the resulting string
   * @param count current count
   * @param ch    the character array
   * @param i     the index at which the count is to be appended
   * @return the res string appended with the count
   */
  public static String checkCount(String res, int count, char[] ch, int i) {
    if (count > 1) {
      res += ch[i] + "" + count;
      count = 1;
    } else {
      res += ch[i] + "";
    }
    return res;
  }
}
package com.thealgorithms.strings;

import java.util.Scanner;

// String compression or String encoding algorithm deals with shortening the size of the string like for example,
// Input:
// Here, we take a string of characters,
// char[] chars = ["a","a","b","b","c","c","c"]
// Now, the compressed version of this would be "a2b2c3"
//Lets take another one ,
// char[] chars = ["a","b","b","b"]
//Here, the compressed version would be "ab3" and not "a1b3" since the count of a is 1 only so we just adding 
//the character to the encoded string and not the count
public class StringCompression {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // Sample test case
    // char[] ch={'a','a','b','b','c','c','c'};
    // Custom input by the user
    // Size of the character array
    int n = in.nextInt();
    // declaration of the character array
    char[] ch = new char[n];
    for (int i = 0; i < n; i++) {
      ch[i] = in.next().charAt(0);
    }
    in.close();
    // Displays the compressed string or the encoded string
    System.out.println("The encoded string or the compressed string is: " + compress(ch));
  }

  public static String compress(char[] ch) {
    // Keeping the count always as 1 since there will always be atleast a single
    // count for the characters present in the array
    int count = 1;
    // resultant string where we will be storing the value of the encoded length as
    // we iterate
    String res = "";
    // Base condition to check whether the array is of size 1, if it is then we
    // directly return that array
    // Since an array of length 1 cannot be compressed any further
    if (ch.length == 1) {
      return "" + ch[0];
    }
    // If not then we move into the iteration in order to compress the given
    // character array
    // Here we could have checked one by one by using the Brute force method of
    // running two loops but that worsens the time complexity to O(n^2)
    // We can rather optimally solve it in O(N) time by running a single loop
    for (int i = 0; i < ch.length - 1; i++) {
      // first we check adjacently whether our elements matches or not if it does we
      // increase our count by 1
      if (ch[i] == ch[i + 1]) {
        count = count + 1;
      }
      // Then we check for whether the next index is pointing to the last index of the
      // array, as well as, we check whether it is the same as the current index
      // if it is same then we get inside and check whether the count we currently
      // hold is greater than 1 or not if it's greater than 1 then we consider it
      // appending to our resulting string
      // else we don't append the count along with the character whose count is 1
      // basically in short this is an edge case that is we are considering
      if ((i + 1) == ch.length - 1 && ch[i + 1] == ch[i]) {
        if (count > 1) {
          res += ch[i] + "" + count;
        } else {
          res += ch[i] + "";
        }
        break;
      }
      // This is a general case or an else case that runs when the current index and
      // the upcoming index contradicts or doesn't match
      else if (ch[i] != ch[i + 1]) {
        // we have an edge case or we can say a subcase that checks whether the upcoming
        // index is the last index of the array
        // inside which we check for the count if greater than 1 we append it else we
        // don't similar as we did it above
        // Also, since this is an edge case we just don't go any further and break the
        // loop
        if ((i + 1) == ch.length - 1) {
          if (count > 1) {
            res += ch[i] + "" + count + ch[i + 1];

          } else {
            res += ch[i] + "" + ch[i + 1];

          }
          break;
        }
        // This is the remaining condition or for any other condition than the above
        // mentioned we just check for the count value, append the count if it's greater
        // than 1 and reset it back to 1 for the next iteration
        else {
          if (count > 1) {
            res += ch[i] + "" + count;
            count = 1;
          } else {
            res += ch[i] + "";

          }
        }

      }

    }
    // Now after we come out of the loop this step is not actually necessary but
    // since, I was solving this problem on leetcode it was requiring me to modify
    // the given character array as well as return the length of the encoded array
    // This loop is not that necessary and can be ignored and we can directly return
    // the encoded or compressed string directly
    for (int i = 0; i < res.length(); i++) {
      ch[i] = res.charAt(i);
    }
    return res;
  }
}

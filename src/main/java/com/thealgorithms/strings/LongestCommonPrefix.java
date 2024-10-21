package com.thealgorithms.strings;
import java.util.*;
// To find the longest Common Prefix of String array
// geeksforgeeks explaination: https://www.geeksforgeeks.org/longest-common-prefix-using-sorting/

/* The Longest Common Prefix (LCP) of a set of strings is the longest substring that appears at the beginning of each of the strings in the set. For example, given the strings:
"flower"
"flow"
"flight"
The longest common prefix is "fl", as it is the longest substring that is common at the start of all three strings. */
public class LongestCommonPrefix {
    public static String longestPrefix(String[] str){
       int n=str.length;
       Arrays.sort(str);
       if(n==0){
        return "";
       }
       String first=str[0];
       String last=str[n-1];
       int len=Math.min(first.length(),last.length());
       int i;
       for(i=0;i<len;i++){
         if(first.charAt(i)!=last.charAt(i)){
            break;
         }
       }
       return first.substring(0, i);

    }
    public static void main(String args[]){
        //Input test case
        String[] arr = {"flower", "flow", "flight"};
        
        // Output the result
        System.out.println("Longest Common Prefix: " + longestPrefix(arr));  //flo
    }
}
/* 
Time and Space Complexity:
Time Complexity:O(n log n + m)

Sorting the array takes 𝑂(𝑛 log 𝑛)
O(nlogn), where n is the number of strings.
Comparing the first and last string takes 𝑂(𝑚)
O(m), where m is the length of the shortest string.
Overall, the time complexity is 
𝑂(log 𝑛 + 𝑚 )


Space Complexity:O(n)

Sorting requires 𝑂(𝑛)
O(n) space for the array.
The space complexity for storing the prefix result is 
𝑂(1)
O(1) since it depends on the length of the prefix, which is part of the input.
Therefore, the space complexity is 𝑂(𝑛)
*/
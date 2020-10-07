package DynamicProgramming;
/*Given a sequence, find the length of its Longest Palindromic Subsequence (or LPS). In a palindromic subsequence, elements read the same backward and forward.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements. 

Test case 1:
Input: “abdbca”
Output: 5
Explanation: LPS is “abdba”.

Test case 2:
Input: = “cddpd”
Output: 3
Explanation: LPS is “ddd”.

Test case 3:
Input: = “pqr”
Output: 1
Explanation: LPS could be “p”, “q” or “r”.

*/

public class LPS {
    public int findLPSLength(String st) {
    return findLPSLengthRecursive(st, 0, st.length()-1);
    }
    private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
    if(startIndex > endIndex)
    return 0;

    if(startIndex == endIndex)
    return 1;
  
    if(st.charAt(startIndex) == st.charAt(endIndex))
    return 2 + findLPSLengthRecursive(st, startIndex+1, endIndex-1);
   
    int c1 = findLPSLengthRecursive(st, startIndex+1, endIndex);
    int c2 = findLPSLengthRecursive(st, startIndex, endIndex-1);
    return Math.max(c1, c2);
    }
    public static void main(String[] args) {
    LPS lps = new LPS();
    System.out.println(lps.findLPSLength(“abdbca”));
    System.out.println(lps.findLPSLength(“cddpd”));
    System.out.println(lps.findLPSLength(“pqr”));
    }
}
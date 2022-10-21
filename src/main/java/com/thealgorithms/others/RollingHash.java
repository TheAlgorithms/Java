/* this Code is the illustration of Rolling Hash to
find the length of the longest common substring.

Time Complexity - O(NlogN)
Space Complexity - O(N)

Other Applications of Rolling Hash :-

    1. Shortest Palindrome (214)
    2. Maximum Length of Repeated Subarray (718)
    3. Longest Duplicate Substring (1044)
    4. Longest Happy Prefix (1392)
 */

package com.thealgorithms.others;

import java.util.*;

public class RollingHash {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        String s1 = input.nextLine(); // first string
        String s2 = input.nextLine(); // second string

        int n = s1.length(); // length of first string
        int m = s2.length(); // length of second string

        int lcs = longestCommonSubstr(s1, s2, n, m); // stores the longest common substring

        System.out.println("Longest Common Substring is " + lcs);
    }

    public static int longestCommonSubstr(String S1, String S2, int n, int m){
        
        int ans = 0;
        int start = 0;
        int end = Math.min(n,m);
        
        // binary search on answer

        while(start <= end) {
            
            int mid = (start + end) / 2;
            
            if(haveCommonSubStringOfLengthK(S1,S2,mid)) {
                
                ans = mid; // update the answer
                start = mid + 1; // no need to check for smaller length
            }
            else {
                
                end = mid - 1; // no need to check for greater length
                
            }
        }
        
        return ans;
    }
    
    public static boolean haveCommonSubStringOfLengthK(String s1,String s2,int len) {
        
        // returns true if we have common substring of length K

        Set<Long> set = new HashSet<>();
        
        int n = s1.length() , m = s2.length();
        
        // Rolling Hash
        long pr = 1; // store the product
        long hash = 0;
        long q = 31; // prime number
        
        for(int i = n - 1 ; i >= n - len ; i--) {
            
            hash = ((hash * q) + (s1.charAt(i) - 'a' + 1));
            
            if(i != n-len)
            pr = (pr * 31);
        }

        set.add(hash);
        
        for(int i = n - 1 ; i >= len  ; i--) {
            
            long exclude = ((s1.charAt(i) - 'a' + 1) * pr);
            long include = (s1.charAt(i - len) - 'a' + 1);
            
            hash = ((hash - exclude) * q  + include);
            
            set.add(hash);
        }
        
        // iterate through second string
        hash = 0;
        pr = 1;
        
        for(int i = m - 1 ; i >= m - len ; i--) {
            
            hash = ((hash * q) + (s2.charAt(i) - 'a' + 1));
            
            if(i != m - len)
            pr = (pr * 31);
        }

        if(set.contains(hash)) return true;
        
        for(int i = m - 1 ; i >= len  ; i--) {
            
            long exclude = ((s2.charAt(i) - 'a' + 1) * pr);
            long include = (s2.charAt(i - len) - 'a' + 1);
            
            hash = ((hash - exclude) * q  + include);
            
            if(set.contains(hash)) return true;
        }
        
        return false;
    }
}

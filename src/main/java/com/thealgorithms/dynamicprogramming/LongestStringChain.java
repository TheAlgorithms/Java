/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

/** Program Description - To find the length of the longest string chain*/
/** Program Description link - https://medium.com/nerd-for-tech/longest-string-chain-daily-challenge-may-a07eda8b112b */

/** Wikipedia Link - https://en.wikipedia.org/wiki/Longest_common_substring */

package com.thealgorithms.dynamicprogramming;
import java.util.*;

public class LongestStringChain {
    /* Function to return length of longest possible string chain*/
    public static int longestStringChain(String words[]) {
        //string of words are passed
        //we use comparator to sort all strings according to their lengths

        Arrays.sort(words,new Comparator<String>(){
            @Override
            public int compare(String a,String b){
                return a.length()-b.length();
            }
        });

        //initialize the length of string array
        int n=words.length;
        //using lis concept
        int arr[]=new int[n];
        Arrays.fill(arr,1);

        int max=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                //if two strings differ by their predecessor property we can increase the length by 1
                if(compare(words[i],words[j])){
                    //updating the length by 1
                    arr[i]=Math.max(arr[i],1+arr[j]);
                }
            }
            max=Math.max(max,arr[i]);
        }
        return max;
        // returns the longest matching number
    }
    //comparing whether two strings are predecessor of each other or not
    public static boolean compare(String s1,String s2){
        // if length of two strings does not differ by 1 returning false
        if(s1.length()!=1+s2.length())return false;
        //initializing two pointers for two strings
        int i=0;
        int j=0;
        while(i<s1.length()){
            if(j<s2.length() && s1.charAt(i)==s2.charAt(j)){
                i++;j++;
            }
            else{
                i++;
            }
        }
        return (i==s1.length() && j==s2.length());

    }
}

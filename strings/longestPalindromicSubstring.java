package strings;

import java.util.*;
import java.io.*;

// Finds the longest palindromic substring in O(n^2) time and O(1) space
public class longestPalindromicSubstring {
    public static String getLPS(String s)
    {
        if(s.length() == 0)
            return "";

        if(s.replace(String.valueOf(s.charAt(0)), "").length() == 0) // If string has same character, no computations needed.
            return s;

        String current = s.substring(0,1);
        for(int i = 1; i < s.length(); i++)
        {
            String odd = getLPSFrom(s, i-1, i+1);
            String even = getLPSFrom(s, i-1, i);
            //Comparator<String> strlenComp = (a, b) -> Integer.compare(a.length(), b.length());
            //int longest=strlenComp.compare(odd,even);
            String longest = (odd.length()>even.length()) ? odd : even;
            if(longest.length() > current.length())
                current = longest;
        }
        return current;
    }

    public static String getLPSFrom(String s, int left, int right)
    {
        while(left >= 0&&right < s.length())
        {
            if(s.charAt(left) != s.charAt(right))
                break;
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public static void main(String[] args)
    {
        // Testcases
        System.out.println(getLPS(""));
    }
}

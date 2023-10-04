package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.HashMap;

/*
This class finds the integer value of a number represented in Roman
*/

public class RomanToInteger {
    /*
   This method returns the integer value of the given Roman number represented by string.
   @param s: A Roman number represented as a string
   @return an integer value of the roman number
   */
    public static int romanToInt(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);

        int i = s.length() - 1;
        int ans = 0;

        while (i >= 0) {
            if (i > 0 && hm.get(s.charAt(i)) > hm.get(s.charAt(i - 1))) {
                ans = ans + hm.get(s.charAt(i)) - hm.get(s.charAt(i - 1));
                i--;
            }
            else{
                ans += hm.get(s.charAt(i));
            }
            i--;
        }
        return ans;
    }
}

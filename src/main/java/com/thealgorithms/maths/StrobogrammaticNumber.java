package com.thealgorithms.maths;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumber {

    public static void main(String[] args) {
        assert isStrobogrammatic("69") == true;
        assert isStrobogrammatic("88") == true;
        assert isStrobogrammatic("818") == true;
        assert isStrobogrammatic("101") == true;
        assert isStrobogrammatic("609") == false;
        assert isStrobogrammatic("120") == false;
    }

    /**
     * Check if a number is strobogrammatic
     *
     * @param number the number to be checked
     * @return true if the number is strobogrammatic, false otherwise
     */
    public static boolean isStrobogrammatic(String number) {
        Map<Character, Character> strobogrammaticMap = new HashMap<>();
        strobogrammaticMap.put('0', '0');
        strobogrammaticMap.put('1', '1');
        strobogrammaticMap.put('8', '8');
        strobogrammaticMap.put('6', '9');
        strobogrammaticMap.put('9', '6');

        int left = 0;
        int right = number.length() - 1;

        while (left <= right) {
            char leftChar = number.charAt(left);
            char rightChar = number.charAt(right);

            if (!strobogrammaticMap.containsKey(leftChar) || strobogrammaticMap.get(leftChar) != rightChar) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}

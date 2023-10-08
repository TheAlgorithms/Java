package com.thealgorithms.maths;

import java.util.HashMap;
import java.util.Map;

/**
 * A strobogrammatic number is a number that remains the same when rotated 180 degrees.
 * In other words, the number looks the same when rotated upside down.
 * Examples of strobogrammatic numbers are "69", "88", "818", and "101".
 * Numbers like "609" or "120" are not strobogrammatic because they do not look the same when rotated.
 */
public class StrobogrammaticNumber {
    /**
     * Check if a number is strobogrammatic
     * @param number the number to be checked
     * @return true if the number is strobogrammatic, false otherwise
     */
    public boolean isStrobogrammatic(String number) {
        Map<Character, Character> strobogrammaticMap = new HashMap<>();
        strobogrammaticMap.put('0', '0');
        strobogrammaticMap.put('1', '1');
        strobogrammaticMap.put('6', '9');
        strobogrammaticMap.put('8', '8');
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

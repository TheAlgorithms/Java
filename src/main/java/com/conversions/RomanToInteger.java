package com.conversions;

import java.util.*;

public class RomanToInteger {

    private static Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * This function convert Roman number into Integer
     *
     * @param A Roman number string
     * @return integer
     */
    public static int romanToInt(String A) {

        char prev = ' ';

        int sum = 0;

        int newPrev = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            char c = A.charAt(i);
            //System.out.println("prev != ' ' " + String.valueOf(prev != ' '));
            if (prev != ' ') {
                // checking current Number greater then previous or not
                //System.out.println("map.get(prev) > newPrev: " + String.valueOf(map.get(prev) > newPrev));
                newPrev = map.get(prev) > newPrev ? map.get(prev) : newPrev;
            }

            int currentNum = map.get(c);

            // if current number greater then prev max previous then add
            System.out.println("currentNum >= newPrev: " + String.valueOf(currentNum >= newPrev));
            if (currentNum >= newPrev) {
                sum += currentNum;
            } else {
                // subtract upcoming number until upcoming number not greater then prev max
                sum -= currentNum;
            }

            prev = c;
        }

        return sum;
    }

    public static void main(String[] args) {
        int sum = romanToInt("IX");
        System.out.println(sum);
    }
}
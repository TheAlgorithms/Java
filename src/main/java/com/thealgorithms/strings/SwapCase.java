package com.thealgorithms.strings;

public class SwapCase {

    public static void main(String[] args) {
        String[] strings = {"abcdef", "ABCDEF", "aBcDeF", "123456", "AbCdEf123456"};
        for (String str : strings) {
            System.out.println(toSwapCases(str));
        }
    }

    /* Changes lowercase letters to uppercase and uppercase letters to lowercase*/

    public static String toSwapCases(String str) {
        char[] characters = str.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isLetter(characters[i]) && Character.isLowerCase(characters[i])) {
                characters[i] = Character.toUpperCase(characters[i]);
            }
            else if (Character.isLetter(characters[i]) && Character.isUpperCase(characters[i])) {
                characters[i] = Character.toLowerCase(characters[i]);
            }
        }
        return new String(characters);
    }
}
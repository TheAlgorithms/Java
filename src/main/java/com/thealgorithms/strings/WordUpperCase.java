package com.thealgorithms.strings;

public class WordUpperCase {
    public static void main(String[] args) {
        String str = "hii my name is abhishek";
        System.out.println(toUpperCase(str));
    }
    public static String toUpperCase(String str) {
        StringBuilder sb = new StringBuilder("");
        if (str == null || str.isEmpty()) {
            return str; // Handle null or empty input gracefully
        }
        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length() - 1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));

            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }   
}

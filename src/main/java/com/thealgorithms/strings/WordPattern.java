package com.thealgorithms.strings;

// Created by Kavita Nampoothiri
// Date:- 3/10/2023

//Word Pattern - 
/*Given a pattern and a string s, find if s follows the same pattern. pattern and s are same if:

1. each character in pattern represents a word in s
2. No two distinct characters in pattern can represent the same word in s
3. No single character in pattern can represent two distinct words in s.

 Examples:

1. pattern = 'abcb'; s = 'mat cat mat cat'; return False
'a' represents 'mat', 'b' represents 'cat'. Then 'c' cannot represent 'mat' again as 'a' already represents 'mat' (#2 condition is: No two distinct characters in pattern i.e. 'a' and 'c' can represent same word i.e 'mat'.)

2. pattern = 'abab'; s = 'mat sat mat sat'; return True
'a' represents 'mat' and 'b' represents sat

3. pattern = 'abcb'; s = 'mat sat hat sat'; return True
'a' represents 'mat'; 'b' represents 'sat'; 'c' represents 'hat'; and last 'sat' is already represented by 'b' and last character in pattern is also 'b'.


*/

import java.util.*;

public class WordPattern {
    public static boolean wordPattern1(String pattern, String s) {
        String[] words = s.split(" ");// creates an String array containing the parts of String "s"
        if (words.length != pattern.length()) { // CHECK 1: if the no. of the words in the String and pattern entered are same or not 
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>(); // we create two hash map's to store mappings of pattern to data string and vice versa.
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (!charToWord.containsKey(c)) {// add pattern char and create mapping with String word
                charToWord.put(c, word);
            }

            if (!wordToChar.containsKey(word)) {   // add String word and create mapping with pattern char
                wordToChar.put(word, c);
            }
            //CHECK 2:  check if map has pattern char and the mapping is valid, if no return false
            //CHECK 3:  check if map has data word and the mapping is valid, if no return false
            if (!charToWord.get(c).equals(word) || !wordToChar.get(word).equals(c)) { 
                return false;
            }
        }

        return true;   // if all mappings are valid return true  
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        //Asking User input for pattern
        System.out.println("Enter the pattern: ");
        String pattern =sc.nextLine();
         //Asking User input for String
        System.out.println("Enter the String: ");
        String checkString=sc.nextLine();
        //Executing our function and printing the respective result
        if(wordPattern1(pattern,checkString))
        System.out.println("Given string follows the pattern");
        else
        System.out.println("Given String doesn't follow the pattern");
    }
}

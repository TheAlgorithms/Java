package com.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Interleave {
    @Test
    public void testInterleaveRegularString() {
        String string1 = "Hello";
        String string2 = "World";
        String expected = "HWeolrllod";
        String actual = interleave(string1, string2);
        assertEquals("Incorrect result from method.", expected, actual);
    }

    @Test
    public void testInterleaveEmptyString() {
        String string1 = "";
        String string2 = "";
        String string3 = "a";
        String string4 = "abc";
        String expected1 = "";
        String actual1 = interleave(string1, string2);
        String expected2 = "a";
        String actual2 = interleave(string1, string3);
        String expected3 = "abc";
        String actual3 = interleave(string1, string4);
        assertEquals("Incorrect result from method.", expected1, actual1);
        assertEquals("Incorrect result from method.", expected2, actual2);
        assertEquals("Incorrect result from method.", expected3, actual3);
    }

    @Test
    public void testInterleaveSingleString() {
        String string1 = "a";
        String string2 = "b";
        String expected = "ab";
        String actual = interleave(string1, string2);
        assertEquals("Incorrect result from method.", expected, actual);
    }

    @Test
    public void testInterleaveIntString() {
        String string1 = "1";
        String string2 = "7";
        String expected = "17";
        String actual = interleave(string1, string2);
        assertEquals("Incorrect result from method.", expected, actual);
    }

    @Test
    public void testInterleaveMixedString() {
        String string1 = "1a2b3c4d";
        String string2 = "5e6f7g8h";
        String expected = "15ae26bf37cg48dh";
        String actual = interleave(string1, string2);
        assertEquals("Incorrect result from method.", expected, actual);
    }

    @Test
    public void testInterleaveSymbols() {
        String string1 = "a@b%c/";
        String string2 = "d#e$g%.";
        String expected = "ad@#be%$cg/%.";
        String actual = interleave(string1, string2);
        assertEquals("Incorrect result from method.", expected, actual);
    }

    @Test
    public void testInterleaveSpaces() {     // This string interleave algorithm defines a space as a valid character.
        String string1 = " ";
        String string2 = "a";
        String string3 = "5 g";
        String string4 = " 4 d ";
        String expected1 = " a";
        String actual1 = interleave(string1, string2);
        String expected2 = "a5 g";
        String actual2 = interleave(string2, string3);
        String expected3 = "5  4g d ";
        String actual3 = interleave(string3, string4);
        assertEquals("Incorrect result from method.", expected1, actual1);
        assertEquals("Incorrect result from method.", expected2, actual2);
        assertEquals("Incorrect result from method.", expected3, actual3);
    }

    /**
     * This method "interweaves" two input strings one character at a time. The first character of the
     * first parameter string always starts the resulting string, unless that character is a space or is empty.
     * This string interleaving method takes a space in a string (e.g. " ") into consideration.
     *
     * For example, if string1 = "abc" and string2 = "def", then the result would be "adbecf", as the first character
     * of the string1 is 'a', then the first character of string2 is 'd', and so forth.
     *
     * For more information on interleaving, check out: https://en.wikipedia.org/wiki/Interleave_sequence
     *
     * @param string1
     * @param string2
     * @return string resulting from the interweaving of the two input strings; string1 and string2.
     */
    public String interleave(String string1, String string2) {
        String result = "";     // The final interleaved string to return.
        List<Character> list1 = new ArrayList<>();     // The ArrayList of string1, with each character being an individual element.
        List<Character> list2 = new ArrayList<>();     // The ArrayList of string2, in a similar manner as above.

        for (int i = 0; i < string1.length(); i++)     // Convert string1 into list1.
            list1.add(string1.charAt(i));

        for (int i = 0; i < string2.length(); i++)     // Convert string2 into list2.
            list2.add(string2.charAt(i));

        if (string1.length() == string2.length()) {     // Interleaving when string1 and string2 are equal length.
            for (int j = 0; j < list1.size(); j++) {
                result = result + list1.get(j);
                result = result + list2.get(j);
            }
            return result;
        }

        if (string1.length() > string2.length()) {     // Interleaving when string1 is longer than string2.
            while (list2.size() > 0) {
                result = result + list1.get(0);
                list1.remove(0);
                result = result + list2.get(0);
                list2.remove(0);
            }
            for (char character : list1) {     // Concatenate the rest of the characters in list1 to the result.
                result = result + character;
            }
            return result;
        }

        if (string2.length() > string1.length()) {     // Interleaving when string2 is longer than string1.
            while (list1.size() > 0) {
                result = result + list1.get(0);
                list1.remove(0);
                result = result + list2.get(0);
                list2.remove(0);
            }
            for (char character : list2) {     // Concatenate the rest of the characters in list2 to the result.
                result = result + character;
            }
            return result;
        }

        return result;
    }
}

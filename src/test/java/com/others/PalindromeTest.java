package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Nguyen Giang
 */
public class PalindromeTest {
    // Data Flow Testing for isPalindrome
    
    Palindrome palindrome = new Palindrome();
    
    @Test
    void normalTrueTest() {
        String input = "aabbaa";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void normalFalseTest() {
        String input = "aabbcc";
        boolean expect = false;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void haveSpaceTrueTest() {
        String input = "gi aig";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void haveNumberTrueTest() {
        String input = "12abba21";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void haveNumberFalseTest() {
        String input = "12abba12";
        boolean expect = false;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void numberTrueTest() {
        String input = "11022011";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void numberFalseTest() {
        String input = "11022012";
        boolean expect = false;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void nullInputTest() {
        String input = "";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
    
    @Test
    void specialStringTest() {
        String input = "@#$abba$#@";
        boolean expect = true;
        Assertions.assertEquals(expect, palindrome.isPalindrome(input));
    }
}

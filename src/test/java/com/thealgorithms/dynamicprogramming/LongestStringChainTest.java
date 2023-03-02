package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class LongestStringChainTest {
    @Test
    void testForFirstElement() {
        String words[] = {"a","b","ba","bca","bda","bdca"};
        int actual = 4;
        int expected = LongestStringChain.longestStringChain(words);
        assertEquals(actual,expected);
    }

    @Test
    void testForSecondElement() {
        String words[] = {"helio","hello","home","hurry","harry","hurricane"};
        int actual = 1;
        int expected = LongestStringChain.longestStringChain(words);
        assertEquals(actual,expected);
    }

    @Test
    void testForThirdElement() {
        String words[] = {"aaa","aaa","aaa","aaa","aaa","aaa"};
        int actual = 1;
        int expected = LongestStringChain.longestStringChain(words);
        assertEquals(actual,expected);
    }

    @Test
    void testForFourthElement() {
        String words[] = {"zyxw","zyx","xyz","wxy","abcs","prwqxyz"};
        int actual = 2;
        int expected = LongestStringChain.longestStringChain(words);
        assertEquals(actual,expected);
    }
    @Test
    void testForFifthElement() {
        String words[] = {"abcdehsikwmnkja"};
        int actual = 1;
        int expected = LongestStringChain.longestStringChain(words);
        assertEquals(actual,expected);
    }
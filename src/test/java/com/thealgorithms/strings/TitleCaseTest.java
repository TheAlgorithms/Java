package com.thealgorithms.strings;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TitleCaseTest {

    @Test
    public void testNullOrEmptyInputs() {
        assertEquals("", TitleCase.toTitleCase(null));
        assertEquals("", TitleCase.toTitleCase(""));
    }

    @Test
    public void testSingleWord() {
        assertEquals("Hello", TitleCase.toTitleCase("hello"));
        assertEquals("Hello", TitleCase.toTitleCase("HELLO"));
        assertEquals("A", TitleCase.toTitleCase("a"));
    }

    @Test
    public void testMultipleWords() {
        assertEquals("The Quick Brown Fox", TitleCase.toTitleCase("the quick brown fox"));
        assertEquals("The Quick Brown Fox", TitleCase.toTitleCase("THE QUICK BROWN FOX"));
        assertEquals("Already Title Case", TitleCase.toTitleCase("already Title Case"));
    }

    @Test
    public void testWhitespace() {
        assertEquals("  Spaces  ", TitleCase.toTitleCase("  spaces  "));
    }
}

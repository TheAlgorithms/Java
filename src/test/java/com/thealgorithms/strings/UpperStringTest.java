package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperStringTest{
    @Test
    public void toUpperCase1() {
        String str1 = "sOfTware TEstinG";
        assertEquals("SOFTWARE TESTING", Upper.toUpperCase(str1));
    }

    @Test
    public void toUpperCase2() {
        String str2 = "hEllO WoRLD";
        assertEquals("HELLO WORLD", Upper.toUpperCase(str2));
    }

    @Test
    public void toUpperCase3() {
        String str3 = "i aM Thang";
        assertEquals("I AM THANG", Upper.toUpperCase(str3));
    }
}
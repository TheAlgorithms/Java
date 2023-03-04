package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyAtoiTest {
	
    @Test
    void testOne() {
        assertEquals(42, MyAtoi.myAtoi("42"));
    }
  
    @Test
    void testTwo() {
        assertEquals(-42, MyAtoi.myAtoi("   -42"));
    }
  
    @Test
    void testThree() {
        assertEquals(4193, MyAtoi.myAtoi("4193 with words"));
    }
	
    @Test
    void testFour() {
        assertEquals(0, MyAtoi.myAtoi("0"));
    }

    @Test
    void testFive() {
        assertEquals(5678, MyAtoi.myAtoi("5678"));
    }

    @Test
    void testSix() {
        assertEquals(42, MyAtoi.myAtoi("+42"));
    }

    @Test
    void testSeven() {
        assertEquals(0, MyAtoi.myAtoi("  +0   "));
    }
}

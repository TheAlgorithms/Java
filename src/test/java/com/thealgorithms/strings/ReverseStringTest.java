package com.thealgorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseStringTest {
    @Test
    public void reverse() {
        assertEquals("hello world" ,ReverseString.reverse("dlrow olleh"));
        assertEquals("hello world" ,ReverseString.reverse2("dlrow olleh"));
        assertEquals("dlrow olleh" ,ReverseString.reverse("hello world"));
        assertEquals("dlrow olleh" ,ReverseString.reverse2("hello world"));
        assertEquals("!? dlrow olleh !?", ReverseString.reverse("?! hello world ?!"));
        assertEquals("!? dlrow olleh !?", ReverseString.reverse2("?! hello world ?!"));
    }
}

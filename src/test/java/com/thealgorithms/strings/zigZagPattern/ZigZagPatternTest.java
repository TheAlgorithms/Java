package com.thealgorithms.strings.zigZagPattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZigZagPatternTest {

    @Test
    public void testZigZagPattern() {
        String input1 = "HelloWorldFromJava";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals("HooeWrrmalolFJvlda", ZigZagPattern.encode(input1, 4));
        Assertions.assertEquals("jAaLgasPrmgaaevIrgmnnuaoig", ZigZagPattern.encode(input2, 4));
        // Edge cases
        Assertions.assertEquals("ABC", ZigZagPattern.encode("ABC", 1)); // Single row
        Assertions.assertEquals("A", ZigZagPattern.encode("A", 2)); // numRows > length of string
        Assertions.assertEquals("", ZigZagPattern.encode("", 3)); // Empty string
    }
}

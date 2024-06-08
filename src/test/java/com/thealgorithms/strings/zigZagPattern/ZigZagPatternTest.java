package com.thealgorithms.strings.zigZagPattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZigZagPatternTest {

    @Test
    public void palindrome() {
        String input1 = "HelloWorldFromJava";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals(ZigZagPattern.encode(input1, 4), "HooeWrrmalolFJvlda");
        Assertions.assertEquals(ZigZagPattern.encode(input2, 4), "jAaLgasPrmgaaevIrgmnnuaoig");
    }
}

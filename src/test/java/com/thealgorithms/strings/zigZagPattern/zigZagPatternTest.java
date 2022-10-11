package com.thealgorithms.strings.zigZagPattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class zigZagPatternTest {

    @Test
    public void palindrome() {
        String input1 = "HelloWorldFromJava";
        String input2 = "javaIsAProgrammingLanguage";
        Assertions.assertEquals(
            zigZagPattern.encode(input1, 4),
            "HooeWrrmalolFJvlda"
        );
        Assertions.assertEquals(
            zigZagPattern.encode(input2, 4),
            "jAaLgasPrmgaaevIrgmnnuaoig"
        );
    }
}

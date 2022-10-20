package com.thealgorithms.strings;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LowerTests {

    @Test
    public void ToLowerCaseTests() {
        String input1 = "String with VarieTy of Upper and LowerCaSE LETTERS";
        String input2 = "";
        String input3 = "all lowercase letters";
        String input4 = "!@#$5123[2[1]23[";
        String input5 = "A";
        String input6 = "simple";
        String input7 = "ALLCAPS";

        String expectedOutput1 = "string with variety of upper and lowercase letters";
        String expectedOutput2 = "";
        String expectedOutput3 = "all lowercase letters";
        String expectedOutput4 = "!@#$5123[2[1]23[";
        String expectedOutput5 = "a";
        String expectedOutput6 = "simple";
        String expectedOutput7 = "allcaps";

        assertEquals(Lower.toLowerCase(input1), expectedOutput1);
        assertEquals(Lower.toLowerCase(input2), expectedOutput2);
        assertEquals(Lower.toLowerCase(input3), expectedOutput3);
        assertEquals(Lower.toLowerCase(input4), expectedOutput4);
        assertEquals(Lower.toLowerCase(input5), expectedOutput5);
        assertEquals(Lower.toLowerCase(input6), expectedOutput6);
        assertEquals(Lower.toLowerCase(input7), expectedOutput7);
    }
}

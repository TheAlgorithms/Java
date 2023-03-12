package com.thealgorithms.strings;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringCompressionTest {
    @Test
    public void StringCompressionTest(){
        //inputs
        char[] input1={'a'};
        char[] input2={'a','a','b','b','b'};
        char[] input3={'a','b','b','b','c'};
        char[] input4={'a','a','b','c','c','d'};
        //expected outputs
        String output1="a";
        String output2="a2b3";
        String output3="ab3c";
        String output4="a2bc2d";

        assertEquals(StringCompression.compress(input1), output1);
        assertEquals(StringCompression.compress(input2), output2);
        assertEquals(StringCompression.compress(input3), output3);
        assertEquals(StringCompression.compress(input4), output4);
    }
}

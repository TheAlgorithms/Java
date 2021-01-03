package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest extends ReverseWords {

    @Test
    void testReverseWords() {
        Assertions.assertEquals(true, returnReverseWords("this is my car").equals("siht si ym rac"), "Correct");
        Assertions.assertEquals(true, returnReverseWords("ABC 123").equals("CBA 321"), "Correct");

    }
}
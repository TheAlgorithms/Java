package com.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReveresWordsTest {

    @Test
    void testReverseWords() {
        ReverseWords reverseWords = new ReverseWords();
        Assertions.assertEquals(true, reverseWords.returnReverseWords("this is my car").equals("siht si ym rac"), "Correct");
        Assertions.assertEquals(true, reverseWords.returnReverseWords("ABC 123").equals("CBA 321"), "Correct");

    }
}
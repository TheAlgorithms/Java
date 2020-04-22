package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountWordsTest {

    @Test
    void testWordCounter(){
        CountWords counter = new CountWords();
        Assertions.assertEquals(0, counter.wordCount(""), "Incorrect");
        Assertions.assertEquals(0, counter.secondaryWordCount(""), "Incorrect");

        Assertions.assertEquals(1, counter.wordCount("coincidence"), "Incorrect");
        Assertions.assertEquals(1, counter.secondaryWordCount("coincidence"), "Incorrect");

        Assertions.assertEquals(7, counter.wordCount("This is a not so long sentence."), "Incorrect");
        Assertions.assertEquals(7, counter.secondaryWordCount("This is a not so long sentence."), "Incorrect");
    }
}

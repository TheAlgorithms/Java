package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MapReduceTest {
    @Test
    public void testMapReduceWithSingleWordSentence() {
        String oneWordSentence = "Hactober";
        String result = MapReduce.mapreduce(oneWordSentence);

        assertEquals("Hactober: 1", result);
    }

    @Test
    public void testMapReduceWithMultipleWordSentence() {
        String multipleWordSentence = "I Love Love HactoberFest";
        String result = MapReduce.mapreduce(multipleWordSentence);

        assertEquals("I: 1,Love: 2,HactoberFest: 1", result);
    }
}

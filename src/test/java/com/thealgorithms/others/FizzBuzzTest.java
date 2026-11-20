package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzzUpToFifteen() {
        List<String> result = FizzBuzz.fizzBuzz(15);
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("Fizz", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("Buzz", result.get(4));
        assertEquals("Fizz", result.get(5));
        assertEquals("7", result.get(6));
        assertEquals("8", result.get(7));
        assertEquals("Fizz", result.get(8));
        assertEquals("Buzz", result.get(9));
        assertEquals("11", result.get(10));
        assertEquals("Fizz", result.get(11));
        assertEquals("13", result.get(12));
        assertEquals("14", result.get(13));
        assertEquals("FizzBuzz", result.get(14));
    }

    @Test
    public void testSingleElement() {
        assertEquals(List.of("1"), FizzBuzz.fizzBuzz(1));
    }

    @Test
    public void testFizz() {
        assertEquals(List.of("1", "2", "Fizz"), FizzBuzz.fizzBuzz(3));
    }
}

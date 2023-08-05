package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CollatzConjectureTest {

    static CollatzConjecture cConjecture;

    @BeforeAll
    static void setUp() {
        cConjecture = new CollatzConjecture();
    }

    @Test
    void nextNumberFromEvenNumber() {
        assertEquals(25, cConjecture.nextNumber(50));
    }

    @Test
    void nextNumberFromOddNumber() {
        assertEquals(154, cConjecture.nextNumber(51));
    }

    @Test
    void collatzConjecture() {
        final List<Integer> expected = List.of(35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1);
        assertIterableEquals(expected, cConjecture.collatzConjecture(35));
    }

    @Test
    void sequenceOfNotNaturalFirstNumber() {
        assertThrows(IllegalArgumentException.class, () -> cConjecture.collatzConjecture(0));
        assertThrows(IllegalArgumentException.class, () -> cConjecture.collatzConjecture(-1));
    }
}

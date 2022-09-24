package com.thealgorithms.maths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void sequenceToOne() {
        final List<Integer> expected = List.of(
                850, 425, 1276, 638, 319, 958, 479, 1438, 719, 2158, 1079, 3238, 1619, 4858, 2429,
                7288, 3644, 1822, 911, 2734, 1367, 4102, 2051, 6154, 3077, 9232, 4616, 2308, 1154,
                577, 1732, 866, 433, 1300, 650, 325, 976, 488, 244, 122, 61, 184, 92, 46, 23, 70,
                35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1);
        assertIterableEquals(expected, cConjecture.sequenceToOne(850));
    }

    @Test
    void sequenceOfNotNaturalNumbers() {
        assertTrue(cConjecture.sequenceToOne(0).isEmpty());
        assertTrue(cConjecture.sequenceToOne(-1).isEmpty());
    }
}
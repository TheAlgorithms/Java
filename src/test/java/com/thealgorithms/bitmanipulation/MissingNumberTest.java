package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MissingNumberTest {
    @Test
    void testMissingNumber() {
        assertEquals(2, MissingNumber.missingNumber({1,3,4,5,6}));
        assertEquals(37, MissingNumber.missingNumber({34,35,36,38,39}));
        assertEquals(3, MissingNumber.missingNumber({6, 5, 4, 2, 1}));
    }
}

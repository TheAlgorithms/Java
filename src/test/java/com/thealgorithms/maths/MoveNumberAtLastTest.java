package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveNumberAtLastTest {

    @Test
    public void arrayTest() {

        int[] input = {5, 1, 5, 3, 12};
        int target = 5;
        int[] expectedOutput = {1, 3, 12, 5, 5};

        assertEquals(MoveNumberAtLast.moveNumber(input, target), expectedOutput);
    }
}

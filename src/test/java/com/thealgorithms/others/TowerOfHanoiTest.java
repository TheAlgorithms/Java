package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TowerOfHanoiTest {

    @Test
    public void testHanoiWithOneDisc() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(1, "Pole1", "Pole2", "Pole3", result);

        // Expected output for 1 disc
        List<String> expected = List.of("Move 1 from Pole1 to Pole3");
        assertEquals(expected, result);
    }

    @Test
    public void testHanoiWithTwoDiscs() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(2, "Pole1", "Pole2", "Pole3", result);

        // Expected output for 2 discs
        List<String> expected = List.of("Move 1 from Pole1 to Pole2", "Move 2 from Pole1 to Pole3", "Move 1 from Pole2 to Pole3");
        assertEquals(expected, result);
    }

    @Test
    public void testHanoiWithThreeDiscs() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(3, "Pole1", "Pole2", "Pole3", result);

        // Expected output for 3 discs
        List<String> expected = List.of("Move 1 from Pole1 to Pole3", "Move 2 from Pole1 to Pole2", "Move 1 from Pole3 to Pole2", "Move 3 from Pole1 to Pole3", "Move 1 from Pole2 to Pole1", "Move 2 from Pole2 to Pole3", "Move 1 from Pole1 to Pole3");
        assertEquals(expected, result);
    }

    @Test
    public void testHanoiWithZeroDiscs() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(0, "Pole1", "Pole2", "Pole3", result);

        // There should be no moves if there are 0 discs
        assertTrue(result.isEmpty());
    }
}

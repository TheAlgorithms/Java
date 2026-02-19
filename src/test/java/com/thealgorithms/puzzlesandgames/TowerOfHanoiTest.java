package com.thealgorithms.puzzlesandgames;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TowerOfHanoiTest {

    @ParameterizedTest
    @MethodSource("diskCountAndMoveCount")
    void testMoveCountMatchesFormula(int disks, int expectedMoves) {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(disks, "A", "B", "C", result);
        assertEquals(expectedMoves, result.size());
    }

    private static Stream<Arguments> diskCountAndMoveCount() {
        return Stream.of(Arguments.of(1, 1), Arguments.of(2, 3), Arguments.of(3, 7), Arguments.of(4, 15), Arguments.of(5, 31), Arguments.of(10, 1023));
    }

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
    public void testHanoiWithDifferentPoles() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(2, "X", "Y", "Z", result);

        List<String> expected = List.of("Move 1 from X to Y", "Move 2 from X to Z", "Move 1 from Y to Z");
        assertEquals(expected, result);
    }

    @Test
    public void testHanoiWithZeroDiscs() {
        List<String> result = new ArrayList<>();
        TowerOfHanoi.shift(0, "Pole1", "Pole2", "Pole3", result);

        // There should be no moves if there are 0 discs
        assertTrue(result.isEmpty());
    }

    @Test
    public void testHanoiWithNegativeDiscsThrows() {
        List<String> result = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.shift(-1, "Pole1", "Pole2", "Pole3", result));
    }
}

package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TowerOfHanoiTest {

    @ParameterizedTest
    @MethodSource("diskCountAndMoveCount")
    void testMoveCount(int disks, long expectedMoves) {
        assertEquals(expectedMoves, TowerOfHanoi.getMoveCount(disks));
    }

    private static Stream<Arguments> diskCountAndMoveCount() {
        return Stream.of(
            Arguments.of(1, 1L),
            Arguments.of(2, 3L),
            Arguments.of(3, 7L),
            Arguments.of(4, 15L),
            Arguments.of(5, 31L),
            Arguments.of(10, 1023L)
        );
    }

    @ParameterizedTest
    @MethodSource("diskCountAndExpectedMoves")
    void testSolveReturnsCorrectNumberOfMoves(int disks, long expectedMoveCount) {
        List<String> moves = TowerOfHanoi.solve(disks, 'A', 'C', 'B');
        assertEquals(expectedMoveCount, moves.size());
    }

    private static Stream<Arguments> diskCountAndExpectedMoves() {
        return Stream.of(
            Arguments.of(1, 1L),
            Arguments.of(2, 3L),
            Arguments.of(3, 7L),
            Arguments.of(4, 15L),
            Arguments.of(5, 31L)
        );
    }

    @Test
    void testSolveWithOneDisks() {
        List<String> moves = TowerOfHanoi.solve(1, 'A', 'C', 'B');
        assertEquals(1, moves.size());
        assertEquals("Move disk 1 from A to C", moves.get(0));
    }

    @Test
    void testSolveWithTwoDisks() {
        List<String> moves = TowerOfHanoi.solve(2, 'A', 'C', 'B');
        assertEquals(3, moves.size());
        assertEquals("Move disk 1 from A to B", moves.get(0));
        assertEquals("Move disk 2 from A to C", moves.get(1));
        assertEquals("Move disk 1 from B to C", moves.get(2));
    }

    @Test
    void testSolveWithThreeDisks() {
        List<String> moves = TowerOfHanoi.solve(3, 'A', 'C', 'B');
        assertEquals(7, moves.size());
        assertEquals("Move disk 1 from A to C", moves.get(0));
        assertEquals("Move disk 3 from A to C", moves.get(3));
        assertEquals("Move disk 1 from A to C", moves.get(6));
    }

    @Test
    void testSolveWithDifferentPegs() {
        List<String> moves = TowerOfHanoi.solve(2, 'X', 'Z', 'Y');
        assertEquals(3, moves.size());
        assertEquals("Move disk 1 from X to Y", moves.get(0));
        assertEquals("Move disk 2 from X to Z", moves.get(1));
        assertEquals("Move disk 1 from Y to Z", moves.get(2));
    }

    @Test
    void testThrowsForNegativeDisks() {
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.solve(-1, 'A', 'C', 'B'));
    }

    @Test
    void testThrowsForZeroDisks() {
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.solve(0, 'A', 'C', 'B'));
    }

    @Test
    void testGetMoveCountThrowsForNegativeDisks() {
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.getMoveCount(-1));
    }

    @Test
    void testGetMoveCountThrowsForZeroDisks() {
        assertThrows(IllegalArgumentException.class, () -> TowerOfHanoi.getMoveCount(0));
    }

    @ParameterizedTest
    @MethodSource("diskCountAndExpectedMoves")
    void testSolveMoveCountMatchesGetMoveCount(int disks, long expectedMoveCount) {
        List<String> moves = TowerOfHanoi.solve(disks, 'A', 'C', 'B');
        long moveCount = TowerOfHanoi.getMoveCount(disks);
        assertEquals(moveCount, moves.size());
        assertEquals(expectedMoveCount, moveCount);
    }

    @Test
    void testSolveIsNotEmpty() {
        List<String> moves = TowerOfHanoi.solve(1, 'A', 'C', 'B');
        assertEquals(false, moves.isEmpty());
    }
}

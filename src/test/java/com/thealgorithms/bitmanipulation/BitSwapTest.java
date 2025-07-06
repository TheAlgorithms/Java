package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BitSwapTest {

    @ParameterizedTest(name = "Additional cases: data={0}, posA={1}, posB={2} -> expected={3}")
    @MethodSource("provideAdditionalCases")
    void testAdditionalCases(int data, int posA, int posB, int expected) {
        assertEquals(expected, BitSwap.bitSwap(data, posA, posB));
    }

    @ParameterizedTest(name = "Swap different bits: data={0}, posA={1}, posB={2} -> expected={3}")
    @MethodSource("provideDifferentBitsCases")
    void swapDifferentBits(int data, int posA, int posB, int expected) {
        assertEquals(expected, BitSwap.bitSwap(data, posA, posB));
    }

    @ParameterizedTest(name = "Swap same bits: data={0}, posA={1}, posB={2} should not change")
    @MethodSource("provideSameBitsCases")
    void swapSameBits(int data, int posA, int posB) {
        assertEquals(data, BitSwap.bitSwap(data, posA, posB));
    }

    @ParameterizedTest(name = "Edge cases: data={0}, posA={1}, posB={2} -> expected={3}")
    @MethodSource("provideEdgeCases")
    void testEdgeCases(int data, int posA, int posB, int expected) {
        assertEquals(expected, BitSwap.bitSwap(data, posA, posB));
    }

    @ParameterizedTest(name = "Invalid positions: data={0}, posA={1}, posB={2} should throw")
    @MethodSource("provideInvalidPositions")
    void invalidPositionThrowsException(int data, int posA, int posB) {
        assertThrows(IllegalArgumentException.class, () -> BitSwap.bitSwap(data, posA, posB));
    }

    static Stream<Arguments> provideAdditionalCases() {
        return Stream.of(Arguments.of(3, 0, 1, 3), Arguments.of(6, 0, 1, 5), Arguments.of(7, 1, 1, 7));
    }

    static Stream<Arguments> provideDifferentBitsCases() {
        return Stream.of(Arguments.of(0b01, 0, 1, 0b10));
    }

    static Stream<Arguments> provideSameBitsCases() {
        return Stream.of(Arguments.of(0b111, 0, 2), Arguments.of(0b0, 1, 3), Arguments.of(0b1010, 1, 3), Arguments.of(-1, 5, 5));
    }

    static Stream<Arguments> provideEdgeCases() {
        return Stream.of(Arguments.of(Integer.MIN_VALUE, 31, 0, 1), Arguments.of(0, 0, 31, 0));
    }

    static Stream<Arguments> provideInvalidPositions() {
        return Stream.of(Arguments.of(0, -1, 0), Arguments.of(0, 0, 32), Arguments.of(0, -5, 33), Arguments.of(0, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}

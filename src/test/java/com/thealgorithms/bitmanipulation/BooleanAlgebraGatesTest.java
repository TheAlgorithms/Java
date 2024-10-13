package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.ANDGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.BooleanGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.NANDGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.NORGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.NOTGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.ORGate;
import com.thealgorithms.bitmanipulation.BooleanAlgebraGates.XORGate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BooleanAlgebraGatesTest {

    @ParameterizedTest(name = "ANDGate Test Case {index}: inputs={0} -> expected={1}")
    @MethodSource("provideAndGateTestCases")
    void testANDGate(List<Boolean> inputs, boolean expected) {
        BooleanGate gate = new ANDGate();
        assertEquals(expected, gate.evaluate(inputs));
    }

    @ParameterizedTest(name = "ORGate Test Case {index}: inputs={0} -> expected={1}")
    @MethodSource("provideOrGateTestCases")
    void testORGate(List<Boolean> inputs, boolean expected) {
        BooleanGate gate = new ORGate();
        assertEquals(expected, gate.evaluate(inputs));
    }

    @ParameterizedTest(name = "NOTGate Test Case {index}: input={0} -> expected={1}")
    @CsvSource({"true, false", "false, true"})
    void testNOTGate(boolean input, boolean expected) {
        NOTGate gate = new NOTGate();
        assertEquals(expected, gate.evaluate(input));
    }

    @ParameterizedTest(name = "XORGate Test Case {index}: inputs={0} -> expected={1}")
    @MethodSource("provideXorGateTestCases")
    void testXORGate(List<Boolean> inputs, boolean expected) {
        BooleanGate gate = new XORGate();
        assertEquals(expected, gate.evaluate(inputs));
    }

    @ParameterizedTest(name = "NANDGate Test Case {index}: inputs={0} -> expected={1}")
    @MethodSource("provideNandGateTestCases")
    void testNANDGate(List<Boolean> inputs, boolean expected) {
        BooleanGate gate = new NANDGate();
        assertEquals(expected, gate.evaluate(inputs));
    }

    @ParameterizedTest(name = "NORGate Test Case {index}: inputs={0} -> expected={1}")
    @MethodSource("provideNorGateTestCases")
    void testNORGate(List<Boolean> inputs, boolean expected) {
        BooleanGate gate = new NORGate();
        assertEquals(expected, gate.evaluate(inputs));
    }

    // Helper methods to provide test data for each gate

    static Stream<Object[]> provideAndGateTestCases() {
        return Stream.of(new Object[] {Arrays.asList(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE), Boolean.TRUE}, new Object[] {Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE), Boolean.FALSE}, new Object[] {Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE), Boolean.FALSE},
            new Object[] {Collections.emptyList(), Boolean.TRUE} // AND over no inputs is true
        );
    }

    static Stream<Object[]> provideOrGateTestCases() {
        return Stream.of(new Object[] {Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE), Boolean.TRUE}, new Object[] {Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE), Boolean.FALSE}, new Object[] {Arrays.asList(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE), Boolean.TRUE},
            new Object[] {Collections.emptyList(), Boolean.FALSE} // OR over no inputs is false
        );
    }

    static Stream<Object[]> provideXorGateTestCases() {
        return Stream.of(new Object[] {Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE), Boolean.FALSE}, // XOR over odd true
            new Object[] {Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE), Boolean.TRUE}, // XOR over single true
            new Object[] {Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE), Boolean.FALSE}, // XOR over all false
            new Object[] {Arrays.asList(Boolean.TRUE, Boolean.TRUE), Boolean.FALSE} // XOR over even true
        );
    }

    static Stream<Object[]> provideNandGateTestCases() {
        return Stream.of(new Object[] {Arrays.asList(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE), Boolean.FALSE}, // NAND of all true is false
            new Object[] {Arrays.asList(Boolean.TRUE, Boolean.FALSE), Boolean.TRUE}, // NAND with one false is true
            new Object[] {Arrays.asList(Boolean.FALSE, Boolean.FALSE), Boolean.TRUE}, // NAND of all false is true
            new Object[] {Collections.emptyList(), Boolean.FALSE} // NAND over no inputs is false (negation of AND)
        );
    }

    static Stream<Object[]> provideNorGateTestCases() {
        return Stream.of(new Object[] {Arrays.asList(Boolean.FALSE, Boolean.FALSE), Boolean.TRUE}, // NOR of all false is true
            new Object[] {Arrays.asList(Boolean.FALSE, Boolean.TRUE), Boolean.FALSE}, // NOR with one true is false
            new Object[] {Arrays.asList(Boolean.TRUE, Boolean.TRUE), Boolean.FALSE}, // NOR of all true is false
            new Object[] {Collections.emptyList(), Boolean.TRUE} // NOR over no inputs is true (negation of OR)
        );
    }
}

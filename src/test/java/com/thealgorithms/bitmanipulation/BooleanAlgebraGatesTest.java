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
        return Stream.of(new Object[]{Arrays.asList(true, true, true), true},new Object[]{Arrays.asList(true, false, true), false},
        new Object[]{Arrays.asList(false, false, false), false},
        new Object[]{Collections.emptyList(), true} // AND over no inputs is true
        );
    }

    static Stream<Object[]> provideOrGateTestCases() {
        return Stream.of(new Object[]{Arrays.asList(true, false, false), true},
        new Object[]{Arrays.asList(false, false, false), false},
        new Object[]{Arrays.asList(true, true, true), true},
        new Object[]{Collections.emptyList(), false} // OR over no inputs is false
        );
    }

    static Stream<Object[]> provideXorGateTestCases() {
        return Stream.of(new Object[]{Arrays.asList(true, false, true), false}, // XOR over odd true
            new Object[]{Arrays.asList(true, false, false), true},  // XOR over single true
            new Object[]{Arrays.asList(false, false, false), false},// XOR over all false
            new Object[]{Arrays.asList(true, true), false}          // XOR over even true
        );
    }

    static Stream<Object[]> provideNandGateTestCases() {
        return Stream.of(new Object[]{Arrays.asList(true, true, true), false},   // NAND of all true is false
            new Object[]{Arrays.asList(true, false), true},         // NAND with one false is true
            new Object[]{Arrays.asList(false, false), true},        // NAND of all false is true
            new Object[]{Collections.emptyList(), false}            // NAND over no inputs is false (negation of AND)
        );
    }

    static Stream<Object[]> provideNorGateTestCases() {
        return Stream.of(new Object[]{Arrays.asList(false, false),true},        // NOR of all false is true
            new Object[]{Arrays.asList(false, true), false},        // NOR with one true is false
            new Object[]{Arrays.asList(true, true), false},         // NOR of all true is false
            new Object[]{Collections.emptyList(), true}             // NOR over no inputs is true (negation of OR)
        );
    }
}

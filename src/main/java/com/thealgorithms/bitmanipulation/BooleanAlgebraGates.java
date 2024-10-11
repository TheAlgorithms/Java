package com.thealgorithms.bitmanipulation;

import java.util.List;

/**
 * Implements various Boolean algebra gates (AND, OR, NOT, XOR, NAND, NOR).
 * This class provides the logic for fundamental Boolean operations that can be 
 * used in various computational contexts.
 */
public final class BooleanAlgebraGates {

    // Private constructor to prevent instantiation
    private BooleanAlgebraGates() {}

    /**
     * Interface representing a Boolean gate that takes multiple inputs and returns a result.
     */
    interface BooleanGate {
        /**
         * Evaluates the Boolean gate with the provided inputs.
         *
         * @param inputs a list of Boolean values representing the gate inputs.
         * @return the result of the Boolean operation.
         */
        boolean evaluate(List<Boolean> inputs);
    }

    /**
     * AND Gate implementation.
     * Returns true if all inputs are true; otherwise, returns false.
     *
     * <p>Test cases for AND Gate:</p>
     * <ul>
     *     <li>AND([true, true]) should return true.</li>
     *     <li>AND([true, false]) should return false.</li>
     *     <li>AND([false, false]) should return false.</li>
     *     <li>AND([true, true, true]) should return true.</li>
     *     <li>AND([true, false, true]) should return false.</li>
     * </ul>
     *
     * <p>Edge case (empty input list):</p>
     * <ul>
     *     <li>AND([]) can either return true or throw an exception depending on your design.</li>
     * </ul>
     */
    static class ANDGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            for (boolean input : inputs) {
                if (!input) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * OR Gate implementation.
     * Returns true if at least one input is true; otherwise, returns false.
     *
     * <p>Test cases for OR Gate:</p>
     * <ul>
     *     <li>OR([true, false]) should return true.</li>
     *     <li>OR([false, false]) should return false.</li>
     *     <li>OR([true, true, false]) should return true.</li>
     *     <li>OR([false, false, false]) should return false.</li>
     * </ul>
     *
     * <p>Edge case (empty input list):</p>
     * <ul>
     *     <li>OR([]) can either return false or throw an exception.</li>
     * </ul>
     */
    static class ORGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            for (boolean input : inputs) {
                if (input) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * NOT Gate implementation (Unary operation).
     * Only accepts a single input and returns the negation.
     *
     * <p>Test cases for NOT Gate:</p>
     * <ul>
     *     <li>NOT(true) should return false.</li>
     *     <li>NOT(false) should return true.</li>
     * </ul>
     *
     * <p>Edge case:</p>
     * <ul>
     *     <li>Not applicable, as NOT is a unary operation and requires a single input.</li>
     * </ul>
     */
    static class NOTGate {
        /**
         * Evaluates the negation of a single input.
         *
         * @param input The input value to be negated.
         * @return The negation of the input.
         */
        public boolean evaluate(boolean input) {
            return !input;
        }
    }

    /**
     * XOR Gate implementation.
     * Returns true if an odd number of inputs are true; otherwise, returns false.
     *
     * <p>Test cases for XOR Gate:</p>
     * <ul>
     *     <li>XOR([true, false]) should return true.</li>
     *     <li>XOR([true, true]) should return false.</li>
     *     <li>XOR([false, false]) should return false.</li>
     *     <li>XOR([true, true, true]) should return true.</li>
     *     <li>XOR([true, false, true]) should return false.</li>
     * </ul>
     *
     * <p>Edge case:</p>
     * <ul>
     *     <li>XOR([]) can either return false or throw an exception.</li>
     * </ul>
     */
    static class XORGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            boolean result = false;
            for (boolean input : inputs) {
                result ^= input;
            }
            return result;
        }
    }

    /**
     * NAND Gate implementation.
     * Returns true if at least one input is false; otherwise, returns false.
     *
     * <p>Test cases for NAND Gate:</p>
     * <ul>
     *     <li>NAND([true, true]) should return false.</li>
     *     <li>NAND([true, false]) should return true.</li>
     *     <li>NAND([false, false]) should return true.</li>
     *     <li>NAND([true, true, true]) should return false.</li>
     *     <li>NAND([true, true, false]) should return true.</li>
     * </ul>
     *
     * <p>Edge case:</p>
     * <ul>
     *     <li>NAND([]) can either return true or throw an exception.</li>
     * </ul>
     */
    static class NANDGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            return !new ANDGate().evaluate(inputs); // Equivalent to negation of AND
        }
    }

    /**
     * NOR Gate implementation.
     * Returns true if all inputs are false; otherwise, returns false.
     *
     * <p>Test cases for NOR Gate:</p>
     * <ul>
     *     <li>NOR([true, false]) should return false.</li>
     *     <li>NOR([false, false]) should return true.</li>
     *     <li>NOR([false, false, false]) should return true.</li>
     *     <li>NOR([true, true, false]) should return false.</li>
     * </ul>
     *
     * <p>Edge case:</p>
     * <ul>
     *     <li>NOR([]) can either return true or throw an exception.</li>
     * </ul>
     */
    static class NORGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            return !new ORGate().evaluate(inputs); // Equivalent to negation of OR
        }
    }

    /**
     * Edge Cases and Special Scenarios:
     * 
     * <p>1. Empty input list:</p>
     * <ul>
     *    <li>Test handling of empty input lists for multi-input gates.</li>
     *    <li>Should throw an exception if the design assumes an empty list is invalid.</li>
     * </ul>
     * 
     * <p>2. Single input for multi-input gates:</p>
     * <ul>
     *    <li>AND([true]) -> true</li>
     *    <li>OR([false]) -> false</li>
     *    <li>XOR([true]) -> true</li>
     *    <li>Test behavior with single input as a corner case.</li>
     * </ul>
     * 
     * <p>3. Mixed inputs:</p>
     * <ul>
     *    <li>AND([true, false, false, true]) -> false</li>
     *    <li>Similar tests for OR, XOR, and other gates.</li>
     * </ul>
     * 
     * <p>4. Large input lists:</p>
     * <ul>
     *    <li>AND([true, true, ..., true]) with 1,000 true values -> true.</li>
     *    <li>OR with mostly false and one true -> true.</li>
     * </ul>
     * 
     * <p>5. Randomized tests:</p>
     * <ul>
     *    <li>Generate random true/false input lists.</li>
     *    <li>Validate expected gate outputs, especially for XOR.</li>
     * </ul>
     * 
     * <p>6. Invalid input handling:</p>
     * <ul>
     *    <li>Test behavior when null is passed as input or within the list.</li>
     *    <li>Should either throw an exception or handle per defined behavior.</li>
     * </ul>
     */
}

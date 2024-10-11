package com.thealgorithms.bitmanipulation;

import java.util.List;

/**
 * Implements various Boolean algebra gates (AND, OR, NOT, XOR, NAND, NOR).
 */
public final class BooleanAlgebraGates {

    private BooleanAlgebraGates() {
        // Private constructor to prevent instantiation
    }

    /**
     * Interface representing a Boolean gate that takes multiple inputs and returns a result.
     */
    interface BooleanGate {
        boolean evaluate(List<Boolean> inputs);
    }

    /**
     * AND Gate implementation.
     * Returns true if all inputs are true; otherwise, false.
     *
     * Test cases for AND Gate:
     * - AND([true, true]) should return true.
     * - AND([true, false]) should return false.
     * - AND([false, false]) should return false.
     * - AND([true, true, true]) should return true.
     * - AND([true, false, true]) should return false.
     * 
     * Edge case (empty input list):
     * - AND([]) can either return true or throw an exception depending on your design.
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
     * Returns true if at least one input is true; otherwise, false.
     *
     * Test cases for OR Gate:
     * - OR([true, false]) should return true.
     * - OR([false, false]) should return false.
     * - OR([true, true, false]) should return true.
     * - OR([false, false, false]) should return false.
     * 
     * Edge case (empty input list):
     * - OR([]) can either return false or throw an exception.
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
     * Test cases for NOT Gate:
     * - NOT(true) should return false.
     * - NOT(false) should return true.
     *
     * Edge case:
     * Not applicable, as NOT is a unary operation and requires a single input.
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
     * Returns true if an odd number of inputs are true; otherwise, false.
     *
     * Test cases for XOR Gate:
     * - XOR([true, false]) should return true.
     * - XOR([true, true]) should return false.
     * - XOR([false, false]) should return false.
     * - XOR([true, true, true]) should return true.
     * - XOR([true, false, true]) should return false.
     * 
     * Edge case:
     * - XOR([]) can either return false or throw an exception.
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
     * Returns true if at least one input is false; otherwise, false.
     *
     * Test cases for NAND Gate:
     * - NAND([true, true]) should return false.
     * - NAND([true, false]) should return true.
     * - NAND([false, false]) should return true.
     * - NAND([true, true, true]) should return false.
     * - NAND([true, true, false]) should return true.
     * 
     * Edge case:
     * - NAND([]) can either return true or throw an exception.
     */
    static class NANDGate implements BooleanGate {
        @Override
        public boolean evaluate(List<Boolean> inputs) {
            return !new ANDGate().evaluate(inputs); // Equivalent to negation of AND
        }
    }

    /**
     * NOR Gate implementation.
     * Returns true if all inputs are false; otherwise, false.
     *
     * Test cases for NOR Gate:
     * - NOR([true, false]) should return false.
     * - NOR([false, false]) should return true.
     * - NOR([false, false, false]) should return true.
     * - NOR([true, true, false]) should return false.
     * 
     * Edge case:
     * - NOR([]) can either return true or throw an exception.
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
     * 1. Empty input list:
     *    - Test handling of empty input lists for multi-input gates.
     *    - Should throw an exception if the design assumes an empty list is invalid.
     * 
     * 2. Single input for multi-input gates:
     *    - AND([true]) -> true
     *    - OR([false]) -> false
     *    - XOR([true]) -> true
     *    - Test behavior with single input as a corner case.
     * 
     * 3. Mixed inputs:
     *    - AND([true, false, false, true]) -> false
     *    - Similar tests for OR, XOR, and other gates.
     * 
     * 4. Large input lists:
     *    - AND([true, true, ..., true]) with 1,000 true values -> true.
     *    - OR with mostly false and one true -> true.
     * 
     * 5. Randomized tests:
     *    - Generate random true/false input lists.
     *    - Validate expected gate outputs, especially for XOR.
     * 
     * 6. Invalid input handling:
     *    - Test behavior when null is passed as input or within the list.
     *    - Should either throw an exception or handle per defined behavior.
     */
}

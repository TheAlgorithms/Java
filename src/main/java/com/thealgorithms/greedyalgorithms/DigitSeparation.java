package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides methods to separate the digits of a large positive number into a list.
 */
public class DigitSeparation {
    public DigitSeparation() {
    }
    /**
     * Separates the digits of a large positive number into a list in reverse order.
     * @param largeNumber The large number to separate digits from.
     * @return A list of digits in reverse order.
     */
    public List<Long> digitSeparationReverseOrder(long largeNumber) {
        List<Long> result = new ArrayList<>();
        if (largeNumber != 0) {
            while (largeNumber != 0) {
                result.add(Math.abs(largeNumber % 10));
                largeNumber = largeNumber / 10;
            }
        } else {
            result.add(0L);
        }
        return result;
    }
    /**
     * Separates the digits of a large positive number into a list in forward order.
     * @param largeNumber The large number to separate digits from.
     * @return A list of digits in forward order.
     */
    public List<Long> digitSeparationForwardOrder(long largeNumber) {
        List<Long> result = this.digitSeparationReverseOrder(largeNumber);
        Collections.reverse(result);
        return result;
    }
}

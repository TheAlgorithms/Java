package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Calculates the stock span for each day in a series of stock prices.
 *
 * <p>The span of a price on a given day is the number of consecutive days ending on that day
 * for which the price was less than or equal to the current day's price.
 *
 */
public final class StockSpanProblem {
    private StockSpanProblem() {
    }

    /**
     * Calculates the stock span for each price in the input array.
     *
     * @param prices the stock prices
     * @return the span for each day
     * @throws IllegalArgumentException if the input array is null
     */
    public static int[] calculateSpan(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Input prices cannot be null");
        }

        int[] spans = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int index = 0; index < prices.length; index++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[index]) {
                stack.pop();
            }

            spans[index] = stack.isEmpty() ? index + 1 : index - stack.peek();
            stack.push(index);
        }

        return spans;
    }
}

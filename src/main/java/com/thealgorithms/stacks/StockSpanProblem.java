package com.thealgorithms.stacks;

import java.util.Stack;

/**
 * Calculates the stock span for each day in a series of stock prices.
 *
 * <p>The span of a price on a given day is the number of consecutive days ending on that day
 * for which the price was less than or equal to the current day's price.
 *
 * <p>Idea: keep a stack of indices whose prices are strictly greater than the current price.
 * While processing each day, pop smaller or equal prices because they are part of the current
 * span. After popping, the nearest greater price left on the stack tells us where the span stops.
 *
 * <p>Time complexity is O(n) because each index is pushed onto the stack once and popped at most
 * once, so the total number of stack operations grows linearly with the number of prices. This
 * makes the stack approach efficient because it avoids rechecking earlier days repeatedly, unlike
 * a naive nested-loop solution that can take O(n^2) time.
 *
 * <p>Example: for prices [100, 80, 60, 70, 60, 75, 85], the spans are
 * [1, 1, 1, 2, 1, 4, 6].
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

        // Small example:
        // prices = [100, 80, 60, 70]
        // spans  = [  1,  1,  1,  2]
        // When we process 70, we pop 60 because 60 <= 70, so the span becomes 2.
        //
        // The stack stores indices of days with prices greater than the current day's price.
        for (int index = 0; index < prices.length; index++) {
            // Remove all previous days whose prices are less than or equal to the current price.
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[index]) {
                stack.pop();
            }

            // If the stack is empty, there is no earlier day with a greater price,
            // so the count will be from day 0 to this day (index + 1).
            //
            // Otherwise, the span is the number of days between
            // the nearest earlier day with a greater price and the current day.
            spans[index] = stack.isEmpty() ? index + 1 : index - stack.peek();

            // Store the current index as a candidate for future span calculations.
            stack.push(index);
        }

        return spans;
    }
}

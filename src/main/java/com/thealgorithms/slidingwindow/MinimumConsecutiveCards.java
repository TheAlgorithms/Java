package com.thealgorithms.slidingwindow;

import java.util.HashMap;

/**
 * The MinimumConsecutiveCards algorithm finds the minimum number of consecutive cards
 * that need to be picked up to get a pair of the same card.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(n)
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public final class MinimumConsecutiveCards {

    // Prevent instantiation
    private MinimumConsecutiveCards() {
    }

    /**
     * This method finds the minimum number of consecutive cards needed to be picked up
     * to get a pair of the same card.
     *
     * @param cards an array of integers representing card numbers
     * @return the minimum number of consecutive cards to pick up, or -1 if no pairs exist
     */
    public static int minimumCardPickup(int[] cards) {
        HashMap<Integer, Integer> cardIndexMap = new HashMap<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            if (cardIndexMap.containsKey(cards[i])) {
                int prevIndex = cardIndexMap.get(cards[i]);
                minLength = Math.min(minLength, i - prevIndex + 1);
            }
            cardIndexMap.put(cards[i], i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}

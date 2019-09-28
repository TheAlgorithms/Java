package DynamicProgramming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoinChangeTestData {
    /**
     * All-Du path with var coint 1-2-3-5-6-7-8-11
     */
    @Test
    public void testCoint() {
        int amount = 1;
        int[] coins = { 1, 4, 5 };
        assertEquals(CoinChange.minimumCoins(coins, amount), 1);
    }

    /**
     * All-Du path with var amouont 1-2-3-5-11
     */
    @Test
    public void testAmount() {
        int amount = -1;
        int[] coins = { 2, 4, 5 };
        try {
            CoinChange.minimumCoins(coins, amount);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * All-Du path with var minimumCoins 1-2-3-4-5-6-7-8-9-10-11
     */
    @Test
    public void testMinimumCoins() {
        int amount = 12;
        int[] coins = { 2, 4, 5 };
        int num = CoinChange.minimumCoins(coins, amount);
        assertEquals(3, num);
    }
}

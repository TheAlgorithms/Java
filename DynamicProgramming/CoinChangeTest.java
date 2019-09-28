package DynamicProgramming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoinChangeTest {
    /**
     * Số tiền ngoài biên (âm hoặc vô cực)
     */
    @Test
    public void wrongAmount() {
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
     * Có loại tiền ngoài biên (âm hoặc cô cực)
     */
    @Test
    public void wrongCoin() {
        int amount = 1;
        int[] coins = { -1, 4, 5 };
        try {
            CoinChange.minimumCoins(coins, amount);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Không có tổng loại tiền nào có thể tạo được số tiền
     * (Số tiền nhỏ hơn các loại tiền hoặc không tạo được tổng)
     */
    @Test
    public void noSolution() {
        int amount = 7;
        int[] coins = { 3, 5, 6 };
        int num = CoinChange.minimumCoins(coins, amount);
        assertNotEquals(0, num);
    }

    /**
     * Tìm thấy solution
     */
    @Test
    public void haveolution() {
        int amount = 12;
        int[] coins = { 2, 4, 5 };
        int num = CoinChange.minimumCoins(coins, amount);
        assertEquals(3, num);
    }
}

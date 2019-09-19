package DynamicProgramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

class CoinChangeTest {

	@Test
	void testMinimumCoins() {
        int[] coins = {0};
        Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
        Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 1;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
		Assert.assertEquals(0, CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals(100, CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals(Integer.MAX_VALUE - 1, CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(Integer.MAX_VALUE, CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 2;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
		Assert.assertEquals(0, CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals(50, CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals((Integer.MAX_VALUE - 1)/2, CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals((Integer.MAX_VALUE)/2, CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 100;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
		Assert.assertEquals(0, CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals(21474837, CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(21474837, CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE - 1;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
		Assert.assertEquals(0, CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(2, CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
		Assert.assertEquals(0, CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(1, CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE + 1;
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, -1));
        Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 0));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 1));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, 100));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", CoinChange.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
	}

}
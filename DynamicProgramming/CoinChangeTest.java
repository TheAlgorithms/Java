package DynamicProgramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

class CoinChangeTest {

	@Test
	void testMinimumCoins() {
        int[] coins = {0};
        Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
        Assert.assertEquals("invalid input!", main.minimumCoins(coins, 0));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, 1));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, 100));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 1;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
		Assert.assertEquals(0, main.minimumCoins(coins, 0));
		Assert.assertEquals(1, main.minimumCoins(coins, 1));
		Assert.assertEquals(100, main.minimumCoins(coins, 100));
		Assert.assertEquals(Integer.MAX_VALUE - 1, main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(Integer.MAX_VALUE, main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 2;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
		Assert.assertEquals(0, main.minimumCoins(coins, 0));
		Assert.assertEquals(1, main.minimumCoins(coins, 1));
		Assert.assertEquals(50, main.minimumCoins(coins, 100));
		Assert.assertEquals((Integer.MAX_VALUE - 1)/2, main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals((Integer.MAX_VALUE)/2, main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = 100;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
		Assert.assertEquals(0, main.minimumCoins(coins, 0));
		Assert.assertEquals(1, main.minimumCoins(coins, 1));
		Assert.assertEquals(1, main.minimumCoins(coins, 100));
		Assert.assertEquals(21474837, main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(21474837, main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE - 1;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
		Assert.assertEquals(0, main.minimumCoins(coins, 0));
		Assert.assertEquals(1, main.minimumCoins(coins, 1));
		Assert.assertEquals(1, main.minimumCoins(coins, 100));
		Assert.assertEquals(1, main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(2, main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
		Assert.assertEquals(0, main.minimumCoins(coins, 0));
		Assert.assertEquals(1, main.minimumCoins(coins, 1));
		Assert.assertEquals(1, main.minimumCoins(coins, 100));
		Assert.assertEquals(1, main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals(1, main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
		coins[0] = Integer.MAX_VALUE + 1;
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, -1));
        Assert.assertEquals("invalid input!", main.minimumCoins(coins, 0));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, 1));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, 100));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE - 1));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE));
		Assert.assertEquals("invalid input!", main.minimumCoins(coins, Integer.MAX_VALUE + 1));
		
	}

}
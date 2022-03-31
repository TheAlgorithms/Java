package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.CountFriendsPairing;
public class CountFriendsPairingTest {
    @Test
	void testForOneElement() 
	{
        int a[] = {1,2,2};
		assertTrue(CountFriendsPairing.countFriendsPairing(3,a));
	}

	@Test
	void testForTwoElements() 
	{
        int a[] = {1,2,2,3};
		assertTrue(CountFriendsPairing.countFriendsPairing(4,a));
	}

	@Test
	void testForThreeElements() 
	{
        int a[] = {1,2,2,3,3};
		assertTrue(CountFriendsPairing.countFriendsPairing(5,a));
	}

	@Test
	void testForFourElements() 
	{
        int a[] = {1,2,2,3,3,4};
		assertTrue(CountFriendsPairing.countFriendsPairing(6,a));
	}

	@Test
	void testForFiveElements() 
	{
        int a[] = {1,2,2,3,3,4,4};
		assertTrue(CountFriendsPairing.countFriendsPairing(7,a));
	}

	@Test
	void testForSixElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4};
		assertTrue(CountFriendsPairing.countFriendsPairing(8,a));
	}

	@Test
	void testForSevenElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5};
		assertTrue(CountFriendsPairing.countFriendsPairing(9,a));
    }

	@Test
	void testForEightElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5,5};
		assertTrue(CountFriendsPairing.countFriendsPairing(10,a));
	}
}
package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.dynamicprogramming.CountFriendsPairing;
public class CountFriendsPairingTest {
    @Test
	void testForOneElement() 
	{
        int a[] = {1,2,2};
		assertTrue(CountFriendsPairing.CountFriendsPairing(3,a));
	}

	@Test
	void testForTwoElements() 
	{
        int a[] = {1,2,2,3};
		assertTrue(CountFriendsPairing.CountFriendsPairing(4,a));
	}

	@Test
	void testForThreeElements() 
	{
        int a[] = {1,2,2,3,3};
		assertTrue(CountFriendsPairing.CountFriendsPairing(5,a));
	}

	@Test
	void testForFourElements() 
	{
        int a[] = {1,2,2,3,3,4};
		assertTrue(CountFriendsPairing.CountFriendsPairing(6,a));
	}

	@Test
	void testForFiveElements() 
	{
        int a[] = {1,2,2,3,3,4,4};
		assertTrue(CountFriendsPairing.CountFriendsPairing(7,a));
	}

	@Test
	void testForSixElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4};
		assertTrue(CountFriendsPairing.CountFriendsPairing(8,a));
	}

	@Test
	void testForSevenElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5};
		assertTrue(CountFriendsPairing.CountFriendsPairing(9,a));
    }

	@Test
	void testForEightElements() 
	{
        int a[] = {1,2,2,3,3,4,4,4,5,5};
		assertTrue(CountFriendsPairing.CountFriendsPairing(10,a));
	}
}
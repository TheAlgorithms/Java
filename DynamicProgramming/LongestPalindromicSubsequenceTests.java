package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LongestPalindromicSubsequenceTests {

	@Test
	void test1() {
		assertEquals(LongestPalindromicSubsequence.LPS(""), "");
	}
	
	@Test
	void test2() {
		assertEquals(LongestPalindromicSubsequence.LPS("A"), "A");
	}
	
	@Test
	void test3() {
		assertEquals(LongestPalindromicSubsequence.LPS("BABCBAB"), "BABCBAB");
	}

	@Test
	void test4() {
		assertEquals(LongestPalindromicSubsequence.LPS("BBABCBCAB"), "BABCBAB");
	}
	
	@Test
	void test5() {
		assertEquals(LongestPalindromicSubsequence.LPS("AAAAAAAAAAAAAAAAAAAAAAAA"), "AAAAAAAAAAAAAAAAAAAAAAAA");
	}
}

package me.coldgod.st;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FibonacciTest {

	@SuppressWarnings("static-access")
	@Test
	void test00() {
		Test1 fibTest = new Test1();
		int actual = fibTest.fibBotUp(0);
		assertEquals(0, actual); 
	}
	
	@SuppressWarnings("static-access")
	@Test
	void test01() {
		Test1 fibTest = new Test1();
		int actual = fibTest.fibBotUp(2);
		assertEquals(1, actual); 
	}
	
	@SuppressWarnings("static-access")
	@Test
	void test02() {
		Test1 fibTest = new Test1();
		int actual = fibTest.fibBotUp(5);
		assertEquals(5, actual); 
	}

	@SuppressWarnings("static-access")
	@Test
	void test03() {
		Test1 fibTest = new Test1();
		int actual = fibTest.fibBotUp(7);
		assertEquals(13, actual); 
	}
}

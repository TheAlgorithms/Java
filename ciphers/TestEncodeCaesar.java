package ciphers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEncodeCaesar {

	@Test
	void test1() {
		Caesar c = new Caesar();
		String result = c.encode("", -1);
		assertEquals("", result);
	}
	
	void test2() {
		Caesar c = new Caesar();
		String result = c.encode("", 0);
		assertEquals("", result);
	}
	
	@Test
	void test3() {
		Caesar c = new Caesar();
		String result = c.encode("", 1);
		assertEquals("", result);
	}
	
	@Test
	void test4() {
		Caesar c = new Caesar();
		String result = c.encode("a", -1);
		assertEquals("`", result);
	}
	
	@Test
	void test5() {
		Caesar c = new Caesar();
		String result = c.encode("a", 0);
		assertEquals("a", result);
	}
	
	@Test
	void test6() {
		Caesar c = new Caesar();
		String result = c.encode("a", 1);
		assertEquals("b", result);
	}
	
	@Test
	void test7() {
		Caesar c = new Caesar();
		String result = c.encode(" ", -1);
		assertEquals(" ", result);
	}
	
	@Test
	void test8() {
		Caesar c = new Caesar();
		String result = c.encode(" ", 0);
		assertEquals(" ", result);
	}
	
	@Test
	void test9() {
		Caesar c = new Caesar();
		String result = c.encode(" ", 1);
		assertEquals(" ", result);
	}
	
	@Test
	void test10() {
		Caesar c = new Caesar();
		String result = c.encode("A", -1);
		assertEquals("@", result);
	}
	
	@Test
	void test11() {
		Caesar c = new Caesar();
		String result = c.encode("A", 0);
		assertEquals("A", result);
	}
	
	@Test
	void test12() {
		Caesar c = new Caesar();
		String result = c.encode("A", 1);
		assertEquals("B", result);
	}
	
	@Test
	void test13() {
		Caesar c = new Caesar();
		String result = c.encode("z", -1);
		assertEquals("y", result);
	}
	
	@Test
	void test14() {
		Caesar c = new Caesar();
		String result = c.encode("z", 0);
		assertEquals("z", result);
	}
	
	@Test
	void test15() {
		Caesar c = new Caesar();
		String result = c.encode("z", 1);
		assertEquals("a", result);
	}
	
	@Test
	void test16() {
		Caesar c = new Caesar();
		String result = c.encode("Z", -1);
		assertEquals("Y", result);
	}
	
	@Test
	void test17() {
		Caesar c = new Caesar();
		String result = c.encode("Z", 0);
		assertEquals("Z", result);
	}
	
	@Test
	void test18() {
		Caesar c = new Caesar();
		String result = c.encode("Z", 1);
		assertEquals("A", result);
	}
	
	@Test
	void test19() {
		Caesar c = new Caesar();
		String result = c.encode("abC", 1);
		assertEquals("bcD", result);
	}
}

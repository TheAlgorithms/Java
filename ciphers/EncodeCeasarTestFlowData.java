package ciphers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EncodeCeasarTestFlowData {
    // - All-DU path với biến message: 1 - 2 - 3 - 4 - 5 - 6
	@Test
	void test1() {
		Caesar c = new Caesar();
		String result = c.encode("abc", 1);
		assertEquals("bcd", result);
    }
    
    // - All-DU path với biến message: 1 - 2 - 3 - 4 - 5 - 6 - 14 - 15 (vòng lặp)
	@Test	
	void test2() {
		Caesar c = new Caesar();
		String result = c.encode("@x", 0);
		assertEquals("@x", result);
	}
    
    // - All-DU path với biến shift: 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8	
	@Test
	void test3() {
		Caesar c = new Caesar();
		String result = c.encode("F", 2);
		assertEquals("H", result);
	}
    
    // - All-DU path với biến shift: 1 - 2 - 3 - 4 - 5 - 6 - 7 - 10 - 11
	@Test
	void test4() {
		Caesar c = new Caesar();
		String result = c.encode("i", -1);
		assertEquals("i", result);
	}
    
    // - All-DU path với biến shift: 1 - 2 - 3 - 4 - 5 - 6 - 14 - 15 (vòng lặp)	
	@Test
	void test5() {
		Caesar c = new Caesar();
		String result = c.encode("4$c", -2);
		assertEquals("4$a", result);
	}
}

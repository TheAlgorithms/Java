package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralUtilTest {
		@ParameterizedTest()
		@CsvSource({"2000,MM", "1,I", "2,II", "1003,MIII", "1004,MIV"})
		void minimumMaximumTest(int testInt, String word) {
            System.out.println(word + ": " + testInt);
            assertEquals(RomanNumeralUtil.generate(testInt), word);
		}

		@Test
		void calcSomeNumerals() {
            assertThrows(IllegalArgumentException.class, () -> RomanNumeralUtil.generate(0));
            assertThrows(IllegalArgumentException.class, () -> RomanNumeralUtil.generate(6000));
		}

		@Test
		void shouldNotThrowOnBounds() {
			int min = 1;
			int max = 5999;

			assertDoesNotThrow(() -> RomanNumeralUtil.generate(min), "Should not throw exception on " + min);
			assertDoesNotThrow(() -> RomanNumeralUtil.generate(max), "Should not throw exception on " + max);
		}
}

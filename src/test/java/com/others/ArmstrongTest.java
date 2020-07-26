package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArmstrongTest {

	@Test
	void testIsArmstrongNumber() {
		int armstrongNumber = 407;

		Assertions.assertTrue(Armstrong.checkIfANumberIsAmstrongOrNot(armstrongNumber));
	}

	@Test
	void testIsNotArmstrongNumber() {
		int armstrongNumber = 100;

		Assertions.assertFalse(Armstrong.checkIfANumberIsAmstrongOrNot(armstrongNumber));
	}

}

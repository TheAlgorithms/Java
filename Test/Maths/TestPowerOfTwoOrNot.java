package Test.Maths;

import Maths.PowerOfTwoOrNot;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestPowerOfTwoOrNot {
    PowerOfTwoOrNot test = new PowerOfTwoOrNot();

    @Test
    public void testCheckIfPowerOfTwoOrNot() {
        assertTrue(test.checkIfPowerOfTwoOrNot(1));
        assertTrue(test.checkIfPowerOfTwoOrNot(4));
        assertTrue(test.checkIfPowerOfTwoOrNot(8));
        assertTrue(test.checkIfPowerOfTwoOrNot(1024));
        assertFalse(test.checkIfPowerOfTwoOrNot(0));
        assertFalse(test.checkIfPowerOfTwoOrNot(6));
        assertFalse(test.checkIfPowerOfTwoOrNot(200));
    }

}

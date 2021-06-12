package Test.Maths;

import Maths.FindMin;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestFindMin {
    int [] testnum1 = {1, 8, 0, 100, 44, 67};
    int [] testnum2 = {-102, 56, -9, 80, 23};

    FindMin test = new FindMin();

    @Test
    public void testFindMax() {
        assertEquals(0, test.findMin(testnum1));
        assertEquals(-102, test.findMin(testnum2));
    }
}

package Test.Maths;

import Maths.FindMax;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestFindMax {
    int [] testnum1 = {1, 8, 0, 100, 44, 67};
    int [] testnum2 = {-102, 56, -9, 80, 23};

    FindMax test = new FindMax();

    @Test
    public void testFindMax() {
        assertEquals(100, test.findMax(testnum1));
        assertEquals(80, test.findMax(testnum2));
    }
}

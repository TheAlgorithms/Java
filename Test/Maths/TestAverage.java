package Test.Maths;

import Maths.Average;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestAverage {
    // Int array:
    int [] testnum1 = {5, 2, 4};
    int [] testnum2 = {2, -8, 10, -32, 9};
    // Double array:
    double [] testnum3 = {3.1, 4.6, 0.2, 10.8};
    double [] testnum4 = {-18.6, 0.675, -2.5, 19.6};

    Average test = new Average();

    @Test
    public void testAverage() {
        assertEquals(3, test.average(testnum1), 0.000001);
        assertEquals(-3, test.average(testnum2), 0.000001);
        assertEquals(4.675, test.average(testnum3), 0.000001);
        assertEquals(-0.20625, test.average(testnum4), 0.000001);
    }
}

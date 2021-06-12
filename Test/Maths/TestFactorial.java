package Test.Maths;

import Maths.Factorial;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestFactorial {
    Factorial test = new Factorial();

    @Test
    public void testSurfaceAreaCube() {
        assertEquals(1,test.factorial(0));
        assertEquals(1,test.factorial(1));
        assertEquals(6,test.factorial(3));
        assertEquals(120,test.factorial(5));
        assertEquals(40320,test.factorial(8));
        assertEquals(3628800,test.factorial(10));
        assertEquals(479001600,test.factorial(12));

    }
}

import Conversions.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class AnyBaseToAnyBaseTest{
    private static AnyBaseToAnyBase AnyBaseToAnyBaseTest;

    //test coverage conditions

    @BeforeClass

    public static void prepareForalltest() {
        AnyBaseToAnyBaseTest = new AnyBaseToAnyBase();
    }

    @Test
    public void testConver1() {
        String result = AnyBaseToAnyBaseTest.base2base("A", 16, 2);
        assertEquals(result, "1010");
    }

    @Test
    public void testConver2() {
        String result = AnyBaseToAnyBaseTest.base2base("5", 16, 2);
        assertEquals(result, "101");
    }

    @Test
    public void testConver3() {
        String result = AnyBaseToAnyBaseTest.base2base("1AB", 16, 2);
        assertEquals(result, "110101011");
    }
}

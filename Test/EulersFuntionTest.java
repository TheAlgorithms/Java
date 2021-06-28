import Others.*;
import org.junit.Assert;
import org.junit.Test;

public class EulersFuntionTest {
    @Test
    public void Value0 () {
        final int expected = 0;

        final int actual = EulersFunction.getEuler(0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void Value1 () {
        final int expected = 1;

        final int actual = EulersFunction.getEuler(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ValueOther () {
        final int expected = 4;

        final int actual = EulersFunction.getEuler(10);

        Assert.assertEquals(expected, actual);
    }
}
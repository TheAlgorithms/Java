package Others.Test;

import Others.EulersFunction;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class EulersFunctionTest {

    @Test
    @DisplayName("Test Negative Value")
    public void getEulerNegative() {
        int n = -1;
        Assert.assertEquals(-1, EulersFunction.getEuler(n));
    }

    @Test
    @DisplayName("Test Small Value <100")
    public void getEulerSmall(){
        int n = 40;
        assertEquals(16, EulersFunction.getEuler(n));
    }

    @Test
    @DisplayName("Test Big Value >100")
    public void getEulerBig() {
        int n = 999;
        assertEquals(648, EulersFunction.getEuler(n));
    }
}
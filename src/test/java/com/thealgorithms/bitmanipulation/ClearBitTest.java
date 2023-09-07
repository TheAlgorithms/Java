import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClearBitTest {
    @Test
    public void clearBitTest() {
        assertEquals(5, ClearBit.clearBit(7,1));
        assertEquals(5, ClearBit.clearBit(5,1));
    }
}

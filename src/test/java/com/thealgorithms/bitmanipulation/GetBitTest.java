import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetBitTest {
    @Test
    public void getBitTest() {
        assertEquals(1, GetBit.getBit(7,1));
        assertEquals(0, GetBit.getBit(5,1));
    }
}

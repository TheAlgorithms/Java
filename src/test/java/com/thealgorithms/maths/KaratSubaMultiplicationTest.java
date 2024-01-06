import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaratSubaMultiplicationTest {
    @Test
    public void testMultiple() {
        BigInteger a = new BigInteger("123456789");
        BigInteger b = new BigInteger("987654321");
        BigInteger expected = a.multiply(b);
        BigInteger actual = KaratSubaMultiplication.multiple(a, b);
        assertEquals(expected, actual);
    }
}

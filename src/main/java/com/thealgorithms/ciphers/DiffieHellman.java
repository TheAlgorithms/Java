import java.math.BigInteger;

public final class DiffieHellman {
    private DiffieHellman() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static BigInteger calculatePublicValue(BigInteger base, BigInteger secret, BigInteger prime) {
        return base.modPow(secret, prime);
    }

    public static BigInteger calculateSharedSecret(BigInteger otherPublicValue, BigInteger secret, BigInteger prime) {
        return otherPublicValue.modPow(secret, prime);
    }
}

// DiffieHellmanTest.java - JUnit Tests
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

public class DiffieHellmanTest {

    @ParameterizedTest
    @DisplayName("Diffie-Hellman Key Exchange Test")
    @CsvSource({
        "23, 5, 6, 15",  
        "97, 7, 12, 23",
        "61, 2, 9, 19"
    })
    void testDiffieHellman(String nStr, String gStr, String xStr, String yStr) {
        BigInteger n = new BigInteger(nStr);
        BigInteger g = new BigInteger(gStr);
        BigInteger x = new BigInteger(xStr);  
        BigInteger y = new BigInteger(yStr);  

        BigInteger a = DiffieHellman.calculatePublicValue(g, x, n);  
        BigInteger b = DiffieHellman.calculatePublicValue(g, y, n);  

        BigInteger k1 = DiffieHellman.calculateSharedSecret(b, x, n); 
        BigInteger k2 = DiffieHellman.calculateSharedSecret(a, y, n); 

        assertEquals(k1, k2, "Shared secret keys do not match for inputs n=" + nStr + ", g=" + gStr);
    }
}

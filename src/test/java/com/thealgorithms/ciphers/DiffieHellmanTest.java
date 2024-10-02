import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiffieHellmanTest {

    // Method to provide test data for public key calculation
    private static Stream<Arguments> providePublicKeyData() {
        return Stream.of(
            // base, secret, prime, expected public value
            Arguments.of(new BigInteger("5"), new BigInteger("6"), new BigInteger("23"), new BigInteger("8")),
            Arguments.of(new BigInteger("2"), new BigInteger("5"), new BigInteger("13"), new BigInteger("6"))
        );
    }

    // Test for public key calculation
    @ParameterizedTest
    @MethodSource("providePublicKey

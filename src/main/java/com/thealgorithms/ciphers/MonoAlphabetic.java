public final class MonoAlphabetic {
    private MonoAlphabetic() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String encrypt(String data, String key) {
        int idx;
        char c;
        StringBuffer sb = new StringBuffer(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            idx = sb.charAt(i) - 65;
            c = key.charAt(idx);
            sb.setCharAt(i, c);
        }
        return new String(sb);
    }

    public static String decrypt(String data, String key) {
        int idx;
        char c;
        StringBuffer sb = new StringBuffer(data.toUpperCase());

        for (int i = 0; i < sb.length(); i++) {
            c = sb.charAt(i);
            idx = getIndex(c, key);
            c = (char) (idx + 65);
            sb.setCharAt(i, c);
        }
        return new String(sb);
    }

    public static int getIndex(char c, String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
}

// JUnit Tests for MonoAlphabetic Cipher
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class MonoAlphabeticTest {

    private final String key = "MNBVCXZLKJHGFDSAPOIUYTREWQ";

    @ParameterizedTest
    @DisplayName("Encrypt Test with MonoAlphabetic Cipher")
    @CsvSource({
        "HELLO, DLZZI",
        "WORLD, XMFLD",
        "JAVA, HBWB",
        "OPENAI, IUPNMW"
    })
    void testEncrypt(String plainText, String expectedCipherText) {
        String encrypted = MonoAlphabetic.encrypt(plainText, key);
        assertEquals(expectedCipherText, encrypted, "Encryption failed for input: " + plainText);
    }

    @ParameterizedTest
    @DisplayName("Decrypt Test with MonoAlphabetic Cipher")
    @CsvSource({
        "DLZZI, HELLO",
        "XMFLD, WORLD",
        "HBWB, JAVA",
        "IUPNMW, OPENAI"
    })
    void testDecrypt(String cipherText, String expectedPlainText) {
        String decrypted = MonoAlphabetic.decrypt(cipherText, key);
        assertEquals(expectedPlainText, decrypted, "Decryption failed for input: " + cipherText);
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PangramTest {

    @Test
    public void testIsPangram() {
        assertTrue(Pangram.isPangram("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangram("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(Pangram.isPangram("+-1234 This string is not alphabetical"));
        assertFalse(Pangram.isPangram("\u0000/\\"));
    }

    @Test
    public void testIsPangramUsingSet() {
        assertTrue(Pangram.isPangramUsingSet("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangramUsingSet("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(Pangram.isPangramUsingSet("+-1234 This string is not alphabetical"));
        assertFalse(Pangram.isPangramUsingSet("\u0000/\\"));
    }

    @Test
    public void testIsPangram2() {
        assertTrue(Pangram.isPangram2("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangram2("The quick brown fox jumps over the azy dog")); // L is missing
        assertFalse(Pangram.isPangram2("+-1234 This string is not alphabetical"));
        assertFalse(Pangram.isPangram2("\u0000/\\"));
    }

    @Test
    public void testIsPangramUsingBitwise() {
        assertTrue(Pangram.isPangramUsingBitwise("The quick brown fox jumps over the lazy dog"));
        assertFalse(Pangram.isPangramUsingBitwise("Hello, World!"));
    }
}

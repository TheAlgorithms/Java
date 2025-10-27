package com.thealgorithms.ciphers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyCaesarTest {
    private final Caesar caesar = new Caesar();

    @Test
    void shouldReturnSameAsInputWhenShiftingZeroOr26() {
        String message = "message";

        String encoded1 = caesar.encode(message, 0);
        String encoded2 = caesar.encode(message, 26);

        assertEquals(message, encoded1, "Encoded should be same as original");
        assertEquals(message, encoded2, "Encoded should be same as original");
    }

    @Test
    void shouldReturnsameAsInputWhenUsingCharactersOutsideLatinAlphabet() {
        String message = "!#¤%&/()=?`^½§@£$€{[]}´`¨~'*-.,_:;<>|";

        String encoded = caesar.encode(message, 10);

        assertEquals(message, encoded);
    }

    @Test
    void shouldWrapZToAWhenEncodingSingleShift() {
        String message = "zZ";

        String encoded = caesar.encode(message, 1);
        String exptected = "aA";

        assertEquals(exptected, encoded, "zZ should wrap to aA");
    }

    @Test
    void shouldWrapAToZWhenDecodingSingleShift() {
        String message = "aA";

        String decoded = caesar.decode(message, 1);
        String expected = "zZ";

        assertEquals(expected, decoded);
    }

    @Test
    void shouldNotWrapWhenEncodingFromYToZ() {
        String message = "yY";

        String encoded = caesar.encode(message, 1);
        String expected = "zZ";

        assertEquals(expected, encoded);
    }

    @Test
    void shouldNotWrapWhenDecodingFromBToA() {
        String message = "bB";

        String decoded = caesar.decode(message, 1);
        String expected = "aA";

        assertEquals(expected, decoded);
    }

    @Test
    void shouldContain27CombinationsFromBruteForce() {
        String message = "message";

        String encoded = caesar.encode(message, 10);
        String[] combinations = caesar.bruteforce(encoded);
        String expected = "wocckqo";

        assertEquals(27, combinations.length, "Should contain 27 possible decoded combinations");
        assertEquals(expected, combinations[0], "First combination should contain encoded message");
        assertEquals(message, combinations[10], "10:th entry should contain original message");
        assertEquals(expected, combinations[26], "26:th entry should be the same as the 0:th entry, the encoded message");
    }
}

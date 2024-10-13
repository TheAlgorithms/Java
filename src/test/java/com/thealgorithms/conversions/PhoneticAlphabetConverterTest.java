package com.thealgorithms.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PhoneticAlphabetConverterTest {

    @Test
    public void testTextToPhonetic() {
        assertEquals("Alpha Bravo", PhoneticAlphabetConverter.textToPhonetic("AB"));
        assertEquals("Alpha Bravo Charlie", PhoneticAlphabetConverter.textToPhonetic("ABC"));
        assertEquals("Alpha One Bravo Two Charlie Three", PhoneticAlphabetConverter.textToPhonetic("A1B2C3"));
        assertEquals("Hotel Echo Lima Lima Oscar", PhoneticAlphabetConverter.textToPhonetic("Hello"));
        assertEquals("One Two Three", PhoneticAlphabetConverter.textToPhonetic("123"));
        assertEquals("Alpha Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee Zulu Zero One Two Three Four Five Six Seven Eight Nine",
            PhoneticAlphabetConverter.textToPhonetic("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        assertEquals("Alpha Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee Zulu Zero One Two Three Four Five Six Seven Eight Nine",
            PhoneticAlphabetConverter.textToPhonetic("abcdefghijklmnopqrstuvwxyz0123456789"));
    }
}

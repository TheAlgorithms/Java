package com.thealgorithms.conversions;

/**
 * Converts turkish character to latin character
 *
 * @author Özgün Gökşenli
 */
public final class TurkishToLatinConversion {
    private TurkishToLatinConversion() {
    }

    /**
     * This method converts a turkish character to latin character.
     * Steps:
     * 1. Define turkish characters and their corresponding latin characters
     * 2. Replace all turkish characters with their corresponding latin characters
     * 3. Return the converted string
     *
     * @param param String paramter
     * @return String
     */
    public static String convertTurkishToLatin(String param) {
        char[] turkishChars = new char[] {
            0x131,
            0x130,
            0xFC,
            0xDC,
            0xF6,
            0xD6,
            0x15F,
            0x15E,
            0xE7,
            0xC7,
            0x11F,
            0x11E,
        };
        char[] latinChars = new char[] {
            'i',
            'I',
            'u',
            'U',
            'o',
            'O',
            's',
            'S',
            'c',
            'C',
            'g',
            'G',
        };
        for (int i = 0; i < turkishChars.length; i++) {
            param = param.replaceAll(String.valueOf(turkishChars[i]), String.valueOf(latinChars[i]));
        }
        return param;
    }
}

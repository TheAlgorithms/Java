package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * Converts turkish character to latin character
 *
 * @author Özgün Gökşenli
 */
public class TurkishToLatinConversion {

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the string: ");
        String b = sc.next();
        System.out.println("Converted: " + convertTurkishToLatin(b));
        sc.close();
    }

    /**
     * This method converts a turkish character to latin character.
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
            param =
                param.replaceAll(
                    new String(new char[] { turkishChars[i] }),
                    new String(new char[] { latinChars[i] })
                );
        }
        return param;
    }
}

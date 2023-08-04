package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * This class converts a character to a ASCII Value
 * @author Tristan Norbury (https://github.com/Tristan296)
 */
class CharToAscii {
    /**
     * Main Method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the character input below: ");
        Scanner reader = new Scanner(System.in);
        char CharInput = reader.next().charAt(0);
        System.out.println("ASCII value:" + ConvertToAsciiDecimal(CharInput));
        reader.close();
    }

     /**
     * This method produces a Integer value of any given input character
     * 
     *
     * @param c char input of which we need the ascii value of
     * @return integer of the corresponding ascii value
     */
    public static int ConvertToAsciiDecimal(char c) {
        int ascii = (int) c;

        return ascii;
    }
}

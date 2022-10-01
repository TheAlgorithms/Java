/**
 * checks if a character is a number or a letter
 */
package com.thealgorithms.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOrCharacterIsDigitTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a text containing letters and numbers: ");

        String inputString = bufferedReader.readLine();

        for(int i=0; i < inputString.length(); i++) {
            Boolean check = Character.isDigit(inputString.charAt(i));
            if(check) {
                System.out.println(inputString.charAt(i) + " is a number!");
            } else {
                System.out.println(inputString.charAt(i) + " is a letter!");
            }
        }
    }
}

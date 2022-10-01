/**
 * checks if a character is a number or a letter
 */
package com.thealgorithms.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOrCharacterIsDigitTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a value: ");

        String str = br.readLine();

        for(int i=0; i < str.length(); i++) {
            Boolean flag = Character.isDigit(str.charAt(i));
            if(flag) {
                System.out.println("'"+ str.charAt(i)+"' is a number!");
            }
            else {
                System.out.println("'"+ str.charAt(i)+"' is a letter!");
            }
        }
    }
}

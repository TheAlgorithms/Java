package com.thealgorithms.conversions;
/*
Author: Sanket Patil
Github username: SanketPatil7467
Topic: Binary to gray conversion and vice-versa:

--------------LOGIC----------------
Gray to Binary:
--> Here the most significant bit of the Binary code is same as the given Gray code, so we direclty copy it.
--> Further traversing the Gray code we check if the current character of Gray code is '0', if it is '0'
    we copy the value of previous index of converted Binary code,else we copy the flipped value of current Gray character to our binary code.

*/

import java.io.IOException;
import java.util.*;
class GraytoBinary {
    // Function to convert given binary code to gray code.
    public String grayToBinary(String gray) {
        StringBuilder sb = new StringBuilder();
        /*
        Initializing a 2d matrice with one column for given gray code and
        another column for converted binary code.
        */
        char[][] matrice = new char[gray.length()][2];

        int i;

        // Assigning the Initial Values
        matrice[0][0] = (gray.charAt(0));
        matrice[0][1] = (gray.charAt(0)); // MSB of binary code is same as that of given Gray code

        for (i = 1; i < gray.length(); i++) {
            matrice[i][0] = gray.charAt(i);
            // if the current character is '0' we copy the previous character of the binary code
            if (gray.charAt(i) == '0') matrice[i][1] = (matrice[i - 1][1]);
            // else we copy the flipped value of the previous gray code character
            // i.e if its '0' we take '1' and vice-versa
            else
                matrice[i][1] = (matrice[i - 1][1] == '0') ? '1' : '0';
        }

        for (i = 0; i < gray.length(); i++) {
            sb.append(matrice[i][1]);
        }
        return sb.toString();
    }

    public static void main(String as[]) throws IOException {
        GraytoBinary obj = new GraytoBinary();
        String gray = "1101101"; // Given Gray Code
        System.out.println(obj.grayToBinary(gray));
    }
}
package com.thealgorithms.conversions;

/*
Author: Sanket Patil
Github username: SanketPatil7467
Topic: Binary to gray conversion:

--------------LOGIC----------------
Binary to Gray:
--> Here the most significant bit of the gray code is same as the given binary code
    so we don't need to make any changes
--> Further traversing the binary from 1st index(i.e 2nd position) we take the xor of current
    index character  of binary code with the previous index character of binary code
    and add it to our converted gray code

*/

import java.io.IOException;

class BinarytoGray {
    // Function to convert given binary code to gray code.
    public String binaryToGray(String bin) {
        StringBuilder sb = new StringBuilder();
        /*
         * Initializing a 2d matrice with one column for given binary code and
         * another column for converted gray code.
         */
        char[][] matrice = new char[bin.length()][2];

        int i;
        // Assigning the Initial Values
        matrice[0][0] = (bin.charAt(0));
        matrice[0][1] = (bin.charAt(0)); // MSB of Gray code is same as that of given Binary code
        for (i = 1; i < bin.length(); i++) {
            // taking the XOR of current index character of binary code with
            // the previous index character of binary code
            char x = (bin.charAt(i) == bin.charAt(i - 1)) ? '0' : '1';
            matrice[i][1] = x;
            matrice[i][0] = bin.charAt(i);
        }

        for (i = 0; i < bin.length(); i++) {
            sb.append(matrice[i][1]);
        }
        return sb.toString();
    }

    public static void main(String as[]) throws IOException {
        BinarytoGray obj = new BinarytoGray();
        String bin = "1001001"; // Given Binary Code
        System.out.println(obj.binaryToGray(bin));
    }
}

package com.thealgorithms.ciphers;

class AffineCipher {

    // Key values of a and b
    static int a = 17;
    static int b = 20;

    static String encryptMessage(char[] msg) {
        /// Cipher Text initially empty
        String cipher = "";
        for (int i = 0; i < msg.length; i++) {
            // Avoid space to be encrypted
            /* applying encryption formula ( a x + b ) mod m
            {here x is msg[i] and m is 26} and added 'A' to
            bring it in range of ascii alphabet[ 65-90 | A-Z ] */
            if (msg[i] != ' ') {
                cipher =
                    cipher + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
            } else { // else simply append space character
                cipher += msg[i];
            }
        }
        return cipher;
    }

    static String decryptCipher(String cipher) {
        String msg = "";
        int a_inv = 0;
        int flag = 0;

        //Find a^-1 (the multiplicative inverse of a
        //in the group of integers modulo m.)
        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;

            // Check if (a*i)%26 == 1,
            // then i will be the multiplicative inverse of a
            if (flag == 1) {
                a_inv = i;
            }
        }
        for (int i = 0; i < cipher.length(); i++) {
            /*Applying decryption formula a^-1 ( x - b ) mod m
            {here x is cipher[i] and m is 26} and added 'A'
            to bring it in range of ASCII alphabet[ 65-90 | A-Z ] */
            if (cipher.charAt(i) != ' ') {
                msg =
                    msg +
                    (char) (
                        ((a_inv * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A'
                    );
            } else { //else simply append space character
                msg += cipher.charAt(i);
            }
        }

        return msg;
    }

    // Driver code
    public static void main(String[] args) {
        String msg = "AFFINE CIPHER";

        // Calling encryption function
        String cipherText = encryptMessage(msg.toCharArray());
        System.out.println("Encrypted Message is : " + cipherText);

        // Calling Decryption function
        System.out.println(
            "Decrypted Message is: " + decryptCipher(cipherText)
        );
    }
}

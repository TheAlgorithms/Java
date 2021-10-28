//The ‘key’ for the Affine cipher consists of 2 numbers, we’ll call them a and b.
// The following discussion assumes the use of a 26 character alphabet (m = 26).
// a should be chosen to be relatively prime to m (i.e. a should have no factors in common with m).

package Ciphers;

import java.util.Scanner;

class AffineCipher
{
    static Scanner in = new Scanner(System.in);

    static String encryptMessage(char[] msg)
    {
        System.out.println("Enter key value a for encryption : ");
        int a = in.nextInt();
        
        System.out.println("Enter key value b for encryption : ");
        int b = in.nextInt();
        
        /// Initially empty cipher String
        String cipher = "";
        for (int i = 0; i < msg.length; i++)
        {
            // Avoid space to be encrypted
            /* applying encryption formula ( a x + b ) mod m
            {here x is msg[i] and m is 26} and added 'A' to
            bring it in range of ascii alphabet[ 65-90 | A-Z ] */
            if (msg[i] != ' ')
            {
                cipher = cipher
                        + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
            } else // append space character
            {
                cipher += msg[i];
            }
        }
        return cipher;
    }

    static String decryptCipher(String cipher)
    {
        System.out.println("Enter key value a for decryption : ");
        int a = in.nextInt();
        
        System.out.println("Enter key value b for decryption : ");
        int b = in.nextInt();
        
        String msg = "";
        int a_inv = 0;
        int flag = 0;

        //Find a^-1 (the multiplicative inverse of a
        //in the group of integers modulo m.)
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;

            // Check if (a*i)%26 == 1,
            // if so, then i will be the multiplicative inverse of a
            if (flag == 1)
            {
                a_inv = i;
            }
        }
        for (int i = 0; i < cipher.length(); i++)
        {
            /*Applying decryption formula a^-1 ( x - b ) mod m
            {here x is cipher[i] and m is 26} and added 'A'
            to bring it in range of ASCII alphabet[ 65-90 | A-Z ] */
            if (cipher.charAt(i) != ' ')
            {
                msg = msg + (char) (((a_inv *
                        ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
            }
            else // append space character
            {
                msg += cipher.charAt(i);
            }
        }

        return msg;
    }

    // Main method
    public static void main(String[] args)
    {
        String msg = "AFFINE CIPHER";

        // Encrypting message
        String cipherText = encryptMessage(msg.toCharArray());
        System.out.println("Encrypted Message is : " + cipherText);

        // Decrypting message
        System.out.println("Decrypted Message is: " + decryptCipher(cipherText));

    }
}

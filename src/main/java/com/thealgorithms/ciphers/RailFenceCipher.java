import java.util.*;

class RailFenceBasic {
    int depth;

    String Encryption(String plainText, int depth) throws Exception {
        int r = depth, len = plainText.length();
        int c = (len / depth) ;
        char mat[][] = new char[r][c];
        int k = 0;

        String cipherText = "";

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != len)
                    mat[j][i] = plainText.charAt(k++);
                else
                    mat[j][i] = 'X';
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cipherText += mat[i][j];
            }
        }
        return cipherText;
    }

    
    String Decryption(String cipherText, int depth) throws Exception {
        int r = depth, len = cipherText.length();
        int c = (len / depth) ;
        char mat[][] = new char[r][c];
        int k = 0;

        String plainText = "";

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cipherText.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                plainText += mat[j][i];
            }
        }

        return plainText;
    }
}

class RailFenceCipher {
    public static void main(String args[]) throws Exception {
        RailFenceBasic rf = new RailFenceBasic();
        Scanner scn = new Scanner(System.in);
        int depth;

        System.out.println("******************************************************************");
        System.out.println("_____________________________RailFence____________________________");

        String plainText, cipherText, decryptedText;
        System.out.println("Enter plain text:");
        plainText = scn.nextLine();
        System.out.println("");

        System.out.println("Enter depth for Encryption:");
        depth = scn.nextInt();
        System.out.println("");
        System.out.println("------------------------------------------------------------------");

        cipherText = rf.Encryption(plainText, depth);
        System.out.println("Encrypted text is: " + cipherText);
        System.out.println("------------------------------------------------------------------");

        decryptedText = rf.Decryption(cipherText, depth);

        System.out.println("Decrypted text is: " + decryptedText);
        System.out.println("------------------------------------------------------------------");

    }
}

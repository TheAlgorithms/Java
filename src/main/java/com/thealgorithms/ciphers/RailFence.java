package com.thealgorithms.ciphers;

import java.util.Scanner;
/*
* Rail fence cipher
* The rail fence cipher (also called a zigzag cipher) is a form of transposition cipher. 
* It derives its name from the way in which it is encoded.
* 
* Code by @hexaorzo
*/
class RailFenceBasic {
  int depth;

  String Encryption(String plainText, int depth) throws Exception {
    int r = depth, len = plainText.length();
    int c = len / depth;
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
    int c = len / depth;
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

class RailFence {
  public static void main(String args[]) throws Exception {
    RailFenceBasic rf = new RailFenceBasic();
    Scanner input = new Scanner(System.in);
        int shift = 0;
        System.out.println("Please enter the message (Latin Alphabet)");
        String message = input.nextLine();
        System.out.println(message);
        System.out.println("(E)ncode or (D)ecode");
        char choice = input.next().charAt(0);
        switch (choice) {
            case 'E':
            case 'e':
                System.out.println("Please enter the depth number");
                depth = input.nextInt() % 26;
                System.out.println("ENCRYPTED TEXT IS \n" + rf.Encryption(message, shift));
                break;
            case 'D':
            case 'd':
                System.out.println("Please enter the depth number");
                shift = input.nextInt() % 26;
                System.out.println("DECRYPTED TEXT IS \n" + rf.Decryption(message, shift));
                break;
            default:
                System.out.println("default case");
        }
        input.close();
  }
}

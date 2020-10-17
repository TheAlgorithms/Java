import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * This is a JAVA implementation of ADFGVX Cipher.
 * The cipher is based on the 6 letters ADFGVX.
 * It uses a 6 x 6 polybius square containing all the letters and the numbers 0 - 9.
 * Any word can be used as a key word.
 *
 * @author Jetty53
 */

public class CipherADFGVX {

    public static void main(String[] args) {

        ADFGVX adfgvx = new ADFGVX();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plain text: "); /* Enter the input text */
        String inputText = scanner.nextLine();
        System.out.println("Enter the matrix pattern: "); /* Polybius Square Pattern */
        String matrixPat = scanner.nextLine();

        /**
         *
         * If the polybius square is:
         *
         *  p h 0 q g 6
         *  4 m e a 1 y
         *  l 2 n o f d
         *  x k r 3 c v
         *  s 5 z w 7 b
         *  j 9 u t i 8
         *
         * Then the matrix pattern input should be like:
         *
         * ph0qg64mea1yl2nofdxkr3cvs5zw7bj9uti8
         *
         */

        System.out.println("Enter the key text: "); /* Enter the key text */
        String keyText = scanner.nextLine();

        String encode = adfgvx.encodeProcedureADFGVX(inputText, matrixPat ,keyText);
        System.out.println("The encoded String is: "+ encode); /* Here is the encoded message */

        String decode = adfgvx.decodeProcedureADFGVX(encode, matrixPat, keyText);
        System.out.println("The decoded String is: "+ decode); /* Here is the decoded message after decoding the encoded message */

    }

    public static class ADFGVX {

        

        private String encodeADFGVX(String input, String matrix, String key) {

            StringBuilder str = new StringBuilder();
            int k = 0;
            int[][] charSquare = new int[6][6];
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {

                    charSquare[row][col] = charToNum(matrix.charAt(k));
                    k++;
                }
                if (k == 35) {
                    break;
                }
            }
            int[] afterMat = new int[input.length() * 2];
            k = 0;
            for (int x = 0; x < input.length(); x++) {
                int found = 0;
                int chi;

                chi = charToNum(input.charAt(x));

                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 6; col++) {
                        if (chi == charSquare[row][col]) {
                            afterMat[k] = row;
                            k++;
                            afterMat[k] = col;
                            k++;
                            found = 1;
                        }
                    }
                    if (found == 1) {
                        break;
                    }
                }
            }
            k = 0;
            int keyArrLength = Math.min(afterMat.length, key.length());

            int[] keyArr = new int[keyArrLength];
            for (int i = 0; i < keyArrLength; i++) {

                keyArr[i] = charToNum(key.charAt(i));

            }

            int[][] array = new int[2][afterMat.length];

            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < afterMat.length; col++) {
                    if (row == 0) {
                        array[row][col] = afterMat[col];
                    } else {
                        array[row][col] = keyArr[k];
                        k++;
                        if (k == keyArr.length) {
                            k = 0;
                        }
                    }
                }
            }
            Arrays.sort(keyArr);

            int[] resArr = new int[afterMat.length];
            k = 0;

            for (int value : keyArr) {
                for (int i = 0; i < keyArr.length; i++) {
                    if (array[1][i] == value) {
                        int loc = i;
                        array[1][i] = 0;
                        while (loc <= afterMat.length - 1) {
                            resArr[k] = array[0][loc];
                            k++;
                            loc = loc + keyArr.length;
                        }
                        break;
                    }
                }
            }
            int m = 0;
            for (int i = 0; i < resArr.length; i++) {

                if (65 <= input.charAt(m) && input.charAt(m) <= 90) {
                    str.append(numToCipherChar(resArr[i], 1));
                } else {
                    str.append(numToCipherChar(resArr[i], 0));
                }
                if (i % 2 != 0) {
                    m++;
                }

            }

            return str.toString();

        }

        private String decodeADFGVX(String inputCipher, String matrix, String key) {

            StringBuilder str = new StringBuilder();
            int keyArrLength = Math.min(inputCipher.length(), key.length());

            int[] keyArr = new int[keyArrLength];
            int[] sortedIndexKey = new int[keyArr.length];
            int[] flag = new int[keyArr.length];
            for (int i = 0; i < keyArr.length; i++) {
                keyArr[i] = charToNum(key.charAt(i));
                sortedIndexKey[i] = charToNum(key.charAt(i));
            }

            Arrays.sort(sortedIndexKey);
            for (int i = 0; i < sortedIndexKey.length; i++) {
                for (int j = 0; j < keyArr.length; j++) {
                    if (sortedIndexKey[i] == keyArr[j] && flag[j] == 0) {
                        sortedIndexKey[i] = j;
                        flag[j] = 1;
                        break;
                    }
                }
            }

            int[] array = new int[inputCipher.length()];
            int k = 0;
            int loc = 0;
            for (int value : sortedIndexKey) {
                if (value < (inputCipher.length() % keyArr.length)) {
                    for (int j = k; j < k + (inputCipher.length() / keyArr.length) + 1; j++) {
                        array[j] = value;
                        loc = j;
                    }
                } else if (value >= (inputCipher.length() % keyArr.length)) {
                    for (int j = k; j < k + (inputCipher.length() / keyArr.length); j++) {
                        array[j] = value;
                        loc = j;
                    }
                }
                k = loc + 1;
            }

            int[] decodedOutput = new int[inputCipher.length()];
            int pos;
            for (int i = 0; i < keyArr.length; i++) {
                pos = i;
                for (int j = 0; j < array.length; j++) {

                    if (array[j] == i) {
                        decodedOutput[pos] = numToCipherNum(charToNum(inputCipher.charAt(j)));
                        pos = pos + keyArr.length;
                        if (pos > decodedOutput.length - 1) {
                            break;
                        }
                    }
                }
            }
            k = 0;
            char[][] charSquare = new char[6][6];
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    charSquare[row][col] = matrix.charAt(k);
                    k++;
                }
                if (k == 35) {
                    break;
                }
            }

            char[] resArr = new char[inputCipher.length() / 2];
            k = 0;
            int r, c;

            for (int i = 0; i < resArr.length; i++) {
                r = decodedOutput[k];
                k++;
                c = decodedOutput[k];
                k++;
                resArr[i] = charSquare[r][c];
            }

            int l = 0;

            for (char value : resArr) {

                if (65 <= inputCipher.charAt(l) && inputCipher.charAt(l) <= 90) {
                    str.append((char) (value - 32));
                } else {
                    str.append(value);
                }

                l += 2;

            }

            return str.toString();

        }

        public String encodeProcedureADFGVX(String inputText, String matrixPat, String keyText) {

            StringBuilder str1 = new StringBuilder();
            StringBuilder encodedText = new StringBuilder();
            String plainText = null;
            String encode;
            int noSpclChar = 0;

            for (int i = 0; i < inputText.length(); i++) {
                if (('0' <= inputText.charAt(i) && inputText.charAt(i) <= '9') || ('A' <= inputText.charAt(i) && inputText.charAt(i) <= 'Z') || ('a' <= inputText.charAt(i) && inputText.charAt(i) <= 'z')) {

                    str1.append(inputText.charAt(i));
                    plainText = str1.toString();
                    noSpclChar = 1;

                } else {

                    noSpclChar = 0;
                    if (plainText != null) {
                        encodedText.append(encodeADFGVX(plainText, matrixPat, keyText));
                    }
                    str1.delete(0, i);

                    plainText = null;
                    encodedText.append(inputText.charAt(i));

                }

            }

            if (noSpclChar == 1 & plainText != null) {
                encodedText.append(encodeADFGVX(plainText, matrixPat, keyText));
            }

            encode = encodedText.toString();

            return encode;

        }

        public String decodeProcedureADFGVX(String encode, String matrixPat, String keyText) {

            StringBuilder str2 = new StringBuilder();
            StringBuilder decodedText = new StringBuilder();
            String plainText = null;
            String decode;
            int noSpclChar = 0;

            for (int i = 0; i < encode.length(); i++) {

                if (encode.charAt(i) == 'A' ||
                        encode.charAt(i) == 'D' ||
                        encode.charAt(i) == 'F' ||
                        encode.charAt(i) == 'G' ||
                        encode.charAt(i) == 'V' ||
                        encode.charAt(i) == 'X' ||
                        encode.charAt(i) == 'a' ||
                        encode.charAt(i) == 'd' ||
                        encode.charAt(i) == 'f' ||
                        encode.charAt(i) == 'g' ||
                        encode.charAt(i) == 'v' ||
                        encode.charAt(i) == 'x') {
                    str2.append(encode.charAt(i));
                    plainText = str2.toString();
                    noSpclChar = 1;

                } else {

                    if (encode.charAt(i) == 'B' ||
                            encode.charAt(i) == 'C' ||
                            encode.charAt(i) == 'E' ||
                            ('H' <= encode.charAt(i) && encode.charAt(i) <= 'U') ||
                            encode.charAt(i) == 'W' ||
                            encode.charAt(i) == 'Y' ||
                            encode.charAt(i) == 'Z' ||
                            encode.charAt(i) == 'b' ||
                            encode.charAt(i) == 'c' ||
                            encode.charAt(i) == 'e' ||
                            ('h' <= encode.charAt(i) && encode.charAt(i) <= 'u') ||
                            encode.charAt(i) == 'w' ||
                            encode.charAt(i) == 'y' ||
                            encode.charAt(i) == 'z' ||
                            ('0' <= encode.charAt(i) && encode.charAt(i) <= '9')) {

                        return "";
                    }

                    noSpclChar = 0;
                    if (plainText != null) {

                        if (plainText.length() % 2 != 0){
                            return "";
                        }

                        decodedText.append(decodeADFGVX(plainText, matrixPat, keyText));


                    }
                    str2.delete(0, i);
                    plainText = null;
                    decodedText.append(encode.charAt(i));

                }

            }

            if (noSpclChar == 1 & plainText !=null) {

                if (plainText.length() % 2 != 0){
                    return "";
                }


                decodedText.append(decodeADFGVX(plainText, matrixPat, keyText));
            }

            decode = decodedText.toString();

            return decode;
        }

        private int charToNum(char c) {
            int ret = 0;
            if (c == 'A' || c == 'a') {
                ret = 11;
            } else if (c == 'B' || c == 'b') {
                ret = 12;
            } else if (c == 'C' || c == 'c') {
                ret = 13;
            } else if (c == 'D' || c == 'd') {
                ret = 14;
            } else if (c == 'E' || c == 'e') {
                ret = 15;
            } else if (c == 'F' || c == 'f') {
                ret = 16;
            } else if (c == 'G' || c == 'g') {
                ret = 17;
            } else if (c == 'H' || c == 'h') {
                ret = 18;
            } else if (c == 'I' || c == 'i') {
                ret = 19;
            } else if (c == 'J' || c == 'j') {
                ret = 20;
            } else if (c == 'K' || c == 'k') {
                ret = 21;
            } else if (c == 'L' || c == 'l') {
                ret = 22;
            } else if (c == 'M' || c == 'm') {
                ret = 23;
            } else if (c == 'N' || c == 'n') {
                ret = 24;
            } else if (c == 'O' || c == 'o') {
                ret = 25;
            } else if (c == 'P' || c == 'p') {
                ret = 26;
            } else if (c == 'Q' || c == 'q') {
                ret = 27;
            } else if (c == 'R' || c == 'r') {
                ret = 28;
            } else if (c == 'S' || c == 's') {
                ret = 29;
            } else if (c == 'T' || c == 't') {
                ret = 30;
            } else if (c == 'U' || c == 'u') {
                ret = 31;
            } else if (c == 'V' || c == 'v') {
                ret = 32;
            } else if (c == 'W' || c == 'w') {
                ret = 33;
            } else if (c == 'X' || c == 'x') {
                ret = 34;
            } else if (c == 'Y' || c == 'y') {
                ret = 35;
            } else if (c == 'Z' || c == 'z') {
                ret = 36;
            } else if (c == '0') {
                ret = 1;
            } else if (c == '1') {
                ret = 2;
            } else if (c == '2') {
                ret = 3;
            } else if (c == '3') {
                ret = 4;
            } else if (c == '4') {
                ret = 5;
            } else if (c == '5') {
                ret = 6;
            } else if (c == '6') {
                ret = 7;
            } else if (c == '7') {
                ret = 8;
            } else if (c == '8') {
                ret = 9;
            } else if (c == '9') {
                ret = 10;
            }
            return ret;
        }

        private char numToCipherChar(int x, int y) {
            char ch = '\0';
            if (x == 0) {
                if (y == 1) {
                    ch = 'A';
                } else {
                    ch = 'a';
                }
            } else if (x == 1) {
                if (y == 1) {
                    ch = 'D';
                } else {
                    ch = 'd';
                }
            } else if (x == 2) {
                if (y == 1) {
                    ch = 'F';
                } else {
                    ch = 'f';
                }
            } else if (x == 3) {
                if (y == 1) {
                    ch = 'G';
                } else {
                    ch = 'g';
                }
            } else if (x == 4) {
                if (y == 1) {
                    ch = 'V';
                } else {
                    ch = 'v';
                }
            } else if (x == 5) {
                if (y == 1) {
                    ch = 'X';
                } else {
                    ch = 'x';
                }
            }
            return ch;
        }

        private int numToCipherNum(int x) {
            int i = -1;

            if (x == 11) { //A
                i = 0;
            } else if (x == 14) { //D
                i = 1;
            } else if (x == 16) { //F
                i = 2;
            } else if (x == 17) { //G
                i = 3;
            } else if (x == 32) { //V
                i = 4;
            } else if (x == 34) { //X
                i = 5;
            }
            return i;
        }


    }

}

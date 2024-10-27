import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ColumnarTranspositionCipher {

    public static String encrypt(String plaintext, String key) {
        int numCols = key.length();
        int numRows = (int) Math.ceil((double) plaintext.length() / numCols);
        char[][] grid = new char[numRows][numCols];
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (index < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(index++);
                } else {
                    grid[i][j] = ' '; 
                }
            }
        }
        Integer[] keyOrder = new Integer[numCols];
        for (int i = 0; i < numCols; i++) {
            keyOrder[i] = i;
        }
        Arrays.sort(keyOrder, Comparator.comparingInt(i -> key.charAt(i)));
        StringBuilder ciphertext = new StringBuilder();
        for (int i : keyOrder) {
            for (int j = 0; j < numRows; j++) {
                ciphertext.append(grid[j][i]);
            }
        }
        return ciphertext.toString().trim(); 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().replaceAll("\\s+", ""); 
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}

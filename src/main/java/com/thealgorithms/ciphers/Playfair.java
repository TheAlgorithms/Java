import java.util.Scanner;

public class PlayfairCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the plaintext from the user.
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        // Get the key from the user.
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        // Encrypt the plaintext using the Playfair cipher.
        String ciphertext = encrypt(plaintext, key);

        // Print the ciphertext.
        System.out.println("The ciphertext is: " + ciphertext);
    }

    private static String encrypt(String plaintext, String key) {
        // Create a 5x5 matrix for the key.
        char[][] keyMatrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = key.charAt(index++);
            }
        }

        // Create a list of digraphs from the plaintext.
        List<String> digraphs = new ArrayList<>();
        for (int i = 0; i < plaintext.length(); i += 2) {
            digraphs.add(plaintext.substring(i, i + 2));
        }

        // Encrypt each digraph using the Playfair cipher.
        StringBuilder ciphertextBuilder = new StringBuilder();
        for (String digraph : digraphs) {
            int row1 = ALPHABET.indexOf(digraph.charAt(0));
            int col1 = ALPHABET.indexOf(digraph.charAt(1));
            int row2 = keyMatrix[row1][col1] - 'A';
            int col2 = keyMatrix[row1][col1] - 'A';
            ciphertextBuilder.append(keyMatrix[row2][col2]);
        }

        return ciphertextBuilder.toString();
    }
}import java.util.Scanner;

public class PlayfairCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the plaintext from the user.
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        // Get the key from the user.
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        // Encrypt the plaintext using the Playfair cipher.
        String ciphertext = encrypt(plaintext, key);

        // Print the ciphertext.
        System.out.println("The ciphertext is: " + ciphertext);
    }

    private static String encrypt(String plaintext, String key) {
        // Create a 5x5 matrix for the key.
        char[][] keyMatrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = key.charAt(index++);
            }
        }

        // Create a list of digraphs from the plaintext.
        List<String> digraphs = new ArrayList<>();
        for (int i = 0; i < plaintext.length(); i += 2) {
            digraphs.add(plaintext.substring(i, i + 2));
        }

        // Encrypt each digraph using the Playfair cipher.
        StringBuilder ciphertextBuilder = new StringBuilder();
        for (String digraph : digraphs) {
            int row1 = ALPHABET.indexOf(digraph.charAt(0));
            int col1 = ALPHABET.indexOf(digraph.charAt(1));
            int row2 = keyMatrix[row1][col1] - 'A';
            int col2 = keyMatrix[row1][col1] - 'A';
            ciphertextBuilder.append(keyMatrix[row2][col2]);
        }

        return ciphertextBuilder.toString();
    }
}

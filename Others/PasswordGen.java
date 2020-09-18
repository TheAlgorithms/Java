package Others;

import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


/**
 * Creates a random password from ASCII letters
 * Given password length bounds
 *
 * @author AKS1996
 * @date 2017.10.25
 */
class PasswordGen {
    public static void main(String args[]) {
        String password = generatePassword(8, 16);
        System.out.print("Password: " + password);
    }

    static String generatePassword(int min_length, int max_length) {
        Random random = new Random();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*(){}?";

        String allChars = upper + lower + numbers + specialChars;

        List<Character> letters = new ArrayList<Character>();
        for (char c : allChars.toCharArray())
            letters.add(c);

        // Inbuilt method to randomly shuffle a elements of a list
        Collections.shuffle(letters);
       StringBuilder password = new StringBuilder();

        // Note that size of the password is also random
        for (int i = random.nextInt(max_length - min_length) + min_length; i > 0; --i) {
            password .append( letters.get(random.nextInt(letters.size())));
        }

        return password.toString();
    }
}

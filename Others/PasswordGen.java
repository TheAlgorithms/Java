package Others;

import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


/**
 * Creates a random password from ASCII letters
 * Given password length bounds
 *
 * @author arrnavvv
 * @date 18-09-2020
 */
public class PasswordGenerator {

    static String generatePassword(int min_length, int max_length){
        String smallAlphabet="abcdefghijklmnopqrstuvwxyz";
        String largeAlphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits="1234567890";
        String specialCharacters="!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        String all = smallAlphabet+largeAlphabet+digits+specialCharacters;
        Random random = new Random();
        int length = random.nextInt(max_length - min_length) + min_length;

        StringBuilder password = new StringBuilder();
        password.append((smallAlphabet.charAt(random.nextInt(26)))).
                append(largeAlphabet.charAt(random.nextInt(26))).
                append(digits.charAt(random.nextInt(10))).
                append(specialCharacters.charAt(random.nextInt(31)));

        int left = length-4;
        StringBuilder remainingPassword = new StringBuilder();
        while(left-->0){
            remainingPassword.append(all.charAt(random.nextInt(93)));
        }
        password.append(remainingPassword);

        ArrayList<Character> list = new ArrayList<>();
        for (char c : password.toString().toCharArray())
            list.add(c);
        Collections.shuffle(list);

        StringBuilder finalPassword = new StringBuilder();
        for(int i=0;i<list.size();i++){
            finalPassword.append(list.get(i));
        }
        return finalPassword.toString();
    }
    public static void main(String[] args) {

        String password = generatePassword(8,16);
        System.out.println("Your password is: "+password);
    }
}


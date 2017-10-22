import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/*
    Creates a random password from ASCII letters
    
    author: AKS1996
	date: 2017-10-22
*/

class PasswordGen {
    public static void main(String args[]){
        Random random = new Random();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*(){}?";

        String allChars = upper+lower+numbers+specialChars;

        List<Character> letters = new ArrayList<Character>();
        for(char c:allChars.toCharArray())
            letters.add(c);

        // Inbuilt method to randomly shuffle a elements of a list
        Collections.shuffle(letters);

        int min_length = 8;
        int max_length = 16;
        String password = "";

        // Note that size of the password is also random
        for(int i = random.nextInt(max_length-min_length) + min_length; i>0; --i) {
            password += letters.get(random.nextInt(letters.size()));
        }

        System.out.print("Password: " + password);
    }
}

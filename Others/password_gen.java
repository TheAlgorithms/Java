import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
/*
	Creates a random password from ASCII letters
	author: akshay sharma
	date: 2017-10-17
*/
class password_gen {
public static void main(String args[]){
        Random random = new Random();
        List<Character> letters = new ArrayList<>();
        for(char c:"ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
                letters.add(c);

        for(char c:"ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray())
                letters.add(c);

        for(char c:"0123456789".toCharArray())
                letters.add(c);

        for(char c:"!@#$%^&*(){}?".toCharArray())
                letters.add(c);


        Collections.shuffle(letters);

        int min_length = 8;
        int max_length = 16;
        String password = "";

        for(int i= random.nextInt(max_length-min_length) + min_length; i>0; --i) {
                password += letters.get(random.nextInt(letters.size()));
        }
        System.out.print("Password: " + password);
        System.out.print("[ If you are thinking of using this passsword, You better save it. ]");
}
}

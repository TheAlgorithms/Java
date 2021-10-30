import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        if (str.matches("[a-zA-Z ]+")) {

            String output = "";

            String[] word = str.split(" ");

            for (int i = 0; i < word.length; i++) {
                for (int j = 0; j < word[i].length(); j++) {

                    int count = 0;
                    char c = word[i].charAt(j);

                    for (int k = 0; k < word[i].length(); k++) {
                        if (c == word[i].charAt(k))
                            count++;
                    }

                    if (count == 1)
                        output += c;
                    else
                        output += Character.toUpperCase(c);
                }
                output += " ";
            }
            System.out.println(output);
        } else
            System.out.println(str + " is an invalid sentence");

    }
}

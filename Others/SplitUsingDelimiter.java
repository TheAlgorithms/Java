import java.util.ArrayList;
import java.util.Scanner;

public class SplitUsingDelimiter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> output = new ArrayList<>();
        int offset = 0;

        System.out.println("Enter string:");
        String str = input.nextLine();

        System.out.println("Enter delimiter:");
        String delim = input.nextLine();

        while (true) {
            int index = str.indexOf(delim, offset);
            if (index == -1) {
                output.add(str.substring(offset));
                break;
            } else {
                output.add(str.substring(offset, index));
                offset = (index + delim.length());
            }
        }

        for(int i = 0; i < output.size(); i++) {
            System.out.println("Part #" + i +": " + output.get(i));
        }
    }
}

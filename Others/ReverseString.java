package Others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This method produces a reversed version of a string
 *
 * @author Unknown
 */
public class ReverseString {

    /**
     * This method reverses the string str and returns it
     *
     * @param str String to be reversed
     * @return Reversed string
     */
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) return str;

        char[] arr = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }

    /**
     * Main Method
     *
     * @param args Command line arguments
     * @throws IOException Exception thrown because of BufferedReader
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string");
        String srr = br.readLine();
        System.out.println("Reverse=" + reverse(srr));
        br.close();
    }
}
		

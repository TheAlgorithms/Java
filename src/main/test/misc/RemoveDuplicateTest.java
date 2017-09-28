package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static misc.RemoveDuplicate.removeDuplicate;

/**
 * Created by Александр on 16.09.2017.
 */
class RemoveDuplicateTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        System.out.println("Actual string is: " + inputString);
        System.out.println("String after removing duplicates: " + removeDuplicate(inputString));
        br.close();
    }
}
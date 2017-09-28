package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static misc.ReverseString.reverse;


public class ReverseStringTest {

    /**
     * Main Method
     *
     * @param args Command line arguments
     * @throws IOException Exception thrown because of BufferedReader
     */
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string");
        String srr = br.readLine();
        System.out.println("Reverse=" + reverse(srr));
        br.close();
    }
}
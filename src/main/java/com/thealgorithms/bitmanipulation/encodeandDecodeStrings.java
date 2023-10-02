package com.thealgorithms.bitmanipulation;

/**
 * Create a method, encode, that converts an array of strings into a single string and then sends it over the network. Create another method, decode, that takes the encoded string and converts it back into the original array of strings.
 * Constraints:
 * 1≤ strings.length ≤ 200
 * 0≤ strings[i].length ≤ 200
 * strings[i] consists of any possible combinations of characters from 256 valid ASCII characters.


 * @author Anjita Gargi Chandora (<a href="https://github.com/anjitagargi">Git-anjitagargi</a>)
 */

 import java.util.*;
 import java.util.stream.Collectors;

public class EncodeandDecodeStrings {

    public static String lengthToBytes(String x) {
        // Express the string length as a string of bytes
        // If there are 4 characters in the string, the function will return "0004"
        int length = x.length(); // Compute the length of the string
        StringBuilder bytes = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            // Apply right shift operator
            bytes.append((char) (length >> (i * 8)));
        }

        return bytes.reverse().toString();
    }

    public static int bytesToLength(String bytesString) {
        // Convert a 4-byte string to an integer
        // If bytesString is "0040", the function will return 40
        int result = 0;

        for (char c : bytesString.toCharArray()) {
            result = result * 256 + c;
        }

        return result;
    }

    public static String encode(List<String> strings) {
        StringBuilder encodedString = new StringBuilder();

        for (String x : strings) {
            // Add appended 4-byte string length to string in encoded string
            encodedString.append(lengthToBytes(x)).append(x);
        }

        return encodedString.toString();
    }

    public static List<String> decode(String str) {
        // Initialize a pointer
        int i = 0;
        List<String> decodedString = new ArrayList<>();

        while (i < str.length()) {
            // Get length in integer from 4-byte string
            int length = bytesToLength(str.substring(i, i + 4));
            i += 4;
            // Add string of the computed length
            decodedString.add(str.substring(i, i + length));
            // Move the pointer
            i += length;
        }

        return decodedString;
    }

    public static void printEncoded(String str) {
        StringBuilder finalStr = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            for (char c : str.substring(i, i + 4).toCharArray()) {
                finalStr.append((int) c);
            }
            int length = bytesToLength(str.substring(i, i + 4));
            i += 4;
            finalStr.append(str.substring(i, i + length));
            i += length;
        }

        System.out.println(finalStr.toString());
    }

    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();
      
        input.add(new ArrayList<>(Arrays.asList("6^Hello_5", "5_World^6")));
        input.add(new ArrayList<>(Arrays.asList("I", "love", "programming")));
        input.add(new ArrayList<>(Arrays.asList("a", "b", "c", "d")));
       

        for (int i = 0; i < input.size(); i++) {
            String encoded = encode(input.get(i));
            System.out.println((i + 1) + ".\tInput = " + input.get(i).stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", ", "[", "]")));
            System.out.print("\tEncoded string = ");
            printEncoded(encoded);
            System.out.println("\tOutput = " + decode(encoded).stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", ", "[", "]")));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

    


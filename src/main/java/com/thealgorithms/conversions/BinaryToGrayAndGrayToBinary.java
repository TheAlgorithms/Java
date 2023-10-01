package com.thealgorithms.conversions;

public class BinaryToGrayAndGrayToBinary {

    // Function to convert binary to Gray code
    public static String binaryToGray(String binary) {
        StringBuilder gray = new StringBuilder();
        gray.append(binary.charAt(0));

        for (int i = 1; i < binary.length(); i++) {
            // XOR operation between adjacent bits
            gray.append((binary.charAt(i - 1) == binary.charAt(i)) ? '0' : '1');
        }

        return gray.toString();
    }

    // Function to convert Gray code to binary
    public static String grayToBinary(String gray) {
        StringBuilder binary = new StringBuilder();
        binary.append(gray.charAt(0));

        for (int i = 1; i < gray.length(); i++) {
            // XOR operation between the previous binary bit and the current Gray bit
            if (gray.charAt(i) == '0') {
                binary.append(binary.charAt(i - 1));
            } else {
                binary.append((binary.charAt(i - 1) == '0') ? '1' : '0');
            }
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        String binaryInput = "100010"; // Replace with your binary input
        String grayInput = "111111";   // Replace with your Gray code input

        String grayResult = binaryToGray(binaryInput);
        String binaryResult = grayToBinary(grayInput);

        System.out.println("Binary to Gray: " + grayResult);
        System.out.println("Gray to Binary: " + binaryResult);
    }
}

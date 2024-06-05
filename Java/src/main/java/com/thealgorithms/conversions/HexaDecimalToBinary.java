package com.thealgorithms.conversions;

// Hex [0-9],[A-F] -> Binary [0,1]
public class HexaDecimalToBinary {
    public String convert(String numHex) {
        // String a HexaDecimal:
        int conHex = Integer.parseInt(numHex, 16);
        // Hex a Binary:
        String binary = Integer.toBinaryString(conHex);
        // Output:
        return completeDigits(binary);
    }

    public String completeDigits(String binNum) {
        final int longBits = 8;
        for (int i = binNum.length(); i < longBits; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }

    public static void main(String[] args) {
        // Testing Numbers:
        String[] hexNums = {
            "1",
            "A1",
            "ef",
            "BA",
            "AA",
            "BB",
            "19",
            "01",
            "02",
            "03",
            "04",
        };
        HexaDecimalToBinary objConvert = new HexaDecimalToBinary();

        for (String num : hexNums) {
            System.out.println(num + " = " + objConvert.convert(num));
        }
    }
}

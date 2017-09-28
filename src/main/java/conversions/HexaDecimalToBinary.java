package conversions;


public class HexaDecimalToBinary {
    private static final int LONG_BITS = 8;

    public static String convert(String numHex) {
        int conHex = Integer.parseInt(numHex, 16);
        String binary = Integer.toBinaryString(conHex);
        String answer = completeDigits(binary);
        System.out.println(numHex + " = " + answer);
        return answer;
    }

    private static String completeDigits(String binNum) {
        for (int i = binNum.length(); i < LONG_BITS; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }
}
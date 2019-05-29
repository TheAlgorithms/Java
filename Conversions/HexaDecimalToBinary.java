package Conversions;

public class HexaDecimalToBinary {

    private final int LONG_BITS = 8;

    public void convert(String numHex) {
        // String a HexaDecimal:
        int conHex = Integer.parseInt(numHex, 16);
        // Hex a Binary:
        String binary = Integer.toBinaryString(conHex);
        // Presentation:
        System.out.println(numHex + " = " + completeDigits(binary));
    }

    public String completeDigits(String binNum) {
        for (int i = binNum.length(); i < LONG_BITS; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }

    public static void main(String[] args) {

        //Testing Numbers:
        String[] hexNums = {"1", "A1", "ef", "BA", "AA", "BB",
                "19", "01", "02", "03", "04"};
        HexaDecimalToBinary objConvert = new HexaDecimalToBinary();

        for (String num : hexNums) {
            objConvert.convert(num);
        }
    }
}

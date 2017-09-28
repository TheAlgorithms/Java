package conversions;

/**
 * Converts any Octal number to a Binary number
 *
 * @author Zachary Jones
 */
public class OctalToBinary {

    /**
     * This method converts an octal number
     * to a binary number.
     *
     * @param o The octal number
     * @return The binary number
     */
    public static int convertOctalToBinary(int o) {
        return getVal(o);
    }

    private static int getVal(int num) {
        System.out.println("Octal to Binary");
        num = Integer.parseInt(String.valueOf(num), 8);
        convert(num);
        return num;
    }

    private static void convert(int num) {
        String binary = Integer.toBinaryString(num);
        System.out.println("Binary Value is : " + binary);
    }
}
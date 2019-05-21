package src.main.java.com.conversions;

/**
 * Convert the binary number into gray code
 */
public class BinaryToGray {

    /**
     * convert the binary number into gray code
     *
     * @param binaryCode binary number
     * @return grayCode return as string
     */
    public String binaryToGray(String binaryCode) {
        StringBuilder grayCode = new StringBuilder(Character.toString(binaryCode.charAt(0)));

        for (int i = 0; i < binaryCode.length() - 1; i++) {
            if (binaryCode.charAt(i) == binaryCode.charAt(i + 1))
                grayCode.append("0");
            else
                grayCode.append("1");
        }
        return grayCode.toString();
    }

}

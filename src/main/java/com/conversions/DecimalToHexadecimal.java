package src.main.java.com.conversions;

import java.math.BigInteger;

public class DecimalToHexadecimal {
    private static final char hexChars[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private static final BigInteger valueHex = new BigInteger("16");

    /**
     * This method converts and decimal number to a Hexadecimal number
     * @param decimalStr
     * @return hexadecimal number
     */
    public String decimalToHex(String decimalStr){
        BigInteger decimal = new BigInteger(decimalStr);

        int rem;
        String hex = "";
        while (decimal.compareTo(BigInteger.ZERO) > 0) {
            rem = decimal.mod(valueHex).intValueExact();
            hex = hexChars[rem] + hex;
            decimal = decimal.divide(valueHex);
        }
        return hex;
    }
}

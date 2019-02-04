package src.main.java.com.conversions;

import java.math.BigInteger;

public class DecimalToOctal {
    private static final char octalChars[] = {'0','1','2','3','4','5','6','7'};
    private static final BigInteger valueOctal = new BigInteger("8");

    /**
     * This method converts and decimal number to a octal number
     * @param decimalStr
     * @return octal number
     */
    public String decimalToOctal(String decimalStr){
        BigInteger decimal = new BigInteger(decimalStr);

        int rem;
        String octal = "";
        while(decimal.compareTo(BigInteger.ZERO) > 0) {
            rem = decimal.mod(valueOctal).intValueExact();
            octal = octalChars[rem] + octal;
            decimal = decimal.divide(valueOctal);
        }
        return octal;
    }
}

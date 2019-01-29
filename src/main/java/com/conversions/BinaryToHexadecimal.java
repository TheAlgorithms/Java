package src.main.java.com.conversions;

import java.math.BigInteger;
import java.util.HashMap;

public class BinaryToHexadecimal {

    /**
     * This method converts a binary number to
     * a hexadecimal number.
     *
     * @param binary The binary number
     * @return The hexadecimal number
     */

    public String binToHex(BigInteger binary) {
        //hm to store hexadecimal codes for binary numbers within the range: 0000 to 1111 i.e. for decimal numbers 0 to 15
        HashMap<Integer,String> hmHexadecimal = new HashMap<>();

        //String to store hexadecimal code
        String hex="";
        int i;
        for(i=0 ; i<10 ; i++)
            hmHexadecimal.put(i, String.valueOf(i));

        for(i=10 ; i<16 ; i++)
            hmHexadecimal.put(i,String.valueOf((char)('A'+i-10)));

        int currentbit;
        BigInteger tenValue = new BigInteger("10");
        while(binary.compareTo(BigInteger.ZERO) != 0) {
            int code4 = 0;	//to store decimal equivalent of number formed by 4 decimal digits
            for(i=0 ; i<4 ; i++)
            {
                currentbit = binary.mod(tenValue).intValueExact();
                binary = binary.divide(tenValue);
                code4 += currentbit*Math.pow(2, i);
            }
            hex= hmHexadecimal.get(code4) + hex;
        }
        return hex;

    }
}
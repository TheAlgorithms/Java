package src.main.java.com.conversions;

import java.util.HashMap;

public class BinaryToHexadecimal {

    /**
     * This method converts a binary number to
     * a hexadecimal number.
     *
     * @param binary The binary number
     * @return The hexadecimal number
     */

    public String binToHex(long binary) {
        //hm to store hexadecimal codes for binary numbers within the range: 0000 to 1111 i.e. for decimal numbers 0 to 15
        HashMap<Integer,String> hmHexadecimal = new HashMap<>();

        //String to store hexadecimal code
        String hex="";
        int i;
        for(i=0 ; i<10 ; i++)
            hmHexadecimal.put(i, String.valueOf(i));

        for(i=10 ; i<16 ; i++)
            hmHexadecimal.put(i,String.valueOf((char)('A'+i-10)));

        long currentbit;
        while(binary != 0) {
            int code4 = 0;	//to store decimal equivalent of number formed by 4 decimal digits
            for(i=0 ; i<4 ; i++)
            {
                currentbit = binary % 10;
                binary = binary / 10;
                code4 += currentbit * Math.pow(2, i);
            }
            hex= hmHexadecimal.get(code4) + hex;
        }
        return hex;

    }
}
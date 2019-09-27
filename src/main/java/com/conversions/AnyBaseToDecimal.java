package com.conversions;

public class AnyBaseToDecimal {
    /**
     * This method produces a decimal value of any given input number of any base
     *
     * @param inpNum String of which we need the decimal value and base in integer format
     * @return string format of the decimal value
     */

    public String convertToDecimal(String inpNum, int base) {
        int len = inpNum.length();
        int num = 0;
        int pow = 1;

        for (int i = len - 1; i >= 0; i--) {
            if (valOfChar(inpNum.charAt(i)) >= base) {
                return "Invalid Number";
            }
            num += valOfChar(inpNum.charAt(i)) * pow;
            pow *= base;
        }
        return String.valueOf(num);
    }

    /**
     * This method produces integer value of the input character and returns it
     *
     * @param c Char of which we need the integer value of
     * @return integer value of input char
     */

    private static int valOfChar(char c) {
        if (c >= '0' && c <= '9') {
            return (int) c - '0';
        } else {
            return (int) c - 'A' + 10;
        }
    }

    public String convertToDecimal2(String inpNum, int base) {
        int len = inpNum.length();
        int num = 0;
        int pow = 1;

        for (int i = len - 1; i >= 0; i--)
        {
            int valOfChar = 0;
            char c = inpNum.charAt(i);
            if (c >= '0' && c <= '9') {
                valOfChar = (int) c - '0';
            } else {
                valOfChar = (int) c - 'A' + 10;
            }

            if (valOfChar >= base)
            {
                return "Invalid Number";
            }
            num += valOfChar * pow;
            pow *= base;
        }
        return String.valueOf(num);
    }

}

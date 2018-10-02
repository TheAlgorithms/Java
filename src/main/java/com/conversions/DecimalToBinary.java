package src.main.java.com.conversions;

import java.util.ArrayList;

public class DecimalToBinary{


    /**
     * This method converts a decimal numbuer to a binary number using
     * a conventional algorithm.
     * @param inp Decimal of which we need the value in binary
     * @return string format of the converted value in binary base
     */
    public String conventionalConversion(int inp){
        ArrayList<Character> charArr = new ArrayList<>();

        while(inp != 0){
            charArr.add(reVal(inp % 2));
            inp /= 2;
        }

        StringBuilder str = new StringBuilder(charArr.size());
        for(Character ch: charArr) {
            str.append(ch);
        }

        return str.reverse().toString();
    }

    /**
     * This method converts a decimal numbuer to a binary number using
     * a bitwise algorithm.
     * @param inp Decimal of which we need the value in binary
     * @return string format of the converted value in binary base
     */
    public String bitwiseConversion(int inp){
        ArrayList<Character> charArr = new ArrayList<>();

        while(inp != 0){
            charArr.add(reVal(inp & 1));
            inp >>= 1;
        }

        StringBuilder str = new StringBuilder(charArr.size());
        for(Character ch: charArr) {
            str.append(ch);
        }

        return str.reverse().toString();
    }

    /**
     * This method produces character value of the input integer and returns it
     * @param num integer of which we need the character value of
     * @return character value of input integer
     */

    private char reVal(int num) {
        if (num >= 0 && num <= 9)
            return (char)(num + '0');
        else
            return (char)(num - 10 + 'A');
    }
}
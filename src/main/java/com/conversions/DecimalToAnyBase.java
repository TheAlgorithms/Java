package src.main.java.com.conversions;

import java.util.ArrayList;

public class DecimalToAnyBase {
    /**
     * This method produces a String value of any given input decimal in any base
     * @param inp Decimal of which we need the value in base in String format
     * @param base base in which we want the decimal value to be converted into
     * @return string format of the converted value in the given base
     */

    public String convertToAnyBase(int inp, int base) {
        ArrayList<Character> charArr = new ArrayList<>();

        while (inp > 0) {
            charArr.add(reVal(inp%base));
            inp /= base;
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

package com.conversions;

public class DecimalToBCD{
     /**
     * This method produces a BCD value of any given input number
     *
     * @param Input  Decimal of which we need the value in BCD
     * @return string format of the converted value in BCD
     */
    public String decimaltoBCD(long Input) {
        String inBCD = "";
        while (Input > 0) {
            Long digit = Input % 10;
            String digitBCD = Long.toBinaryString(digit);
            while (digitBCD.length() < 4) {
                digitBCD = "0" + digitBCD;
            }
            inBCD = digitBCD + inBCD;
            Input /= 10;
        }
        return inBCD;
    }
}
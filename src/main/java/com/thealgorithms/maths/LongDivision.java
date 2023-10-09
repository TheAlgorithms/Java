//        Given two integers dividend and divisor, divide two integers without using multiplication,
//        division, and mod operator.
//
//        The integer division should truncate toward zero, which means losing its fractional part.
//        For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2. My
//        method used Long Division, here is the source
//        "https://en.wikipedia.org/wiki/Long_division"

package com.thealgorithms.maths;

public class LongDivision {
    public static int divide(int dividend, int divisor) {
        long new_dividend_1 = dividend;
        long new_divisor_1 = divisor;

        if (divisor == 0) {
            return 0;
        }
        if (dividend < 0) {
            new_dividend_1 = new_dividend_1 * -1;
        }
        if (divisor < 0) {
            new_divisor_1 = new_divisor_1 * -1;
        }

        if (dividend == 0 || new_dividend_1 < new_divisor_1) {
            return 0;
        }

        StringBuilder answer = new StringBuilder();

        String dividend_string = "" + new_dividend_1;
        int last_index = 0;

        String remainder = "";

        for (int i = 0; i < dividend_string.length(); i++) {
            String part_v1 = remainder + "" + dividend_string.substring(last_index, i + 1);
            long part_1 = Long.parseLong(part_v1);
            if (part_1 > new_divisor_1) {
                int quotient = 0;
                while (part_1 >= new_divisor_1) {
                    part_1 = part_1 - new_divisor_1;
                    quotient++;
                }
                answer.append(quotient);
            } else if (part_1 == new_divisor_1) {
                int quotient = 0;
                while (part_1 >= new_divisor_1) {
                    part_1 = part_1 - new_divisor_1;
                    quotient++;
                }
                answer.append(quotient);
            } else if (part_1 == 0) {
                answer.append(0);
            } else if (part_1 < new_divisor_1) {
                answer.append(0);
            }
            if (!(part_1 == 0)) {
                remainder = String.valueOf(part_1);
            } else {
                remainder = "";
            }

            last_index++;
        }

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            try {
                return Integer.parseInt(answer.toString()) * (-1);
            } catch (NumberFormatException e) {
                return -2147483648;
            }
        }
        try {
            return Integer.parseInt(answer.toString());
        } catch (NumberFormatException e) {
            return 2147483647;
        }
    }
}

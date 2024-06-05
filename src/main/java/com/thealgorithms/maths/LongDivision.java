//        Given two integers dividend and divisor, divide two integers without using multiplication,
//        division, and mod operator.
//
//        The integer division should truncate toward zero, which means losing its fractional part.
//        For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2. My
//        method used Long Division, here is the source
//        "https://en.wikipedia.org/wiki/Long_division"

package com.thealgorithms.maths;

public final class LongDivision {
    private LongDivision() {
    }
    public static int divide(int dividend, int divisor) {
        long newDividend1 = dividend;
        long newDivisor1 = divisor;

        if (divisor == 0) {
            return 0;
        }
        if (dividend < 0) {
            newDividend1 = newDividend1 * -1;
        }
        if (divisor < 0) {
            newDivisor1 = newDivisor1 * -1;
        }

        if (dividend == 0 || newDividend1 < newDivisor1) {
            return 0;
        }

        StringBuilder answer = new StringBuilder();

        String dividendString = "" + newDividend1;
        int lastIndex = 0;

        String remainder = "";

        for (int i = 0; i < dividendString.length(); i++) {
            String partV1 = remainder + "" + dividendString.substring(lastIndex, i + 1);
            long part1 = Long.parseLong(partV1);
            if (part1 > newDivisor1) {
                int quotient = 0;
                while (part1 >= newDivisor1) {
                    part1 = part1 - newDivisor1;
                    quotient++;
                }
                answer.append(quotient);
            } else if (part1 == newDivisor1) {
                int quotient = 0;
                while (part1 >= newDivisor1) {
                    part1 = part1 - newDivisor1;
                    quotient++;
                }
                answer.append(quotient);
            } else if (part1 == 0) {
                answer.append(0);
            } else if (part1 < newDivisor1) {
                answer.append(0);
            }
            if (!(part1 == 0)) {
                remainder = String.valueOf(part1);
            } else {
                remainder = "";
            }

            lastIndex++;
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

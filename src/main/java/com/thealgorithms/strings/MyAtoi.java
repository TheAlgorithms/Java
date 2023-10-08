// Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer
// (similar to C/C++'s atoi function). Here is my implementation

package com.thealgorithms.strings;

public class MyAtoi {
    public static int myAtoi(String s) {
        s = s.trim();
        char[] char_1 = s.toCharArray();
        String number = "";
        boolean negative = false;
        boolean zero = false;
        boolean isDigit = false;

        for (char ch : char_1) {
            if (Character.isDigit(ch)) {
                if (number.length() > 1 && !isDigit) {
                    number = "0";
                    break;
                }
                isDigit = true;
                if (zero) {
                    number = "0";
                    break;
                }
                if (ch >= '0' && ch <= '9') number += ch;
            } else if (ch == '-' && !isDigit) {
                number += "0";
                negative = true;
            } else if (ch == '+' && !isDigit) {
                number += "0";
            } else if (ch == '.' && isDigit) {
                break;
            } else if (ch == '.') {
                zero = true;
            } else {
                if (!isDigit) {
                    number = "0";
                }
                break;
            }
        }

        if (!isDigit) {
            return 0;
        }

        number = number.replaceFirst("^0+(?!$)", "");

        if (number.length() > 10 && negative) {
            return -2147483648;
        } else if (number.length() > 10) {
            return 2147483647;
        } else if (number.length() == 10 && negative) {
            double db1 = Double.parseDouble(number);
            if (db1 >= 2147483648d) {
                return -2147483648;
            }
        } else if (number.length() == 10) {
            double db1 = Double.parseDouble(number);
            if (db1 > (2147483647)) {
                return 2147483647;
            }
        }

        if (negative) {
            return Integer.parseInt(number) * -1;
        }

        return Integer.parseInt(number);
    }
}

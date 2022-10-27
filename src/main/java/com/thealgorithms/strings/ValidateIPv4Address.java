package com.thealgorithms.strings;

/**
 * Validate an ip address as a valid ipv4 or not
 *
 * @author Sineth Sankalpa (https://github.com/sinsankio)
 * */

import java.util.regex.Pattern;

public class ValidateIPv4Address {
    private final String PATTERN_REGEX = "^(0?(25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$";

    public boolean isValidIPv4Address(String ipv4) {
        /**
         * @param ipv4 a string which represents the ip address to be validated
         * @return true or false if valid or not respectively
         */
        if(ipv4 == null || ipv4.length() <= 0) {
            return false;
        }
        return Pattern.matches(PATTERN_REGEX, ipv4);
    }

    public static void main(String[] args) {
        ValidateIPv4Address validateIPv4Address = new ValidateIPv4Address();
        String ipv4List[] = {"10.93.2.145", "128.75.80.99", "255.255.255.255", "192.32.64.0", "10.5.9.1", "01.023.035.089",
        "0192.065.032.012", "0192.65.032.12", "255.255.198.256", "175.45.21.20.2", "100.90.88.-1", "70.102.2", "191.70.32.1.3"};

        for(int i = 0; i < ipv4List.length; i++) {
            String ipv4 = ipv4List[i];

            System.out.printf("Is %s a valid ipv4 address: %b\n", ipv4,
                    validateIPv4Address.isValidIPv4Address(ipv4));
        }
    }
}

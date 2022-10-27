package com.thealgorithms.strings;

/**
 * Validate a phone number as a sri lankan phone number or not
 *
 * @author Sineth Sankalpa (https://github.com/sinsankio)
 * */

import java.util.regex.Pattern;

public class ValidateSriLankanPhoneNumber {
    // References: https://aye.sh/blog/sri-lankan-phone-number-regex
    private final String PATTERN_REGEX = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$";
    public boolean isValidPhoneNumber(String phoneNumber) {
        /**
         * @param phoneNumber a string which represents the phone number to be validated
         * @return true or false if valid or not respectively
         */
        if(phoneNumber == null || phoneNumber.length() <= 0) {
            return false;
        }
        return Pattern.matches(PATTERN_REGEX, phoneNumber);
    }
    public static void main(String[] args) {
        ValidateSriLankanPhoneNumber validateSriLankanPhoneNumber =
                new ValidateSriLankanPhoneNumber();
        String phoneNumbers[] = {"0475682163", "0718382399", "94773283048", "+94777920924", "00949835756",
                "0112136745", "+94717589727", "0123456789", "+94 701235478", "78423567"};

        for(int i = 0; i < phoneNumbers.length; i++) {
            String phoneNumber = phoneNumbers[i];
            System.out.printf("Is %s a valid phone number: %b\n", phoneNumber,
                    validateSriLankanPhoneNumber.isValidPhoneNumber(phoneNumber));
        }
    }
}

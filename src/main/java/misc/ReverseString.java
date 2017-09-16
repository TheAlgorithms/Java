package misc;

/**
 * This method produces a reversed version of a string
 *
 * @author Unknown
 */
class ReverseString {

    /**
     * This method reverses the string str and returns it
     *
     * @param str String to be reversed
     * @return Reversed string
     */
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) return str;

        char arr[] = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }
}


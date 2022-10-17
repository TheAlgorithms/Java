package com.thealgorithms.strings;

/**
 * Reverse String using different version
 */
public class ReverseString {

    public static void main(String[] args) {
        assert reverse("abc123").equals("321cba");
        assert reverse2("abc123").equals("321cba");
        assert reverse3("abc123").equals("321cba");
    }

    /**
     * easiest way to reverse the string str and return it
     *
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * second way to reverse the string str and return it
     *
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse2(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] value = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
        return new String(value);
    }
    
    /**
     * third way to reverse the string str and return it reducing the time complexity
     *
     * @param str string to be reversed
     * @return reversed string
     */
    public static String reverse3(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        int len=str.length();
        String s1="",s2="";
        for(int i=0, j=len-1; i<len/2; i++, j--)
        {
            s1=str.charAt(i)+s1;
            s2+=str.charAt(j);
        }
        if(len%2==0)
        return new String(s2+s1);
        else
        return new String(s2+str.charAt(len/2)+s1);
    }
}

package ciphers;

/**
 * A Java implementation of Vigenere Cipher.
 * @author  straiffix
 */


public class Vigenere  {

    public  static String encrypt(final String message, final String key)
    {

        String result = "";

        for (int i = 0, j = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result += (char) ((c + key.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            } else if (c >= 'a' && c <= 'z') {
                result += (char) ((c + key.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a');
                j = ++j % key.length();
            } else {
                result+=c;
                continue;
            }
        }
        return result;
    }

    public static String decrypt( final String message, final String key)
    {
        String result ="";

        for(int i = 0, j = 0; i < message.length(); i++){

            char c = message.charAt(i);
            if((c >= 'A' && c <= 'Z')){
                result += ((char)('Z'-(25-(c-key.toUpperCase().charAt(j)))%26));
            }
            else if(c >= 'a' && c <= 'z'){
                result += ((char)('z'-(25-(c-key.toLowerCase().charAt(j)))%26));
            }
            else{
                result+=c;
                continue;
            }
            j = ++j % key.length();
        }
        return result;
    }
    public static void main (String [] args){
        String text="Hello World!";
        String key="itsakey";
        System.out.println(text);
        String ciphertext=encrypt(text, key);
        System.out.println(ciphertext);
        System.out.println(decrypt(ciphertext, key));

    }
}
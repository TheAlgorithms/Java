public class ROTN {
    /**
     * Method that solves ROT N cipher
     * @param encrypted message that is encrypted with ROT N cipher
     * @param rot amount encrypted message was rotated
     * @return decrypted message
     */
    public String solveROTN(String encrypted, int rot) {
        char[] arr = encrypted.toCharArray();
        char[] decrypted = new char[arr.length];
        int rotation = 26-rot;
        int i=0;
        for (char c : arr) {
            if (c>='A' && c<='Z')
                decrypted[i] = (char)(((c-'A')+rotation)%26+'A');
            else if (c>='a' && c<='z')
                decrypted[i] = (char)(((c-'a')+rotation)%26+'a');
            else
                decrypted[i] = c;
            i++;
        }
        return new String(decrypted);
    }
}

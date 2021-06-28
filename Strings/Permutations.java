public class Permutations {

    /**
     * Takes the string whose permutations are to be printed.
     * 
     * @param str
     */
    public static void permutation(String str) {
        permutation(str, "");
    }

    /**
     * Prints the permutations by recursively selecting all possible character at a
     * position.
     * 
     * @param str
     * @param prefix
     */
    private static void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        String str = "cat";
        permutation(str);
    }
}

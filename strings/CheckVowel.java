package strings;

public class CheckVowel {

    /**
     * fixing an issue
     * this method will accept the string and count the vowel present in the string.
     * count will be return which will tell the number of vowels present
     *
     * @param s
     * @return
     *

     */
    public static int numberOfVowelsInString(String s){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(isVowelChar(s.charAt(i)))
                count++;
        }
        return count;
    }

    /**
     * this method is used to check whether a character is vowel or not
     *
     * @param ch
     * @return
     */
    private static boolean isVowelChar(char ch){
        return ((ch=='a' ||ch=='e' ||ch=='i' ||ch=='o' ||ch=='u')||
                (ch=='A' ||ch=='E' ||ch=='I' ||ch=='O' ||ch=='U'));
    }

    /** Driver Code */
    public static void main(String[] args) {
        System.out.print(numberOfVowelsInString("This is a test code."));
    }
}

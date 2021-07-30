package strings;

public class CheckVowel {

    /**
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
            if(s.charAt(i)=='a' ||s.charAt(i)=='e' ||s.charAt(i)=='i' ||s.charAt(i)=='o' ||s.charAt(i)=='u')
                count++;
        }
        return count;
    }

    /** Driver Code */
    public static void main(String[] args) {
        System.out.print(numberOfVowelsInString("This is a test code."));
    }
}

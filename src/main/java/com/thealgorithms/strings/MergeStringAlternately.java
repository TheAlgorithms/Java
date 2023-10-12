
import java.util.*;
class Sample {
    public static String mergeAlternately(String word1, String word2) {
        String res = "";
        int i = 0;
        while (i< word1.length() || i < word2.length()){
            if (i < word1.length()) res += word1.charAt(i);
            if (i < word2.length()) res += word2.charAt(i);
            i+= 1;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter word 1: ");
        String s1 = sc.nextLine();
        System.out.println("Enter word 2: ");
        String s2 = sc.nextLine();
        String res = mergeAlternately(s1,s2);
        System.out.println("The result is : "+ res);
    }
}

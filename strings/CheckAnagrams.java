import java.util.Scanner;

public class Solution {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        int i,j;
        a = a.toUpperCase();
        b = b.toUpperCase();
        if(a.length() != b.length()){
            return false;
        }
        int[] c = new int[26];
         int[] d = new int[26];

        for(i = 0;i < a.length();i++){
              c[a.charAt(i)-'A']++;
                d[b.charAt(i)-'A']++;
        }
        for(j = 0;j < 26;j++){
                if(c[j] != d[j]) return false;
          }
     return true;
        
    }

    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}

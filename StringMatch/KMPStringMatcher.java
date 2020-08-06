package StringMatch;

import java.util.ArrayList;
import java.util.List;
public class KMPStringMatcher implements Matcher{
    private static boolean isEmptyString(String... texts){
        for(String str : texts){
            if(str == null || str.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param text		input text
     * @param pattern	input pattern to be found in text
     * Preprocessing time: Theta(m)
     * Matching time: Theta(n)
     */
    public static List<Integer> match(String text, String pattern) {
        List<Integer> list = new ArrayList<>();
        if(isEmptyString(text,pattern)){
            return list;
        }
        int n = text.length();
        int m = pattern.length();
        int[] prefixFunction = computePrefixFunction(pattern);
        int q=0;
        for(int i=0;i<=(n-1);i++) {
            while(q>0 && pattern.charAt(q)!=text.charAt(i)) {
                q = prefixFunction[q-1];
            }
            if(pattern.charAt(q)==text.charAt(i)) {
                q++;
            }
            if(q==m) {
                list.add(i-m+1);
                q = prefixFunction[q-1];
            }
        }
        return list;
    }

    public static void matchPrint(String text, String pattern){
        for(int index : match(text,pattern)){
            System.out.print("Pattern occurs with shift\t" + index+"\n");
        }
    }

    //helper to stringMatcher
    //calculates prefix function
    private static int[] computePrefixFunction(String pattern) {
        int m=pattern.length();
        int[] result = new int[m];
        result[0] =0;
        int k=0;
        for(int q =1; q<=(m-1);q++) {
            while(k>0 && (pattern.charAt(k+1) != pattern.charAt(q)) ) {
                k = result[k];
            }
            if(pattern.charAt(k+1) == pattern.charAt(q)) {
                k++;
            }
            result[q]=k;
        }
        return result;
    }

    //there must not be object of this class
    private KMPStringMatcher() {
        throw new IllegalStateException("Utility class");
    }

}

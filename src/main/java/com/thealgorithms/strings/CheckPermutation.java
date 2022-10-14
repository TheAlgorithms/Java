package com.thealgorithms.strings;

import java.util.Arrays;

public class CheckPermutation {
    public static String sort(String s){
        char[] content = s.toCharArray();
        Arrays.sort(content);

        return new String(content);
    }

    public static boolean isPermutation(String str1, String str2) {
        //Your code goes here
        if (str1.length() == 0 && str2.length() == 0)
            return true;
        if (str1.length() == 0 || str2.length() == 0)
            return false;
        if (str1.length() != str2.length()){
            return false;
        }
        String che1= sort(str1);
        String che2 =sort(str2);
        return (che1).equals(che2);
    }
}


// 	public static void main(String[] args) {
// 		// TODO Auto-generated method stub

// 	}
// }

    


    


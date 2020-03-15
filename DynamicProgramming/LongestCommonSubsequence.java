package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

class LongestCommonSubsequence {
    @SuppressWarnings({"assignment.type.incompatible", "cast.unsafe", "array.access.unsafe.high"})
    /* Cast unsafe arrises whenever the compiler is unable to prove the code statically.*/
    // array.access.unsafe.high arrises whenever the compiler finds an expression used to index and cannot prove it statically whether the index is valid or not.
    public static String getLCS(String str1, String str2) {

        //At least one string is null
        if (str1 == null || str2 == null)
            return null;

        //At least one string is empty
        if (str1.length() == 0 || str2.length() == 0)
            return "";

        String @SameLen("str1")[] arr1 = str1.split("");
        String @SameLen("str2")[] arr2 = str2.split("");

        //lcsMatrix[i][j]  = LCS of first i elements of arr1 and first j characters of arr2
        int @MinLen(2) [] @MinLen(2)[] lcsMatrix = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i < arr1.length + 1; i++)
            lcsMatrix[i][0] = 0;
        for (int j = 1; j < (@LTLengthOf("lcsMatrix[0]") int)arr2.length + 1; j++)
            lcsMatrix[0][j] = 0;
        for (int i = 1; i < (@LTLengthOf("lcsMatrix") int)arr1.length + 1; i++) {
            for (int j = 1; j < (@LTEqLengthOf("lcsMatrix[i]") int )(arr2.length + 1); j++) {
                if (arr1[(@IndexFor("arr1") int)(i - 1)].equals(arr2[(@IndexFor("arr2") int)(j - 1)])) {
                    lcsMatrix[i][j] = lcsMatrix[i - 1][j - 1] + 1;
                } else {
                    lcsMatrix[i][j] = lcsMatrix[i - 1][j] > lcsMatrix[i][j - 1] ? lcsMatrix[i - 1][j] : lcsMatrix[i][j - 1];
                }
            }
        }
        return lcsString(str1, str2, (int @LTLengthOf("#1") [] @LTLengthOf("#2") [] )lcsMatrix);
    }
    @SuppressWarnings({"cast.unsafe", "array.access.unsafe.high"})
    public static String lcsString(String str1, String str2, int @LTLengthOf("#1") [] @LTLengthOf("#2") [] lcsMatrix) {
        StringBuilder lcs = new StringBuilder();
        int i = str1.length(),
                j = str2.length();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (lcsMatrix[(@IndexFor("lcsMatrix") int)(i - 1)][j] > lcsMatrix[(@IndexFor("lcsMatrix") int)i][(@IndexFor("lcsMatrix[i]") int)(j - 1)]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "DSGSHSRGSRHTRD";
        String str2 = "DATRGAGTSHS";
        String lcs = getLCS(str1, str2);

        //Print LCS
        if (lcs != null) {
            System.out.println("String 1: " + str1);
            System.out.println("String 2: " + str2);
            System.out.println("LCS: " + lcs);
            System.out.println("LCS length: " + lcs.length());
        }
    }
}

/*
Implementation of Knuth–Morris–Pratt algorithm
Usage: 
final String T = "AAAAABAAABA";
final String P = "AAAA";
KMPmatcher(T, P);
*/
public class KMP {

    // find the starting index in string T[] that matches the search word P[]
    public void KMPmatcher(final String T, final String P) {
        final int m = T.length();
        final int n = P.length();
        final int[] pi = computePrefixFunction(P);
        int q = 0;
        for (int i = 0; i < m; i++) {
            while (q > 0 && T.charAt(i) != P.charAt(q)) {
                q = pi[q - 1]; 
            }

            if (T.charAt(i) == P.charAt(q)) {
                q++;
            }

            if (q == n) {
                System.out.println("Pattern starts: " + (i + 1 - n));
                q = pi[q - 1];  
            }
        }

    }

    // return the prefix function 
    private int[] computePrefixFunction(final String P) {
        final int n = P.length();
        final int[] pi = new int[n];
        pi[0] = 0;
        int q = 0;
        for (int i = 1; i < n; i++) {
            while (q > 0 && P.charAt(q) != P.charAt(i)) {
                q = pi[q - 1];
            }

            if (P.charAt(q) == P.charAt(i)) {
                q++;
            }

            pi[i] = q;

        }

        return pi;
    }
}

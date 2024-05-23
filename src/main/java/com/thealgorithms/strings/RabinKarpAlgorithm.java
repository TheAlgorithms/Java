package com.thealgorithms.strings;

public class RabinKarpAlgorithm {
    static final int d = 256;
    static final int q = 101;

    //M = length of pattern
    // N = length 0f text
    static void robinKarpSearch(String pat,String txt,int M,int N){
        int h = 1;
        for (int i = 1; i <=M-1 ; i++) {
            h = (h*d)%q;
        }
        // Hash-value for pattern
        // Hash-value for text
        int p = 0,t = 0;
        for (int i = 0; i < M; i++) {
            p = (p*d+pat.charAt(i))%q;
            t = (t*d+txt.charAt(i))%q;
        }
        //Slide the pattern over text one by one
        for (int i = 0; i <=(N-M) ; i++) {
            //check the hash values of current window of
            //text and pattern. if hash-value match then only
            // check for characters one by one
            if(p==t){
                //checking for character one by one
                boolean flag = true;
                for (int j = 0; j < M; j++) {
                    if(txt.charAt(i+j)!=pat.charAt(j)){
                        flag = false;
                        break;
                    }
                }if (flag==true){
                    System.out.println(i+" ");
                }
            }
            //Calculate hash-value for next window of text:
            // Remove leading digit. Add trailing digit
            if(i<N-M){
                t = ((d*(t-txt.charAt(i)*h))+txt.charAt(i+M))%q;
            }

            //We might get negative value of t,
            // converting it to positive
            if(t<0){
                t = t+q;
            }
        }
    }

    public static void main(String[] args) {
        String txt = "ABCCDDAEFG";
        String pat = "CDD";
        System.out.println("All index numbers where patter found: ");
        robinKarpSearch(pat,txt,pat.length(),txt.length());
    }
}

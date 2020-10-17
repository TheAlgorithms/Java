/**
 * CREATED BY ROSHAN SINGH
 *
 */
import java.io.*;
import java.util.*;
class Main{
    static class FastScanner {  // for string + number
        BufferedReader br;
        StringTokenizer st;
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastScanner(String file)  throws IOException{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static void helper(int[][] dp,char[] ch,int l,int r){
        if(l > r) return;
        if(l == r){
            return;
        }
        if(r - l == 2){
            if(ch[l] != ch[r]){
                dp[l][r] = ((ch[l+1] == ch[l])?0:1) + ((ch[r-1] == ch[r])?0:1);
            }
            return;
        }
        if(r - l == 1){
            if(ch[l] != ch[r]){
                dp[l][r] = 1;
            }
            return;
        }
        if(ch[l] == ch[r]){
            if(dp[l+1][r-1] == 0)helper(dp,ch,l+1,r-1);
            dp[l][r] = dp[l+1][r-1];
        }else{
            if(dp[l+1][r] == 0)helper(dp,ch,l+1,r);
            if(dp[l][r-1] == 0)helper(dp,ch,l,r-1);
            dp[l][r] = 1+Math.min(dp[l+1][r],dp[l][r-1]);
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner s = new FastScanner();
//        FastScanner s = new FastScanner("INPUT");
        OutputStream outputStream = System.out;
//        OutputStream outputStream = new FileOutputStream("OUTPUT");
        PrintWriter o = new PrintWriter(outputStream);
        // start
        int t = s.nextInt();
        while (t-->0){
            char[] ch = (s.next()).toCharArray();
            int[][] dp = new int[ch.length][ch.length];
            helper(dp,ch,0,ch.length-1);
//            for(int[] i : dp) System.out.println(Arrays.toString(i));
            o.println(dp[0][ch.length-1]);

        }
        // end
        o.close();
    }
}
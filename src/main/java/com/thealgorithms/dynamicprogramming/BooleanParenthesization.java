package com.sandeepprabhakula.dp;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BooleanParenthesization {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int i = 0;
        int j = s.length() - 1;
        int[][][] dp = new int[s.length() + 1][s.length() + 1][2];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
//        bw.write(bpMem(s, i, j, true) + "\n");
        bw.write(dpTable(s, i, j, 1, dp)+"\n");
        bw.flush();
    }

    private static int bpRec(String s, int i, int j, boolean flag) {
        // base condition
        if (i > j) return 0;
        if (i == j) {
            if (flag) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0;
        }
        // k-Loop
        int ans = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            int lt = bpRec(s, i, k - 1, true);
            int lf = bpRec(s, i, k - 1, false);
            int rt = bpRec(s, k + 1, j, true);
            int rf = bpRec(s, k + 1, j, false);
            char sym = s.charAt(k);
            switch (sym) {
                case '|':
                    if (flag) {
                        ans += lt * rt + lt * rf + rt * lf;
                    } else ans += lf * rf;
                    break;
                case '&':
                    if (flag) {
                        ans += lt * rt;
                    } else ans += lf * rt + lt * rf + lf * rf;
                    break;
                case '^':
                    if (flag) {
                        ans += lt * rf + rt * lf;
                    } else ans += lf * rf + lt * rt;
            }
        }
        return ans;
    }

    static Map<String, Integer> map = new HashMap<>();

    public static int bpMem(String s, int i, int j, boolean flag) {
// base condition
        if (i > j) return 0;
        if (i == j) {
            if (flag) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0;
        }
        String temp = i + " " + j + " " + flag;
        if (map.containsKey(temp)) return map.get(temp);
        // k-Loop
        int ans = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            int lt = bpMem(s, i, k - 1, true);
            int lf = bpMem(s, i, k - 1, false);
            int rt = bpMem(s, k + 1, j, true);
            int rf = bpMem(s, k + 1, j, false);
            char sym = s.charAt(k);
            switch (sym) {
                case '|':
                    if (flag) {
                        ans += lt * rt + lt * rf + rt * lf;
                    } else ans += lf * rf;
                    break;
                case '&':
                    if (flag) {
                        ans += lt * rt;
                    } else ans += lf * rt + lt * rf + lf * rf;
                    break;
                case '^':
                    if (flag) {
                        ans += lt * rf + rt * lf;
                    } else ans += lf * rf + lt * rt;
            }
        }
        map.put(temp, ans);
        return ans;
    }

    public static int dpTable(String s, int i, int j, int flag, int[][][] dp) {
        if (i > j) return 0;
        if (i == 0) {
            if (flag == 1) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0;
        }
        if (dp[i][j][flag] != -1) return dp[i][j][flag];
        int ans = 0;
        int lf = 0, lt = 0, rf = 0, rt = 0;
        for (int k = i + 1; k <= j - 1; k++) {
            if (dp[i][k - 1][1] != -1) lt = dp[i][k - 1][1];
            else dp[i][k - 1][1] = dpTable(s, i, k - 1, 1, dp);
            if (dp[i][k - 1][0] != -1) lf = dp[i][k - 1][0];
            else dp[i][k - 1][0] = dpTable(s, i, k - 1, 0, dp);
            if (dp[k + 1][j][1] != -1) rt = dp[k + 1][j][1];
            else dp[k + 1][j][1] = dpTable(s, i, k - 1, 1, dp);
            if (dp[k + 1][j][0] != -1) rf = dp[k + 1][j][0];
            else dp[k + 1][j][0] = dpTable(s, k + 1, j, 0, dp);

            char sym = s.charAt(k);
            switch (sym) {
                case '|':
                    if (flag == 1) {
                        ans += lt * rt + lt * rf + rt * lf;
                    } else ans += lf * rf;
                    break;
                case '&':
                    if (flag == 1) {
                        ans += lt * rt;
                    } else ans += lf * rt + lt * rf + lf * rf;
                    break;
                case '^':
                    if (flag == 1) {
                        ans += lt * rf + rt * lf;
                    } else ans += lf * rf + lt * rt;
            }
            dp[i][j][flag] = ans;
        }
        return ans;
    }
}

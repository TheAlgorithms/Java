package Others;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Mayank K Jha
 */

public class Dijkshtra {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // n = Number of nodes or vertices
        int n = in.nextInt();
        // m = Number of Edges
        int m = in.nextInt();

        // Adjacency Matrix
        long[][] w = new long[n + 1][n + 1];

        // Initializing Matrix with Certain Maximum Value for path b/w any two vertices
        for (long[] row : w) {
            Arrays.fill(row, 1000000L);
        }

		/* From above,we Have assumed that,initially path b/w any two Pair of vertices is Infinite such that Infinite = 1000000l
		    For simplicity , We can also take path Value = Long.MAX_VALUE , but i have taken Max Value = 1000000l */

        // Taking Input as Edge Location b/w a pair of vertices
        for (int i = 0; i < m; i++) {
            int x = in.nextInt(), y = in.nextInt();
            long cmp = in.nextLong();

            // Comparing previous edge value with current value - Cycle Case
            if (w[x][y] > cmp) {
                w[x][y] = cmp;
                w[y][x] = cmp;
            }
        }

        // Implementing Dijkshtra's Algorithm
        Stack<Integer> t = new Stack<>();
        int src = in.nextInt();

        for (int i = 1; i <= n; i++) {
            if (i != src) {
                t.push(i);
            }
        }

        Stack<Integer> p = new Stack<>();
        p.push(src);
        w[src][src] = 0;

        while (!t.isEmpty()) {
            int min = 989997979;
            int loc = -1;

            for (int i = 0; i < t.size(); i++) {
                w[src][t.elementAt(i)] = Math.min(w[src][t.elementAt(i)], w[src][p.peek()] + w[p.peek()][t.elementAt(i)]);
                if (w[src][t.elementAt(i)] <= min) {
                    min = (int) w[src][t.elementAt(i)];
                    loc = i;
                }
            }
            p.push(t.elementAt(loc));
            t.removeElementAt(loc);
        }

        // Printing shortest path from the given source src
        for (int i = 1; i <= n; i++) {
            if (i != src && w[src][i] != 1000000L) {
                System.out.print(w[src][i] + " ");
            }
            // Printing -1 if there is no path b/w given pair of edges
            else if (i != src) {
                System.out.print("-1" + " ");
            }
        }
    }
}
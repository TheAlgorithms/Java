package com.thealgorithms.dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class FordFulkerson {

    static final int INF = 987654321;
    // edges
    static int V;
    static int[][] capacity, flow;

    public static void main(String[] args) {
        System.out.println("V : 6");
        V = 6;
        capacity = new int[V][V];

        capacity[0][1] = 12;
        capacity[0][3] = 13;
        capacity[1][2] = 10;
        capacity[2][3] = 13;
        capacity[2][4] = 3;
        capacity[2][5] = 15;
        capacity[3][2] = 7;
        capacity[3][4] = 15;
        capacity[4][5] = 17;

        System.out.println("Max capacity in networkFlow : " + networkFlow(0, 5));
    }

    private static int networkFlow(int source, int sink) {
        flow = new int[V][V];
        int totalFlow = 0;
        while (true) {
            Vector<Integer> parent = new Vector<>(V);
            for (int i = 0; i < V; i++) {
                parent.add(-1);
            }
            Queue<Integer> q = new LinkedList<>();
            parent.set(source, source);
            q.add(source);
            while (!q.isEmpty() && parent.get(sink) == -1) {
                int here = q.peek();
                q.poll();
                for (int there = 0; there < V; ++there) {
                    if (capacity[here][there] - flow[here][there] > 0 && parent.get(there) == -1) {
                        q.add(there);
                        parent.set(there, here);
                    }
                }
            }
            if (parent.get(sink) == -1) {
                break;
            }

            int amount = INF;
            String printer = "path : ";
            StringBuilder sb = new StringBuilder();
            for (int p = sink; p != source; p = parent.get(p)) {
                amount = Math.min(capacity[parent.get(p)][p] - flow[parent.get(p)][p], amount);
                sb.append(p + "-");
            }
            sb.append(source);
            for (int p = sink; p != source; p = parent.get(p)) {
                flow[parent.get(p)][p] += amount;
                flow[p][parent.get(p)] -= amount;
            }
            totalFlow += amount;
            printer += sb.reverse() + " / max flow : " + totalFlow;
            System.out.println(printer);
        }

        return totalFlow;
    }
}

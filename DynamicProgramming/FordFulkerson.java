package DynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

public class FordFulkerson {
    final static int INF = 987654321;
    // edges
    static @Positive int V;
    static int[][] capacity, flow;

    public static void main(String[] args) {
        System.out.println("V : 6");
        V = 6;
        capacity = (int @MinLen(6) [] @MinLen(6) [])new int[V][V];

        ((int @MinLen(6) [] @MinLen(6) []) capacity)[0][1] = 12;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[0][3] = 13;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[1][2] = 10;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[2][3] = 13;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[2][4] = 3;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[2][5] = 15;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[3][2] = 7;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[3][4] = 15;
        ((int @MinLen(6) [] @MinLen(6) []) capacity)[4][5] = 17;

        System.out.println("Max capacity in networkFlow : " + networkFlow(0, 5));
    }
    @SuppressWarnings({"cast.unsafe"})
    private static int networkFlow(@NonNegative int source,@NonNegative int sink) {
        flow = (int @MinLen(6) [] @MinLen(6) [])new int[V][V];
        int totalFlow = 0;
        while (true) {
            Vector<Integer> parent = new Vector<>(V);
            for (int i = 0; i < V; i++)
                parent.add(-1);
            Queue<@NonNegative Integer> q = new LinkedList<>();
            parent.set(source, source);
            q.add(source);
            while (!q.isEmpty() && parent.get(sink) == -1) {
                int here = q.peek();
                q.poll();
                for (int there = 0; there < V; ++there)
                    if (capacity[(@IndexFor("DynamicProgramming.FordFulkerson.capacity") int )here][there] - flow[(@IndexFor("DynamicProgramming.FordFulkerson.flow") int )here][there] > 0 && parent.get(there) == -1) {
                        q.add(there);
                        parent.set(there, here);
                    }
            }
            if (parent.get(sink) == -1)
                break;

            int amount = INF;
            String printer = "path : ";
            StringBuilder sb = new StringBuilder();
            for (@NonNegative int p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
                amount = Math.min(capacity[parent.get((@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.capacity") int)p)][p] - flow[(@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.flow") int)parent.get(p)][p], amount);
                sb.append(p + "-");
            }
            sb.append(source);
            for (@NonNegative int p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
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

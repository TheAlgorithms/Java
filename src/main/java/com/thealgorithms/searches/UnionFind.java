package com.thealgorithms.searches;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionFind {

    private final int[] p;
    private final int[] r;

    public UnionFind(int n) {
        p = new int[n];
        r = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    public int find(int i) {
        int parent = p[i];

        if (i == parent) {
            return i;
        }

        final int result = find(parent);
        p[i] = result;

        return result;
    }

    public void union(int x, int y) {
        int r0 = find(x);
        int r1 = find(y);

        if (r1 == r0) {
            return;
        }

        if (r[r0] > r[r1]) {
            p[r1] = r0;
        } else if (r[r1] > r[r0]) {
            p[r0] = r1;
        } else {
            p[r1] = r0;
            r[r0]++;
        }
    }

    public int count() {
        List<Integer> parents = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            if (!parents.contains(find(i))) {
                parents.add(find(i));
            }
        }
        return parents.size();
    }

    public String toString() {
        return "p " + Arrays.toString(p) + " r " + Arrays.toString(r) + "\n";
    }

    // Tests
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        System.out.println("init /w 5 (should print 'p [0, 1, 2, 3, 4] r [0, 0, 0, 0, 0]'):");
        System.out.println(uf);

        uf.union(1, 2);
        System.out.println("union 1 2 (should print 'p [0, 1, 1, 3, 4] r [0, 1, 0, 0, 0]'):");
        System.out.println(uf);

        uf.union(3, 4);
        System.out.println("union 3 4 (should print 'p [0, 1, 1, 3, 3] r [0, 1, 0, 1, 0]'):");
        System.out.println(uf);

        uf.find(4);
        System.out.println("find 4 (should print 'p [0, 1, 1, 3, 3] r [0, 1, 0, 1, 0]'):");
        System.out.println(uf);

        System.out.println("count (should print '3'):");
        System.out.println(uf.count());
    }
}

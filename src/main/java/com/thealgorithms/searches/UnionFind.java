package com.thealgorithms.searches;

import java.util.*;

public class UnionFind {

    //private int[] p;
    //private int[] r;
    private int[] unf;

    public UnionFind(int n) {
        unf = new int[n+1];
        for(int i = 1; i <= n; i++) unf[i] = i;
    }

    public int find(int i) {
       if(i == unf[i]) return i;
       else return unf[i] = find(unf[i]);
    }

    public void union(int x, int y) {
        int r0 = find(x);
        int r1 = find(y);
        if(r0 != r1) unf[r0] = r1;
    }

    public int count() {
        List parents = new ArrayList();
        for (int i = 1; i < unf.length; i++) {
            if (!parents.contains(find(i))) {
                parents.add(find(i));
            }
        }
        return parents.size();
    }

    public String toString() {
        return "unf " + Arrays.toString(unf) + "\n";
    }

    // Tests
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        System.out.println("init /w 5 (should print 'unf [0, 1, 2, 3, 4, 5]'):");
        System.out.println(uf);

        uf.union(1, 2);
        System.out.println("union 1 2 (should print 'unf [0, 2, 2, 3, 4, 5]'):");
        System.out.println(uf);

        uf.union(3, 4);
        System.out.println("union 3 4 (should print 'unf [0, 2, 2, 4, 4, 5]'):");
        System.out.println(uf);

        uf.find(4);
        System.out.println("find 4 (should print 'p [0, 2, 2, 4, 4. 5]'):");
        System.out.println(uf);

        System.out.println("count (should print '3'):");
        System.out.println(uf.count());
    }
}

package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Merges account records using Disjoint Set Union (Union-Find) on shared emails.
 *
 * <p>Each account is expected to be a list where the first element is the user name and the
 * remaining elements are email addresses. Accounts that share at least one email are merged into a
 * single record.
 */
public final class AccountMerge {
    private AccountMerge() {
        // Utility class; do not instantiate.
    }

    /**
     * Merges accounts that share one or more email addresses.
     *
     * <p>The returned list is sorted by account owner name, then by the first email address when
     * multiple merged groups have the same owner name. Within each merged account, emails are
     * returned in lexicographic order.
     *
     * @param accounts a list of accounts where each entry contains a user name followed by emails
     * @return merged accounts, or an empty list when {@code accounts} is null or empty
     */
    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            return List.of();
        }

        UnionFind dsu = new UnionFind(accounts.size());
        Map<String, Integer> emailToAccount = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer previous = emailToAccount.putIfAbsent(email, i);
                if (previous != null) {
                    dsu.union(i, previous);
                }
            }
        }

        Map<Integer, List<String>> rootToEmails = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {
            int root = dsu.find(entry.getValue());
            rootToEmails.computeIfAbsent(root, ignored -> new ArrayList<>()).add(entry.getKey());
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).size() <= 1) {
                int root = dsu.find(i);
                rootToEmails.computeIfAbsent(root, ignored -> new ArrayList<>());
            }
        }

        List<List<String>> merged = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : rootToEmails.entrySet()) {
            int root = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);

            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(accounts.get(root).getFirst());
            mergedAccount.addAll(emails);
            merged.add(mergedAccount);
        }

        merged.sort((a, b) -> {
            int cmp = a.getFirst().compareTo(b.getFirst());
            if (cmp != 0) {
                return cmp;
            }
            if (a.size() == 1 || b.size() == 1) {
                return Integer.compare(a.size(), b.size());
            }
            return a.get(1).compareTo(b.get(1));
        });
        return merged;
    }

    /**
     * Lightweight union-find structure with path compression and union by rank.
     */
    private static final class UnionFind {
        private final int[] parent;
        private final int[] rank;

        private UnionFind(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

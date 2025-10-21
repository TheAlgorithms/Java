package com.thealgorithms.graph;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class DisjointSetTest {

    @Test
    public void testAccountsMerge() {
        // Input data setup
        List<List<String>> list = new ArrayList<>();
        list.add(new ArrayList<>(List.of("abc", "abc@mail.com", "abx@mail.com")));
        list.add(new ArrayList<>(List.of("abc", "abc@mail.com", "aby@mail.com")));
        list.add(new ArrayList<>(List.of("Mary", "mary@mail.com")));
        list.add(new ArrayList<>(List.of("John", "johnnybravo@mail.com")));
        list.add(new ArrayList<>(List.of("John", "johnnybravo@mail.com", "john@mail.com")));

        // Create instance of your Solution/DisjointSet class
        DisjointSet disjointSet = new DisjointSet(list.size());  // or DisjointSet if that’s where accountsMerge() lives

        // Execute the method
        List<List<String>> result = disjointSet.accountsMerge(list);

        // Expected output (order of accounts may vary)
        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("abc", "abc@mail.com", "abx@mail.com", "aby@mail.com"));
        expected.add(Arrays.asList("Mary", "mary@mail.com"));
        expected.add(Arrays.asList("John", "john@mail.com", "johnnybravo@mail.com"));

        // Sort both results for deterministic comparison
        sortAccounts(result);
        sortAccounts(expected);

        // Verify results
        assertEquals(expected, result);
    }

    // Helper method to sort emails and accounts for deterministic comparison
    private static void sortAccounts(List<List<String>> accounts) {
        for (List<String> acc : accounts) {
            // Sort all emails (leave name first)
            List<String> emails = acc.subList(1, acc.size());
            Collections.sort(emails);
        }
        // Sort accounts lexicographically by name
        accounts.sort(Comparator.comparing(a -> a.get(0)));
    }
}



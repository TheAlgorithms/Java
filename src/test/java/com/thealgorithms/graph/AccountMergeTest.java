package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class AccountMergeTest {

    @Test
    void testMergeAccountsWithSharedEmails() {
        List<List<String>> accounts = List.of(List.of("abc", "abc@mail.com", "abx@mail.com"), List.of("abc", "abc@mail.com", "aby@mail.com"), List.of("Mary", "mary@mail.com"), List.of("John", "johnnybravo@mail.com"));

        List<List<String>> merged = AccountMerge.mergeAccounts(accounts);

        List<List<String>> expected = List.of(List.of("John", "johnnybravo@mail.com"), List.of("Mary", "mary@mail.com"), List.of("abc", "abc@mail.com", "abx@mail.com", "aby@mail.com"));

        assertEquals(expected, merged);
    }

    @Test
    void testAccountsWithSameNameButNoSharedEmailStaySeparate() {
        List<List<String>> accounts = List.of(List.of("Alex", "alex1@mail.com"), List.of("Alex", "alex2@mail.com"));

        List<List<String>> merged = AccountMerge.mergeAccounts(accounts);
        List<List<String>> expected = List.of(List.of("Alex", "alex1@mail.com"), List.of("Alex", "alex2@mail.com"));

        assertEquals(expected, merged);
    }

    @Test
    void testEmptyInput() {
        assertEquals(List.of(), AccountMerge.mergeAccounts(List.of()));
    }

    @Test
    void testNullInput() {
        assertEquals(List.of(), AccountMerge.mergeAccounts(null));
    }

    @Test
    void testTransitiveMergeAndDuplicateEmails() {
        List<List<String>> accounts = List.of(List.of("A", "a1@mail.com", "a2@mail.com"), List.of("A", "a2@mail.com", "a3@mail.com"), List.of("A", "a3@mail.com", "a4@mail.com", "a4@mail.com"));

        List<List<String>> merged = AccountMerge.mergeAccounts(accounts);

        List<List<String>> expected = List.of(List.of("A", "a1@mail.com", "a2@mail.com", "a3@mail.com", "a4@mail.com"));

        assertEquals(expected, merged);
    }

    @Test
    void testAccountsWithNoEmailsArePreserved() {
        List<List<String>> accounts = List.of(List.of("Alex"), List.of("Alex", "alex1@mail.com"), List.of("Bob"));

        List<List<String>> merged = AccountMerge.mergeAccounts(accounts);
        List<List<String>> expected = List.of(List.of("Alex"), List.of("Alex", "alex1@mail.com"), List.of("Bob"));

        assertEquals(expected, merged);
    }
}

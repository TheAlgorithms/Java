package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;


final class KnuthMorrisPratt {
	private KnuthMorrisPratt() {
	}

	// Compute the longest proper prefix which is also suffix (LPS) array
	public static int[] computeLps(final String pattern) {
		final int n = pattern.length();
		final int[] lps = new int[n];
		int len = 0; // length of the previous longest prefix suffix
		lps[0] = 0;
		for (int i = 1; i < n; ) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	// Return list of start indices where pattern occurs in text
	public static List<Integer> search(final String text, final String pattern) {
		final List<Integer> occurrences = new ArrayList<>();
		if (pattern == null || pattern.isEmpty() || text == null) {
			return occurrences;
		}

		final int[] lps = computeLps(pattern);
		int i = 0; // index for text
		int j = 0; // index for pattern
		final int n = text.length();
		final int m = pattern.length();
		while (i < n) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
				if (j == m) {
					occurrences.add(i - j);
					j = lps[j - 1];
				}
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		return occurrences;
	}

	// example runner
	public static void main(String[] args) {
		final String text = "AAAAABAAABA";
		final String pattern = "AAAA";
		final List<Integer> idx = search(text, pattern);
		for (int pos : idx) {
			System.out.println("Pattern starts: " + pos);
		}
	}
}

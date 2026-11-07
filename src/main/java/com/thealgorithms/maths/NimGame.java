package com.thealgorithms.maths;

/**
 * Nim Game: Two players take turns removing 1 to 3 stones from a pile.
 * The player who removes the last stone wins. Returns true if the first
 * player can guarantee a win given n stones.
 *
 * <p>Key insight: if n is a multiple of 4, the second player always wins
 * because whatever the first player takes (1-3), the second player can
 * always take enough to leave another multiple of 4.
 *
 * <p>Time complexity: O(1), Space complexity: O(1)
 *
 * @see <a href="https://leetcode.com/problems/nim-game/">LeetCode #292</a>
 */
public final class NimGame {
    private NimGame() {
    }

    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}

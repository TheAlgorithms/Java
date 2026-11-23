package com.thealgorithms.puzzlesandgames;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConnectFourTest {

    private static ConnectFour play(int... columns) {
        ConnectFour game = new ConnectFour();
        for (int col : columns) {
            game.dropDisc(col);
        }
        return game;
    }

    @Test
    void newGameStartsEmptyWithRedToMove() {
        ConnectFour game = new ConnectFour();
        assertEquals(ConnectFour.RED, game.getCurrentPlayer());
        assertEquals(ConnectFour.EMPTY, game.getWinner());
        assertFalse(game.isGameOver());
    }

    @Test
    void discFallsToBottomAndStacks() {
        ConnectFour game = new ConnectFour();
        assertEquals(ConnectFour.ROWS - 1, game.dropDisc(3));
        assertEquals(ConnectFour.ROWS - 2, game.dropDisc(3));
        assertEquals(ConnectFour.YELLOW, game.getBoard()[ConnectFour.ROWS - 2][3]);
    }

    @Test
    void verticalWin() {
        ConnectFour game = play(0, 1, 0, 1, 0, 1, 0);
        assertEquals(ConnectFour.RED, game.getWinner());
        assertTrue(game.isGameOver());
    }

    @Test
    void horizontalWin() {
        ConnectFour game = play(0, 0, 1, 1, 2, 2, 3);
        assertEquals(ConnectFour.RED, game.getWinner());
    }

    @Test
    void diagonalWinBothDirections() {
        assertEquals(ConnectFour.RED, play(0, 1, 1, 2, 3, 2, 2, 3, 6, 3, 3).getWinner());
        assertEquals(ConnectFour.RED, play(6, 5, 5, 4, 3, 4, 4, 3, 0, 3, 3).getWinner());
    }

    @Test
    void yellowCanWin() {
        ConnectFour game = play(0, 1, 0, 1, 0, 1, 6, 1);
        assertEquals(ConnectFour.YELLOW, game.getWinner());
    }

    @Test
    void fullColumnThrows() {
        ConnectFour game = play(0, 0, 0, 0, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(0));
    }

    @Test
    void invalidColumnThrows() {
        ConnectFour game = new ConnectFour();
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(-1));
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(ConnectFour.COLS));
    }

    @Test
    void moveAfterGameOverThrows() {
        ConnectFour game = play(0, 1, 0, 1, 0, 1, 0);
        assertThrows(IllegalStateException.class, () -> game.dropDisc(2));
    }

    @Test
    void getBoardReturnsCopy() {
        ConnectFour game = new ConnectFour();
        game.getBoard()[0][0] = ConnectFour.RED;
        assertEquals(ConnectFour.EMPTY, game.getBoard()[0][0]);
    }
}

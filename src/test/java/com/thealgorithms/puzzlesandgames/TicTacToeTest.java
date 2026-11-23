package com.thealgorithms.puzzlesandgames;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TicTacToeTest {

    private static TicTacToe play(int[]... moves) {
        TicTacToe game = new TicTacToe();
        for (int[] move : moves) {
            game.placeMark(move[0], move[1]);
        }
        return game;
    }

    @Test
    void newGameStartsEmptyWithXToMove() {
        TicTacToe game = new TicTacToe();
        assertEquals(TicTacToe.X, game.getCurrentPlayer());
        assertEquals(TicTacToe.EMPTY, game.getWinner());
        assertFalse(game.isGameOver());
    }

    @Test
    void rowWin() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {1, 0}, new int[] {0, 1}, new int[] {1, 1}, new int[] {0, 2});
        assertEquals(TicTacToe.X, game.getWinner());
        assertTrue(game.isGameOver());
    }

    @Test
    void columnWin() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {0, 1}, new int[] {1, 0}, new int[] {1, 1}, new int[] {2, 0});
        assertEquals(TicTacToe.X, game.getWinner());
    }

    @Test
    void diagonalWins() {
        TicTacToe main = play(new int[] {0, 0}, new int[] {0, 1}, new int[] {1, 1}, new int[] {0, 2}, new int[] {2, 2});
        assertEquals(TicTacToe.X, main.getWinner());

        TicTacToe anti = play(new int[] {0, 2}, new int[] {0, 0}, new int[] {1, 1}, new int[] {0, 1}, new int[] {2, 0});
        assertEquals(TicTacToe.X, anti.getWinner());
    }

    @Test
    void oCanWin() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {1, 0}, new int[] {0, 1}, new int[] {1, 1}, new int[] {2, 2}, new int[] {1, 2});
        assertEquals(TicTacToe.O, game.getWinner());
    }

    @Test
    void draw() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {0, 1}, new int[] {0, 2}, new int[] {1, 1}, new int[] {1, 0}, new int[] {1, 2}, new int[] {2, 1}, new int[] {2, 0}, new int[] {2, 2});
        assertTrue(game.isDraw());
        assertTrue(game.isGameOver());
        assertEquals(TicTacToe.EMPTY, game.getWinner());
    }

    @Test
    void invalidMovesThrow() {
        TicTacToe game = play(new int[] {1, 1});
        assertThrows(IllegalArgumentException.class, () -> game.placeMark(1, 1));
        assertThrows(IllegalArgumentException.class, () -> game.placeMark(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> game.placeMark(0, 3));
    }

    @Test
    void moveAfterGameOverThrows() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {1, 0}, new int[] {0, 1}, new int[] {1, 1}, new int[] {0, 2});
        assertThrows(IllegalStateException.class, () -> game.placeMark(2, 2));
        assertThrows(IllegalStateException.class, game::bestMove);
    }

    @Test
    void getBoardReturnsCopy() {
        TicTacToe game = new TicTacToe();
        game.getBoard()[0][0] = TicTacToe.X;
        assertEquals(TicTacToe.EMPTY, game.getBoard()[0][0]);
    }

    @Test
    void bestMoveTakesWinningMove() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {1, 0}, new int[] {0, 1}, new int[] {1, 1});
        Assertions.assertArrayEquals(new int[] {0, 2}, game.bestMove());
    }

    @Test
    void bestMoveBlocksOpponent() {
        TicTacToe game = play(new int[] {0, 0}, new int[] {1, 1}, new int[] {0, 1});
        Assertions.assertArrayEquals(new int[] {0, 2}, game.bestMove());
    }

    @Test
    void perfectPlayAlwaysDraws() {
        TicTacToe game = new TicTacToe();
        while (!game.isGameOver()) {
            int[] move = game.bestMove();
            game.placeMark(move[0], move[1]);
        }
        assertTrue(game.isDraw());
    }
}

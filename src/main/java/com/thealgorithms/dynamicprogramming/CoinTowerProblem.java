package com.thealgorithms.dynamicprogramming;

/**
 * CoinTowerProblem class to determine the winner of the Coin Tower Game.
 *
 * A and B are playing a game where they remove 1, X, or Y coins alternately from a tower of N coins.
 * The person to make the last move wins the game.
 *
 * @SarthakChaudhary46
 */
public class CoinTowerProblem {

    // Driver Program
    public static void main(String[] args) {
        int N = 5;
        int X = 3;
        int Y = 4;

        char result = coinTowerWinner(N, X, Y);
        System.out.println("The winner of the Coin Tower Game is Player " + result + ".");
    }

    /**
     * Determines the winner of the Coin Tower Game.
     *
     * @param N Number of coins in the tower.
     * @param X Number of coins player can remove in one step.
     * @param Y Number of coins player can remove in one step.
     * @return 'A' if Player A wins, 'B' if Player B wins.
     */
    public static char coinTowerWinner(int N, int X, int Y) {
        char[] winner = new char[N + 1];

        // Base cases: B wins with 1, X, or Y coins
        winner[1] = 'B';
        winner[X] = 'B';
        winner[Y] = 'B';

        // Fill the winner array for the remaining positions
        for (int i = 2; i <= N; i++) {
            // If the player can remove 1 coin and the opponent loses in the next turn
            if (winner[i - 1] == 'A') {
                winner[i] = 'B';
            }
            // If the player can remove X coins and the opponent loses in the next turn
            else if (i - X >= 1 && winner[i - X] == 'A') {
                winner[i] = 'B';
            }
            // If the player can remove Y coins and the opponent loses in the next turn
            else if (i - Y >= 1 && winner[i - Y] == 'A') {
                winner[i] = 'B';
            }
            // If the player cannot make a winning move, the opponent wins
            else {
                winner[i] = 'A';
            }
        }

        // Return the winner of the game
        return winner[N];
    }
}

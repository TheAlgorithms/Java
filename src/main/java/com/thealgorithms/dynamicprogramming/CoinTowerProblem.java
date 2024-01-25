//Code By @SarthakChaudhary46

import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoinTowerProblem {

    // Driver Program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of coins in the tower (N): ");
        int N = scanner.nextInt();

        System.out.print("Enter the number of coins player can remove in one step (X): ");
        int X = scanner.nextInt();

        System.out.print("Enter the number of coins player can remove in one step (Y): ");
        int Y = scanner.nextInt();

        char result = coinTowerWinner(N, X, Y);
        System.out.println("The winner of the Coin Tower Game is Player " + result + ".");

        // Run JUnit tests
        org.junit.runner.JUnitCore.main("CoinTowerProblemTest");
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
        if (X <= N) winner[X] = 'B';
        if (Y <= N) winner[Y] = 'B';

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

class CoinTowerProblemTest {

    @Test
    public void testCoinTowerWinner() {
        assertEquals('A', CoinTowerProblem.coinTowerWinner(5, 3, 4));
        assertEquals('A', CoinTowerProblem.coinTowerWinner(10, 2, 4));
        assertEquals('B', CoinTowerProblem.coinTowerWinner(15, 3, 5));
        assertEquals('B', CoinTowerProblem.coinTowerWinner(20, 2, 5));
        assertEquals('A', CoinTowerProblem.coinTowerWinner(7, 2, 3));
    }

    @Test
    public void testCoinTowerWinnerEdgeCases() {
        assertEquals('B', CoinTowerProblem.coinTowerWinner(1, 2, 3));
        assertEquals('B', CoinTowerProblem.coinTowerWinner(2, 2, 3));
        assertEquals('B', CoinTowerProblem.coinTowerWinner(3, 2, 3));
        assertEquals('A', CoinTowerProblem.coinTowerWinner(4, 2, 3));
        assertEquals('A', CoinTowerProblem.coinTowerWinner(5, 2, 3));
    }
}

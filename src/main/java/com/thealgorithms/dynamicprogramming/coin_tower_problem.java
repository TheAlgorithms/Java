// Coin Tower Problem
// Question:
// A and B are playing a new game today. They form a tower of N coins and make a move in alternate turns. 
// B plays first. In one step, the player can remove either 1, X, or Y coins from the tower. 
// The person to make the last move wins the game. Can you find out who wins the game?

public class coin_tower_problems {
    static final int A = 1;
    static final int B = 0;

    static int solve(int n, int x, int y, int player) {
        int enemy = 1 - player;
        int winner = enemy;
        if (n >= 1)
            winner = solve(n - 1, x, y, enemy);
        if (winner != player && n >= x)
            winner = solve(n - x, x, y, enemy);
        if (winner != player && n >= y)
            winner = solve(n - y, x, y, enemy);
        return winner;
    }
}    
    
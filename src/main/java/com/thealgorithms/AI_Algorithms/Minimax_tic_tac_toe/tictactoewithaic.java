import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class tictactoewithaic {

     char[] board = new char[9];  

    static char AI_PLAYER = 'O';

    static char HUMAN_PLAYER = 'X';

    static char EMPTY_CELL = ' ';

    public tictactoewithaic() {
        for (int i = 0; i < 9; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.print("| ");
            for (int j = i; j < i + 3; j++) {
                System.out.print(board[j] + " | ");
            }
            System.out.println();
        }
    }


    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }

    public boolean hasPlayerWon(char player) {

        for (int i = 0; i < 3; i++) {

            if (board[i] == player && board[i + 3] == player && board[i + 6] == player) {
                return true;
            }
            if (board[i * 3] == player && board[i * 3 + 1] == player && board[i * 3 + 2] == player) {
                return true;
            }
        }

        if (board[0] == player && board[4] == player && board[8] == player) {
            return true;
        }
        if (board[2] == player && board[4] == player && board[6] == player) {
            return true;
        }

        return false;
    }


    public List<Integer> getAvailableMoves() {

        List<Integer> moves = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (board[i] == EMPTY_CELL) {
                moves.add(i);
            }
        }

        return moves;
    }


    private int minimax(char player) {

        if (hasPlayerWon(AI_PLAYER)) {
            return 1;
        }
        if (hasPlayerWon(HUMAN_PLAYER)) {
            return -1;
        }
        if (isBoardFull()) {
            return 0;
        }

        List<Integer> availableMoves = getAvailableMoves();

        if (player == AI_PLAYER) {
            int bestScore = Integer.MIN_VALUE;
            for (int move : availableMoves) {
                board[move] = AI_PLAYER;
                int score = minimax(HUMAN_PLAYER);
                board[move] = EMPTY_CELL;
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;

        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int move : availableMoves) {
                board[move] = HUMAN_PLAYER;
                int score = minimax(AI_PLAYER);
                board[move] = EMPTY_CELL;
                bestScore = Math.min(bestScore, score);
            }

            return bestScore;
        }
    }
    
    public void makeAIMove() {
        List<Integer> availableMoves = getAvailableMoves();

        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int move : availableMoves) {
            board[move] = AI_PLAYER;
            int score = minimax(HUMAN_PLAYER);
            board[move] = EMPTY_CELL;
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        board[bestMove] = AI_PLAYER;
    }


    public static void main(String[] args) {

        tictactoewithaic game = new tictactoewithaic();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic-Tac-Toe Game with AI (Minimax Algorithm)");

        System.out.println("Enter a number (0-8) to make a move on the board, where:");

        game.printBoard();

        System.out.println("0 | 1 | 2");
        System.out.println("---------");
        System.out.println("3 | 4 | 5");
        System.out.println("---------");
        System.out.println("6 | 7 | 8");
        System.out.println();

        while (!game.isBoardFull() && !game.hasPlayerWon(HUMAN_PLAYER) && !game.hasPlayerWon(AI_PLAYER)) {
            System.out.print("Your turn, enter a number: ");
            int move = scanner.nextInt();

            if (move >= 0 && move < 9 && game.board[move] == EMPTY_CELL) {
                game.board[move] = HUMAN_PLAYER;
                game.printBoard();
            } else {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (game.hasPlayerWon(HUMAN_PLAYER)) {
                game.printBoard();
                System.out.println("Congratulations! You win!");
                break;
            }

            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            System.out.print("AI Player's Turn: ");
            game.makeAIMove();
            game.printBoard();

            if (game.hasPlayerWon(AI_PLAYER)) {
                game.printBoard();
                System.out.println("AI Player Wins!!");
                break;
            }
        }

    }
}
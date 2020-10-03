import java.util.*;

class Nqueen {
    static int N = 4;
    static int[] ld = new int[30];

    static int[] rd = new int[30];

    static int[] cl = new int[30];

    static void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf(" %d ", board[i][j]);
            System.out.printf("\n");
        }
    }

    static int solveNQUtil(int board[][], int col) {
        if (col >= N)
            return 1;
        for (int i = 0; i < N; i++) {
            if ((ld[i - col + N - 1] != 1 && rd[i + col] != 1) && cl[i] != 1) {

                board[i][col] = 1;
                ld[i - col + N - 1] = rd[i + col] = cl[i] = 1;
                if (solveNQUtil(board, col + 1)==1)
                    return 1;
                board[i][col] = 0;
                ld[i - col + N - 1] = rd[i + col] = cl[i] = 0;
            }
        }
        return 0;
    }

    static int solveNQ() {
        int board[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

        if (solveNQUtil(board, 0) == 0) {
            System.out.printf("Solution does not exist");
            return 0;
        }
        printSolution(board);
        return 1;
    }

    public static void main(String[] args) {
        int x = solveNQ();
    }
}
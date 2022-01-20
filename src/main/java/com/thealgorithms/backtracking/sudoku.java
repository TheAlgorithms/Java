package sudoku;

import java.util.*;

public class Main {

    static final Scanner s = new Scanner(System.in);

    static final int[][] sudokuBoard = new int[9][9];
    static final ArrayList<HashMap<Integer, Boolean>> rows = new ArrayList<>();
    static final ArrayList<HashMap<Integer, Boolean>> cols = new ArrayList<>();
    static final ArrayList<HashMap<Integer, Boolean>> boxes = new ArrayList<>();

    public static boolean solve(int i, int j) {

        if (i == 9) {
            return true;
        }

        if (i < 0 || i >= 9 || j < 0 || j >= 9) {
            return false;
        }

        int nextj = j + 1;
        int nexti = i + nextj / 9;
        nextj %= 9;

        if (sudokuBoard[i][j] != 0) {

            return solve(nexti, nextj);
        }

        int boxNo = i / 3;
        boxNo = 3 * boxNo + j / 3;

        for (int x = 1; x <= 9; ++x) {

            if (rows.get(i).get(x) || cols.get(j).get(x) || Boolean.TRUE.equals(boxes.get(boxNo).get(x))) {
                continue;
            }

            sudokuBoard[i][j] = x;

            rows.get(i).put(x, true);
            cols.get(j).put(x, true);
            boxes.get(boxNo).put(x, true);

            if (solve(nexti, nextj)) {
                return true;
            } else {
                sudokuBoard[i][j] = 0;

                rows.get(i).put(x, false);
                cols.get(j).put(x, false);
                boxes.get(boxNo).put(x, false);
            }

        }

        sudokuBoard[i][j] = 0;
        return false;
    }

    public static void takeInput() {
        for (int i = 0; i < 9; ++i) {
            rows.add(new HashMap<>());
            cols.add(new HashMap<>());
            boxes.add(new HashMap<>());
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 1; j <= 9; ++j) {
                rows.get(i).put(j, false);
                cols.get(i).put(j, false);
                boxes.get(i).put(j, false);
            }
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {

                sudokuBoard[i][j] = s.nextInt();

                if (sudokuBoard[i][j] == 0) {
                    continue;
                }

                int boxNo = i / 3;
                boxNo = 3 * boxNo + j / 3;

                rows.get(i).put(sudokuBoard[i][j], true);
                cols.get(j).put(sudokuBoard[i][j], true);
                boxes.get(boxNo).put(sudokuBoard[i][j], true);
            }
        }

    }

    public static void print() {

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(sudokuBoard[i][j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("---------------------");
            }
        }
    }

    public static void main(String[] args) {

        takeInput();

        if (solve(0, 0)) {
            System.out.println("\nThe solution of given sudoku is : \n");
            print();
        } else {
            System.out.println("The given sudoku is invalid / doesn't have a definite solution!!");
        }
    }

}

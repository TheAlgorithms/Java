import java.io.*;

class Sudoku
{
    static int N = 9;

    static int row[] = new int[N], col[] = new int[N], box[] = new int[N];
    static Boolean seted = false;

    static int getBox(int i, int j)
    {
        return i / 3 * 3 + j / 3;
    }


    static Boolean isSafe(int i, int j, int number)
    {
        return ((row[i] >> number) & 1) == 0 && ((col[j] >> number) & 1) == 0 && ((box[getBox(i, j)] >> number) & 1) == 0;
    }

    static void setInitialValues(int grid[][])
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid.length; j++)
            {
                row[i] |= 1 << grid[i][j];
                col[j] |= 1 << grid[i][j];
                box[getBox(i, j)] |= 1 << grid[i][j];
            }
        }
    }


    static Boolean SolveSudoku(int grid[][], int i, int j)
    {
        // Set the initial values
        if (!seted)
        {
            seted = true;
            setInitialValues(grid);
        }

        if (i == N - 1 && j == N)
            return true;
        if (j == N)
        {
            j = 0;
            i++;
        }

        if (grid[i][j] > 0)
            return SolveSudoku(grid, i, j + 1);

        for (int nr = 1; nr <= N; nr++)
        {
            if (isSafe(i, j, nr))
            {
                grid[i][j] = nr;
                row[i] |= 1 << nr;
                col[j] |= 1 << nr;
                box[getBox(i, j)] |= 1 << nr;

                if (SolveSudoku(grid, i, j + 1))
                    return true;

                row[i] &= ~(1 << nr);
                col[j] &= ~(1 << nr);
                box[getBox(i, j)] &= ~(1 << nr);
            }

            grid[i][j] = 0;
        }

        return false;
    }

    static void print(int grid[][])
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                System.out.printf("%d ", grid[i][j]);
            }
            System.out.println();
        }
    }


public
    static void main(String args[])
    {
        // 0 means empty cells
        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        if (SolveSudoku(grid, 0, 0))
            print(grid);
        else
            System.out.println("No solution exists");
    }
}
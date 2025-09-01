package sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a Sudoku board with support for iteration using the Iterator pattern.
 */
public class SudokuBoard implements Iterable<SudokuBoard.Cell> {

    private final int[][] board;
    private final int size;

    public SudokuBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    /** Represents a single cell in the Sudoku board */
    public static class Cell {
        private final int row;
        private final int col;
        private final int value;

        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getValue() {
            return value;
        }
    }

    /** Iterator implementation for Sudoku board cells */
    private class CellIterator implements Iterator<Cell> {
        private int row = 0;
        private int col = 0;

        @Override
        public boolean hasNext() {
            return row < size && col < size;
        }

        @Override
        public Cell next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Cell cell = new Cell(row, col, board[row][col]);
            col++;
            if (col == size) {
                col = 0;
                row++;
            }
            return cell;
        }
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator();
    }
}

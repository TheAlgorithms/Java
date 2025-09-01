package com.thealgorithms.puzzlesandgames;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a Sudoku board with validation and iteration support.
 * The board is always a square grid of size n x n,
 * where n must be a perfect square (e.g., 4, 9, 16).
 */
 class SudokuBoard implements Iterable<SudokuBoard.Cell> {

    private final int size;
    private final int boxSize;
    private final int[][] board;

    /**
     * Constructs a SudokuBoard of the given size.
     *
     * @param size the dimension of the Sudoku board (must be a perfect square)
     * @throws IllegalArgumentException if size is not a positive perfect square
     */
    public SudokuBoard(int size) {
        if (size <= 0 || Math.sqrt(size) % 1 != 0) {
            throw new IllegalArgumentException("Size must be a perfect square (e.g., 4, 9, 16)");
        }
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.board = new int[size][size];
    }

    /**
     * Returns the size of the board.
     *
     * @return the board size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the box (subgrid) size.
     *
     * @return the size of a subgrid
     */
    public int getBoxSize() {
        return boxSize;
    }

    /**
     * Gets the value at the given cell.
     *
     * @param row the row index
     * @param col the column index
     * @return the value at the specified cell
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public int get(int row, int col) {
        validateCell(row, col);
        return board[row][col];
    }

    /**
     * Sets the value at the given cell.
     *
     * @param row   the row index
     * @param col   the column index
     * @param value the value to set (0 means empty)
     * @throws IndexOutOfBoundsException if indices are invalid
     * @throws IllegalArgumentException  if value is out of range
     */
    public void set(int row, int col, int value) {
        validateCell(row, col);
        if (value < 0 || value > size) {
            throw new IllegalArgumentException("Value must be between 0 and " + size);
        }
        board[row][col] = value;
    }

    /**
     * Checks whether placing a value at the given cell is valid
     * according to Sudoku rules.
     *
     * @param row   the row index
     * @param col   the column index
     * @param value the value to check
     * @return true if placement is valid, false otherwise
     */
    public boolean isValid(int row, int col, int value) {
        validateCell(row, col);
        if (value <= 0 || value > size) {
            return false;
        }

        // check row
        for (int c = 0; c < size; c++) {
            if (board[row][c] == value) {
                return false;
            }
        }

        // check column
        for (int r = 0; r < size; r++) {
            if (board[r][col] == value) {
                return false;
            }
        }

        // check box
        int boxRowStart = (row / boxSize) * boxSize;
        int boxColStart = (col / boxSize) * boxSize;

        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (board[boxRowStart + r][boxColStart + c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Ensures that the given cell indices are valid.
     *
     * @param row the row index
     * @param col the column index
     * @throws IndexOutOfBoundsException if indices are outside the board
     */
    private void validateCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
    }

    /**
     * Represents a single cell on the Sudoku board.
     */
    public class Cell {
        private final int row;
        private final int col;

        /**
         * Constructs a Cell with the given row and column.
         *
         * @param row the row index
         * @param col the column index
         */
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Returns the row index of this cell.
         *
         * @return the row index
         */
        public int getRow() {
            return row;
        }

        /**
         * Returns the column index of this cell.
         *
         * @return the column index
         */
        public int getCol() {
            return col;
        }

        /**
         * Gets the current value stored in this cell.
         *
         * @return the cell value
         */
        public int getValue() {
            return board[row][col];
        }

        /**
         * Sets a value in this cell.
         *
         * @param value the value to set
         */
        public void setValue(int value) {
            SudokuBoard.this.set(row, col, value);
        }
    }

    /**
     * Iterator for traversing all cells in the board.
     */
    private class CellIterator implements Iterator<Cell> {
        private int row = 0;
        private int col = 0;

        @Override
        public boolean hasNext() {
            return row < size;
        }

        @Override
        public Cell next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Cell cell = new Cell(row, col);
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

package com.thealgorithms.puzzlesandgames;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SudokuBoard implements Iterable<SudokuBoard.Cell> {

    private final int size;
    private final int boxSize;
    private final int[][] board;

    public SudokuBoard(int size) {
        if (size <= 0 || Math.sqrt(size) % 1 != 0) {
            throw new IllegalArgumentException("Size must be a perfect square (e.g., 4, 9, 16)");
        }
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.board = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public int getBoxSize() {
        return boxSize;
    }

    public int get(int row, int col) {
        validateCell(row, col);
        return board[row][col];
    }

    public void set(int row, int col, int value) {
        validateCell(row, col);
        if (value < 0 || value > size) {
            throw new IllegalArgumentException("Value must be between 0 and " + size);
        }
        board[row][col] = value;
    }

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

    private void validateCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
    }

    public class Cell {
        private final int row;
        private final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getValue() {
            return board[row][col];
        }

        public void setValue(int value) {
            SudokuBoard.this.set(row, col, value);
        }
    }

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

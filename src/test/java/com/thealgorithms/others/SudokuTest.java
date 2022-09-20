package com.thealgorithms.others;

import org.junit.jupiter.api.*;

public class SudokuTest {

    int[][] board;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }



        @BeforeEach
        void setUp(){
            System.out.println("@BeforeEach - executes before each test method in this class");
            board = new int[][]{
                    {3, 0, 6, 5, 0, 8, 4, 0, 0},
                    {5, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 7, 0, 0, 0, 0, 3, 1},
                    {0, 0, 3, 0, 1, 0, 0, 8, 0},
                    {9, 0, 0, 8, 6, 3, 0, 0, 5},
                    {0, 5, 0, 0, 9, 0, 6, 0, 0},
                    {1, 3, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 4},
                    {0, 0, 5, 2, 0, 6, 3, 0, 0}
            };

        }


        @DisplayName("1 - Check if a value is in the row yet. Return False")
        @Test
        void PositionInARowIsInValid(){
            System.out.println("Test 1 - ran");
            final boolean isSafe = Sudoku.isSafe(board, 0,0, 3 );
            Assertions.assertFalse(isSafe);
        }


        @DisplayName("2 - Check if a value is in the column yet. Return False")
        @Test
        void PositionInAColumnIsInValid(){
            System.out.println("Test 2 - ran");
            final boolean isSafe = Sudoku.isSafe(board, 0,1, 7 );
            Assertions.assertFalse(isSafe);

        }


        @DisplayName("3 - Check if a value is valid in a square. Return False")
        @Test
        void ValueIsInValidInASquare(){
            System.out.println("Test 3 - ran");
            final boolean isSafe = Sudoku.isSafe(board, 3,0, 9 );
            Assertions.assertFalse(isSafe);

        }

        @DisplayName("4 - Value is value. Return True")
        @Test
        void ValueIsValid(){
            System.out.println("Test 4 - ran");
            final boolean isSafe = Sudoku.isSafe(board, 3,0, 4 );
            Assertions.assertTrue(isSafe);

        }


    @DisplayName("5 - Value is value. Return True")
    @Test
    void Finished(){
        System.out.println("Test 4 - ran");
        final boolean isSafe = Sudoku.solveSudoku(board, 9 );
        Assertions.assertTrue(isSafe);
    }



            @AfterEach
    void tearDown() {
        System.out.println("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        System.out.println("@AfterAll - executed after all test methods.");
    }


}

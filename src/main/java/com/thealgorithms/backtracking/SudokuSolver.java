class SudokuSolver {
/*
Question Description :

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example ; 

Input: board = {
{'5','3','.','.','7','.','.','.','.'},
{'6','.','.','1','9','5','.','.','.'},
{'.','9','8','.','.','.','.','6','.'},
{'8','.','.','.','6','.','.','.','3'},
{'4','.','.','8','.','3','.','.','1'},
{'7','.','.','.','2','.','.','.','6'},
{'.','6','.','.','.','.','2','8','.'},
{'.','.','.','4','1','9','.','.','5'},
{'.','.','.','.','8','.','.','7','9'}
}

Output: {
{'5','3','4','6','7','8','9','1','2'},
{'6','7','2','1','9','5','3','4','8'},
{'1','9','8','3','4','2','5','6','7'},
{'8','5','9','7','6','1','4','2','3'},
{'4','2','6','8','5','3','7','9','1'},
{'7','1','3','9','2','4','8','5','6'},
{'9','6','1','5','3','7','2','8','4'},
{'2','8','7','4','1','9','6','3','5'},
{'3','4','5','2','8','6','1','7','9'},
}

*/
   public static void main(String[] args){
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        print(board);   
   }

  public static void print(char[][] board){
    int n = board.length;
    int m = board[0].length;

    System.out.println('{');
    for(int i=0;i<n;i++){
      System.out.print('{');
      for(int j=0;j<m-1;j++){
        System.out.print("'"+board[i][j]+"',");
      }
      System.out.print("'"+board[i][m-1]+"'}");
      if(i!=n-1){
        System.out.print(",");
      }
      System.out.println();
    }
    System.out.print('}');
  }

  public static void solveSudoku(char[][] board) {
      
      solveBoard(board);

  }
  public static boolean solveBoard(char[][] board)
  {
      for(int i=0;i<9;i++)
      {
          for(int j=0;j<9;j++)
          {
              if(board[i][j]=='.')
              {
                  for(int k=1;k<=9;k++)
                  {
                      if(isSafe(board,i,j,(char)(k+'0')))
                      {
                          board[i][j]=(char)(k+'0');
                          if(solveBoard(board)){
                              return true;
                          }
                          else
                              board[i][j]='.';
                      }
                  }
                  return false;
              }
          }
      }
      return true; 
  }
  
  public static boolean isRowSafe(char[][] board,int row,char num)
  {
      for(int i =0;i<9;i++)
      {
          if(num==board[row][i])
              return false;
      }
      return true;
  }
  
  public static boolean isColumnSafe(char[][] board,int column,char num)
  {
      for(int i =0;i<9 ;i++)
      {
          if(num==board[i][column])
              return false;
      }
      return true;
  }
  
  public static boolean isBoxSafe(char[][] board,int row,int column,char num)
  {
      int cRow = row - row%3;
      int cColumn = column - column%3;
      
      for(int i=cRow;i<cRow+3;i++)
      {
          for(int j=cColumn;j<cColumn+3;j++)
          {
              if(num==board[i][j])
                  return false;
          }
      }
      return true;
  }
  
  public static boolean isSafe(char[][] board,int row,int column,char num)
  {
      return isRowSafe(board,row,num) && isColumnSafe(board,column,num) && isBoxSafe(board,row,column,num);
  }
  
}

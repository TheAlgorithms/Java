import java.util.Scanner;

public class matrixTranspose {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, j, row, column;
        System.out.println("Enter the number of rows in the 2D matrix:");
        row = sc.nextInt();
        System.out.println("Enter the number of columns in the 2D matrix:");
        column = sc.nextInt();
        int[][] arr = new int[row][column];
        System.out.println("Enter the elements");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println("The matrix is:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.println("The Transpose of the given matrix is:");
        for (i = 0; i < column; i++) {
            for (j = 0; j < row; j++) {
                System.out.print(arr[j][i] + "\t");
            }
            System.out.print("\n");
        }
    }
}

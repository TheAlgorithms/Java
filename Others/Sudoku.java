import java.util.Scanner;
class Sudoku {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int[][] arr = new int[9][9];
		for(int i = 0; i < arr.length; ++i){
			for(int j = 0; j < arr[0].length; ++j){
				arr[i][j] = scn.nextInt();
			}
		}
		
		int[] rows = new int[9];
		int[] cols = new int[9];
		int[][] sms = new int[3][3];
		
		for(int row = 0; row < arr.length; ++row){
			for(int col = 0; col < arr[0].length; ++col){
				rows[row] |= (1 << arr[row][col]);
				cols[col] |= (1 << arr[row][col]);
				sms[row / 3][col / 3] |= (1 << arr[row][col]);
			}
		}
		
		sudoku(arr, 1, rows, cols, sms);
	}
	
	private static void sudoku(int[][] arr, int cellno, int[] rows, int[] cols, int[][] sms){
		if(cellno > arr.length * arr.length){
			for(int i = 0; i < arr.length; ++i){
				for(int j = 0; j < arr[0].length; ++j){
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		int row = (cellno - 1) / arr.length;
		int col = (cellno - 1) % arr.length;
		
		if(arr[row][col] != 0){
			sudoku(arr, cellno + 1, rows, cols, sms);
		} else {
			for(int choice = 1; choice <= 9; ++choice){
				if(isNumberAvailable(arr, rows, cols, sms, choice, row, col)){
					reserveANumber(arr, rows, cols, sms, choice, row, col);
					sudoku(arr, cellno + 1, rows, cols, sms);
					releaseTheNumber(arr, rows, cols, sms, choice, row, col);
				}
			}
		}
	}
	
	private static boolean isNumberAvailable(int[][] arr,int[] rows, int[] cols, int[][] sms, int num, int row, int col){
		int mask = 1 << num;
		
		if((rows[row] & mask) != 0){
			return false;
		} else if((cols[col] & mask) != 0){
			return false;
		} else if((sms[row / 3][col / 3] & mask) != 0){
			return false;
		} else {
			return true;
		}
	}
	
	private static void reserveANumber(int[][] arr,int[] rows, int[] cols, int[][] sms, int num, int row, int col){
		int mask = 1 << num;
		
		arr[row][col] = num;
		rows[row] = rows[row] | mask;
		cols[col] = cols[col] | mask;
		sms[row / 3][col / 3] = sms[row / 3][col / 3] | mask;
	}
	
	private static void releaseTheNumber(int[][] arr,int[] rows, int[] cols, int[][] sms, int num, int row, int col){
		int mask = ~(1 << num);
			
		arr[row][col] = 0;
		rows[row] = rows[row] & mask;
		cols[col] = cols[col] & mask;
		sms[row / 3][col / 3] = sms[row / 3][col / 3] & mask;
	}
}

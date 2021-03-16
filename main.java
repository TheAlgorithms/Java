import java.util.*;
public class main{
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("number of rows");
		int a = sc.nextInt();
		System.out.println("number of columns");
		int b = sc.nextInt();
		int array1[][] = new int[a][b];
		int array2[][] = new int[a][b];
		int ans[][] = new int[a][b];
		System.out.println("give values for first matrix");
		for(int i = 0;i<array1.length;i++) {
			for(int j = 0;j<array2.length;j++) {
				array1[i][j] = sc.nextInt();
			}
		}
		System.out.println("give values for second matrix");
		for(int i = 0;i<array2.length;i++) {
			for(int j = 0;j<array2.length;j++) {
				array2[i][j] = sc.nextInt();
			}
		}
		for(int i = 0;i<array1.length;i++) {
			for(int j = 0;j<array2.length;j++) {
				ans[i][j] = array1[i][j] + array2[i][j];
			}
		}
		System.out.println();
		for(int i = 0;i<array1.length;i++) {
			for(int j = 0;j<array2.length;j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
}

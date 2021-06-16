//Take N (number of rows), print the following pattern (for N = 6)
//1
//1 1
//1 2 1
//1 3 3 1
//1 4 6 4 1
//1 5 10 10 5 1


import java.util.Scanner;
public class pascal_triangle {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int rows = 1;
		int nst = 1;
		while (rows <= n) {
			int cst = 1;
			while (cst <= nst) {
				System.out.print(pascal(rows - 1, cst - 1) + " ");
				cst++;
			}
			System.out.println();
			nst++;
			rows++;
		} 
	}

	public static int pascal(int n, int r) {
		int mul = 1;
		r=Math.min(r, n-r);
		for (int i = 0; i < r; i++) {
			mul = mul * (n - i) / (i + 1);
		}
		return mul;
	}
}

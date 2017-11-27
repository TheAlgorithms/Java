import java.util.Arrays;

public class MatrixChainMultiplication {
	private int[][] a;
	private int[][] s;
	private int n;

	public MatrixChainMultiplication(int[] p)

	{
		// The number of matrices in the chain
		n = p.length - 1;
		// used so we don't use 0 in our multiplication
		a = new int[n + 1][n + 1];
		// used so we don't use 0 in our sub-multiplications
		s = new int[n + 1][n + 1];
		// the algorithm
		matrixChainOrder(p);
	}

	private void matrixChainOrder(int[] p) {
		
		// output
		int result = 0;
		// deals with the empty sub problems
		for (int i = 1; i <= n; i++)
			a[i][i] = 0;
		// deals with chains of link 1
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				a[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int q = a[i][k] + a[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < a[i][j]) {
						a[i][j] = q;
						s[i][j] = k;
					}
				}
				if(result<a[i][j]){
					result=a[i][j];
				}
			}
		}
		System.out.println();
		System.out.println("Output : "+ result);
	}

	private String printOptimalParens(int i, int j) {
		if (i == j)
			return "A[" + i + "]";
		else
			return "(" + printOptimalParens(i, s[i][j]) + printOptimalParens(s[i][j] + 1, j) + ")";
	}

	public String toString() {
		return printOptimalParens(1, n);
	}

	public static void main(String[] args) {
		int[] p = { 40, 20, 30, 10, 30 };
		System.out.print("Input : ");
		for(int i=0;i<p.length;i++){
			System.out.print(p[i]+" ");
		}
		MatrixChainMultiplication mult = new MatrixChainMultiplication(p);
	}
}
package Maths;

public class GaussianElimination {
	private double[][] matrix;
	private int row;
	private int col;
	private double[] solution;
	
	public GaussianElimination(double[][] matrix) {
		this.matrix = matrix;
		this.row = matrix.length;
		this.col = matrix[0].length;
		this.solution = new double[row];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < this.col; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
	}
	
	public double[] solve() {
		this.nomalize();
		this.pivoting();
		this.forwardElminate();
		this.backwardSubstitue();
		return this.solution;
	}
	
	public void nomalize() {
		for(int i = 0; i < this.row; i++) {
			double max = 0;
			for(int j = 0; j < this.col-1; j++) {
				if(Math.abs(max) < Math.abs(this.matrix[i][j])) {
					max = this.matrix[i][j];
				}
			}
			
			for(int j = 0; j < this.col; j++) {
				this.matrix[i][j] /= max;
			}
		}
	}
	
	public void pivoting() {
		int pivot_row;
		double big;
		for(int i = 0; i < this.row; i++) {
			pivot_row = i;
			big = Math.abs(this.matrix[i][i]);
			for(int j = pivot_row+1; j < row; j++) {
				double temp = Math.abs(this.matrix[j][i]);
				if(temp>big) {
					pivot_row = j;
				}
			}
			
			if(pivot_row != i) {
				for(int k = 0; k < this.col; k++) {
					double temp = this.matrix[i][k];
					this.matrix[i][k] = this.matrix[pivot_row][k];
					this.matrix[pivot_row][k] = temp;
				}
			}
		}
	}
	
	public void forwardElminate() {
		for(int i = this.row-1; i>0; i--) {
			double pivot = this.matrix[i][i];
			for(int j = i - 1; j >= 0; j--) {
				double m = this.matrix[j][i] / pivot;
				for(int k = i; k < this.col; k++) {
					this.matrix[j][k] -= m*this.matrix[i][k];
				}
			}
		}
	}
	
	public void backwardSubstitue() {
		for(int i = this.row-1; i >= 0; i--) {
			double num = this.matrix[i][this.col-1];
			for(int j = this.col-2; j>i; j--) {
				num -= this.matrix[i][j] * this.solution[j];
			}
			this.solution[i] = num/this.matrix[i][i];
		}
	}
	
	public static void main(String[] args) {
		GaussianElimination gauss = new GaussianElimination(new double[][] { { 2, 1, 5, 1, 5 }, { 1, 1, -3, -4, -1}, {3, 6, -2, -1, 8}, {2, 2, 2, -3, 2}});
		gauss.solve();
		for(int i = 0; i < gauss.solution.length; i++) {
			System.out.println("x"+(i+1)+"="+gauss.solution[i]);
		}
	}
}

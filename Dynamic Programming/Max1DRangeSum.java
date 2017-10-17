package DynamicProgramming;

public class Max1DRangeSum {
	
	public static int getMax1DRangeSum(int n, int A[]){
		int current_sum = 0, ans = 0;
	    for (int i = 0; i < n; i++)
		 {
			if (current_sum + A[i] >= 0) {
			    current_sum += A[i];
			    ans = Math.max(ans, current_sum);
			  }
			else {
				current_sum = 0;
			}
	 	}
	    return ans;
	}

	public static void main(String[] args) {

		int n = 9, A[] = { 4, -5, 4, -3, 4, 4, -4, 4, -5 };
	    System.out.println(getMax1DRangeSum(n, A));         // 9
	}

}

package dp1;

class catalanNumberDp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;		//nth catalan number
		System.out.println(calculateCatalan(n));
	}
	  public static int calculateCatalan(int n) {
	        int catalan[] = new int[n + 2]; 
	  
	        catalan[0] = 1; 
	        catalan[1] = 1; 
	  
	        for (int i = 2; i <= n; i++) { 
	            catalan[i] = 0; 
	            for (int j = 0; j < i; j++) { 
	                catalan[i] += catalan[j] * catalan[i - j - 1]; 
	            } 
	        } 
	        return catalan[n];    
	    }

}

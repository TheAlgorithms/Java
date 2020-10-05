public class PalindromePartition {

	public static int minCut(String s) {
        int n = s.length();
        if(n == 0){
            return 0;
        }
        int part[][] = new int[n][n];
        
        for(int g = 0;g < n;g++){
            for(int i = 0;i < n-g;i++){
                int j = i+g;
                if(g == 0){
                    part[i][j] = 0;
                }
                else if(s.charAt(i) == s.charAt(j) && part[i+1][j-1] == 0){
                    part[i][j] = part[i+1][j-1];
                }
                else{
                    int min = Integer.MAX_VALUE;
                    for(int k = i;k < j;k++){
                        min = (int)Math.min(min,part[i][k]+part[k+1][j]);
                    }
                    part[i][j] = min+1;
                }
            }
        }
        return part[0][n-1];
    }
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		String input = s.nextLine();
		
		System.out.println("Minimum cuts to make palindromes are " + minCut(input));
	}
}
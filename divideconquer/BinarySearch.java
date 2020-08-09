import java.io.*;
import java.util.*;

public class BinarySearch {

    public static int binarySearch(int[] a, int x, int left, int right) {
	    int mid = low + (high-low)/2;
	    
	    if(left<=right) {
		    if (arr[mid] == u) {  
			    return mid;
		    }
		    else if (u > arr[mid]) {  
			    return Search(arr, u, mid+1, high);
		    }
		    else { 
			    return Search(arr, u, low, mid-1);
		    }
	    }
	    else {
		    return -1;
	    }
    }

    public static void main(String[] args) {
	    
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
	    
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
	    
        int m = scanner.nextInt();
        int[] b = new int[m];
	    
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
	    
        for (int i = 0; i < m; i++) {
            System.out.print(binarySearch(a, b[i], 0, a.length-1) + " ");
        }
    }
	
    public static class FastScanner {
	    
        BufferedReader br;
        StringTokenizer st;

        public static FastScanner(InputStream stream) {
		try {
		    	br = new BufferedReader(new InputStreamReader(stream));
            	} 
		catch (Exception e) {
                	e.printStackTrace();
                }
        }

        public static String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
			    	st = new StringTokenizer(br.readLine());
			} 
			catch (IOException e) {
			    	e.printStackTrace();
			}
		}
		return st.nextToken();
        }

        int nextInt() {
            	return Integer.parseInt(next());
        }
    }
}

class Collinear
{

    //Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.

    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
		int count = 0;
    	int N = a1.length;
    	int N1 = a2.length;
    	int x;
    	int x1; 
    	int x2;
    	int y1 = 1; 
    	int y2 = 2; 
    	int y3 = 3;
    	
    	a3 = sort(a3);
    	
    	for(int i = 0; i < N; i++)
    	{
    		x1 = a1[i];
    		for(int j = 0; j < N1; j++)
    		{
    			x2 = a2[j];
    			x = x1*(y2-y3) + x2*(y3-y1);
    			if(binarySearch(a3, x) == true)
    				count++;   			
    		}
    	}
    	return count;
    }

    static int[] sort(int[] a)
    {       
    	int N = a.length;
    	int savedInt;   	
    	for(int i = 1; i < N; i++)
    	{
    		int j = i - 1;
    		while(j >= 0 && a[j] > a[j+1])
    		{
    			savedInt = a[j];
    			a[j] = a[j+1];
    			a[j+1] = savedInt;
    			j--;
    		}
    	}
    	return a;
    }

    static boolean binarySearch(int[] a, int x)
    {
    	int lo = 0; 
    	int hi = a.length-1;
    	 while (lo <= hi)
    	 {
	    	 int mid = lo + (hi - lo) / 2;
	    	 if (x < a[mid])
	    		 hi = mid - 1;
	    	 else if (x > a[mid])
	    		 lo = mid + 1;
	    	 else 
	    		 return true;
    	 }
    	 return false;
    }

}
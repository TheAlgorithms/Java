class lucas 
{ 
  
    // recursive function 
    public static int lucasNumber(int n) 
    { 
  
        // Base cases 
        if (n == 0) 
            return 2; 
        if (n == 1) 
            return 1; 
  
        // recurrence relation 
        return lucasNumber(n - 1) +  
               lucasNumber(n - 2); 
    } 
  
    // Driver Code 
    public static void main(String args[]) 
    { 
        int n = 9; 
        System.out.println(lucasNumber(9)); 
    } 
} 
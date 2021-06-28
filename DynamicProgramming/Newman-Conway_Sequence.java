

  
class GFG  
{ 
    
    public static void sequence(int n)  
    { 
        int f[] = new int[n + 1]; 
          
        f[0] = 0; 
        f[1] = 1; 
        f[2] = 1; 
          
        System.out.print( f[1] + " " + f[2] + " "); 
        for (int i = 3; i <= n; i++)  
        { 
            f[i] = f[f[i - 1]] + f[i - f[i - 1]];      
            System.out.print(f[i] + " "); 
        } 
    } 
      
    public static void main(String []args) 
    { 
        int n = 13 ; 
        sequence(n); 
    } 
  
} 
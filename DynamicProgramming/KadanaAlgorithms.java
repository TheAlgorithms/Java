static int maxSubArraySum(int a[],int size)  
{  
      
    int max_so_far = 0, max_ending_here = 0;  
  
    for (int i = 0; i < size; i++)  
    {  
        max_ending_here = max_ending_here + a[i]; 
        if (max_ending_here < 0)  
            max_ending_here = 0;  
          
        /* Do not compare for all 
           elements. Compare only  
           when max_ending_here > 0 */
        else if (max_so_far < max_ending_here)  
            max_so_far = max_ending_here;  
          
    }  
    return max_so_far;  
}  

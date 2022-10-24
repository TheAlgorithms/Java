public class CocktailSort { 
    void cocktailSort(int a[]) 
    { 
        boolean swapped = true; 
        int start = 0; 
        int end = a.length; 
  
        while (swapped == true) { 
       
             
	    swapped = false; 

            for (int i = start; i < end - 1; ++i) { 
                if (a[i] > a[i + 1]) { 
                    int temp = a[i]; 
                    a[i] = a[i + 1]; 
                    a[i + 1] = temp; 
                    swapped = true; 
                } 
            } 
  
           
            if (swapped == false) 
                break; 
   
            swapped = false; 
  
            end = end - 1; 
  

            for (int i = end - 1; i >= start; i--) { 
                if (a[i] > a[i + 1]) { 
                    int temp = a[i]; 
                    a[i] = a[i + 1]; 
                    a[i + 1] = temp; 
                    swapped = true; 
                } 
            }
            start = start + 1; 
        } 
    } 


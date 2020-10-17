import java.util.*; 
  
public class GFG { 
      
    /* Inheriting comparator */
    static class Box implements Comparable<Box>{ 
      
        // h --> height, w --> width, 
        // d --> depth 
        int h, w, d, area; 
          
        // for simplicity of solution, 
        // always keep w <= d 
  
        /*Constructor to initialise object*/
        public Box(int h, int w, int d) { 
            this.h = h; 
            this.w = w; 
            this.d = d; 
        } 
          
        /*Overriding compareTo function */
        @Override
        public int compareTo(Box o) { 
            return o.area-this.area; 
        } 
    } 
  
    /* Returns the height of the tallest 
    stack that can be formed with give  
    type of boxes */
    static int maxStackHeight( Box arr[], int n){ 
          
        Box[] rot = new Box[n*3]; 
          
        for(int i = 0;i < n;i++){ 
            Box box = arr[i]; 
              
            rot[3*i] = new Box(box.h, Math.max(box.w,box.d),  
                                    Math.min(box.w,box.d)); 
              
            rot[3*i + 1] = new Box(box.w, Math.max(box.h,box.d),  
                                       Math.min(box.h,box.d)); 
              
            rot[3*i + 2] = new Box(box.d, Math.max(box.w,box.h), 
                                       Math.min(box.w,box.h)); 
        } 
          
       /*calculating area of base*/
        for(int i = 0; i < rot.length; i++) 
            rot[i].area = rot[i].w * rot[i].d; 
          
        /* Sorting the Boxes on the bases  
        of Area in non Increasing order.*/
        Arrays.sort(rot); 
          
        int count = 3 * n; 
          
        /* Initialize msh values for all  
        indexes  
        msh[i] --> Maximum possible Stack Height 
                   with box i on top */
        int[]msh = new int[count]; 
        for (int i = 0; i < count; i++ ) 
            msh[i] = rot[i].h; 
          
        /* Computing optimized msh[]  
        values in bottom up manner */
        for(int i = 0; i < count; i++){ 
            msh[i] = 0; 
            Box box = rot[i]; 
            int val = 0; 
              
            for(int j = 0; j < i; j++){ 
                Box prevBox = rot[j]; 
                if(box.w < prevBox.w && box.d < prevBox.d){ 
                    val = Math.max(val, msh[j]); 
                } 
            } 
            msh[i] = val + box.h; 
        } 
          
        int max = -1; 
          
        /* Pick maximum of all msh values */
        for(int i = 0; i < count; i++){ 
            max = Math.max(max, msh[i]); 
        } 
          
        return max; 
    } 
      
    
    public static void main(String[] args) { 
          
        Box[] arr = new Box[4]; 
        arr[0] = new Box(4, 6, 7); 
        arr[1] = new Box(1, 2, 3); 
        arr[2] = new Box(4, 5, 6); 
        arr[3] = new Box(10, 12, 32); 
          
        System.out.println("The maximum possible "+ 
                           "height of stack is " +  
                           maxStackHeight(arr,4)); 
    } 
} 
  
 

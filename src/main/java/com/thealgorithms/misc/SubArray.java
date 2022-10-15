public class SubArray {
    static int findSplitPoint(int arr[], int n)
    {
      
    int leftSum = 0 ;
  
    for (int i = 0; i < n; i++)
    {
        leftSum += arr[i] ;
  
        int rightSum = 0 ;
          
        for (int j = i+1 ; j < n ; j++ )
            rightSum += arr[j] ;
  
        if (leftSum == rightSum)
            return i+1 ;
    }

    return -1;
    }   
  
   
    static void printTwoParts(int arr[], int n)
    {
      
        int splitPoint = findSplitPoint(arr, n);
      
        if (splitPoint == -1 || splitPoint == n )
        {
            System.out.println("Not Possible");
            return;
        }
          
        for (int i = 0; i < n; i++)
        {
            if(splitPoint == i)
               System.out.println();
                 
            System.out.print(arr[i] + " ");
              
        }
    }
        
    public static void main (String[] args) {
      
    int arr[] = {3,9,6,5};
    int n = arr.length;
    printTwoParts(arr, n);
}

}
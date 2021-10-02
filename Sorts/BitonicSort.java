public class BitonicSort{  
  static void exchange(int arr[], int i, int j, boolean d){  
      int temp;  
      if (d==(arr[i]>arr[j]){
        temp = arr[i];  
        arr[i] = arr[j];  
        arr[j] = temp;  
      }  
  }  
  static void merge(int arr[], int l, int c, boolean d){
    int k,i;  
    if (c>1){
      k = c/2;  
      for (i=l; i<l+k; i++){
        exchange(arr, i, i+k, d);
        merge(arr, l, k, d);  
        merge(arr, l+k, k, d);  
      }
    }  
  static void bitonicSort(int arr[],int l, int c, boolean d){
    int k;  
    if (c>1){  
        k = c/2;  
        bitonicSort(arr, l, k, true);  
        bitonicSort(arr, l+k, k, false);  
        merge(arr,l, c, d);  
    }
  } 
   
  static void sort(int arr[], int n, boolean order)  
  {  
      bitonicSort(arr,0, n, order);  
  }  
  public static void main(String[] args)  
  {  
      int arr[]= {1, 10, 2, 3, 1, 23, 45, 21};  
      int n = arr.length;  
      int i;  
      boolean order = true;     
      sort(arr, n, order);  
     
      System.out.println("Sorted array: \n");  
      for (i=0; i<n; i++){
          System.out.println(arr[i]);  
      }
  }
}
          
  }  

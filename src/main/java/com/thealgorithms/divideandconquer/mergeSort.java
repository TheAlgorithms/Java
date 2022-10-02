class mergeSort
{
    void merge(int arr[], int l, int m, int r)
    {
         // Your code here
         int[] a = new int[r-l+1];    
         int id1 = l;
         int id2 = m+1;
         int c = 0;
         while(id1<=m && id2<=r){            // merging two sorted arrays (first half and second half) into one 
             if(arr[id1]<=arr[id2]){
                 a[c++] = arr[id1++];
             }else{
                 a[c++] = arr[id2++];
             }
         }
         while(id1<=m){            // if any elements are remaining in first half of the array then insert those 
             a[c++] = arr[id1++];
         }
         while(id2<=r){           // if any elements are remaining in second half of the array then insert
             a[c++] = arr[id2++];
         }
         for(int i=0,j=l;i<a.length;i++,j++){    // modifying the original array
             arr[j] = a[i];
         }
    }
    void mergeSort(int arr[], int l, int r)
    {
        //code here
        if(l>=r){
            return;
        }
        int mid = l + (r-l)/2;     // taking the mid of the array size
        mergeSort(arr,l,mid);      // first half of the array
        mergeSort(arr,mid+1,r);    // second half of the array
        merge(arr,l,mid,r);        // merging both the halfs after computations
    }
}

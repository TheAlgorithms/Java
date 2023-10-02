// mergesort implimentation in java
// 1st we can understand the algorithm as this is a divide and conqure method
//1. 1st we dive the array into 2 equal halves 
//2 . further divide the given halves in another equal halves
//3 continue this process untill we get atomic value
//4 then we will murge but in a sorted order.
public class program{
  void merge(int arr1[],int st,int mid,int end){
    int i,j,k;
    int n1=mid-st+1;
    int n2=end-mid;
    // crete new array i.e left nd right array
  int left[]=new int[n1];
    int right[]=new int[n2];
    for(i=0;i<n1;i++)
      left[i]=arr1[st+i];
    for(j=0;j<n2;j++)
      right[j]=arr1[mid+1+j];
    i=0;
    j=0;
    k=st;
    while(i<n1 && j<n2){
      if(left[i]<=right[j]){
        arr1[k]=left[i];
        i++;
      }
      else{
        arr1[k]=right[j];
        j++;
      }
      k++;
    }
    while(i<n1){
      arr1[k]=left[i];
      i++;
      k++;
    }
  while(j<n2){
    arr1[k]=right[j];
    j++;
    k++;
  }
  }
  void mergesort(int arr1[],int st,int end){
    if(st<end){
      int mid=(st+end)/2;
      mergesort(arr1,st,mid);
      mergesort(arr1,mid+1,end);
      merge(arr1,st,mid,end);
    }
  }

  // for display the array
  void display(int arr1[],int n){
    int i;
    for(i=0;i<n;i++)
      System.out.print(arr1[i]+" ");
  }
  public static void main(String args[]){
    int arr1[]={10,30,45,32,2,65,12};
    int n=arr1.length;
    program obj=new program();
    System.out.println(" before sorting arrayelt are");
    obj.display(arr1,n);
    obj.mergesort(arr1,0,n-1);
    System.out.println("after sorting the array elt are");
    obj.display(arr1,n);
  }
}

// output:
/* before soting the array element are
  10,30,45,32,2,65,12
  after sorting the array element are
  2,10,12,30,32,45,65
  */
  
  

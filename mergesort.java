import java.util.Arrays;

public class mergesort {
    public static void main(String args[])
    {
        int[] arr={3,4,5,6,8,12};
        int[] ar=mergesort.mergeSort(arr);
        System.out.println(Arrays.toString(ar));
    }
    static int[] mergeSort(int[] arr)
    {
        if(arr.length==1)
        {
            return arr;
        }

            else{int mid=(arr.length)/2;
            int[] arr1 = mergeSort(Arrays.copyOfRange(arr,0,mid));
            int[] arr2 = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));
            return merge(arr1,arr2);}

    }
    static int[] merge(int[] first,int[] second)
    {
        int[] res=new int[first.length+second.length];
        int i=0,j=0,k=0;
        while(i<first.length  && j<second.length )
        {
            if(first[i]<second[j])
            {
             res[k]=first[i];

             i++;

            }
            else
            {
                res[k]=second[j];
                j++;
            }
            k++;
        }
        while(i<first.length)
        {
            res[k]=first[i];
            i++;
            k++;
        }
        while(j<second.length)
        {
            res[k]=second[j];


                    j++;
            k++;
        }
      return res;
    }
}

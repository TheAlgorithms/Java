import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java .util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
int arr[]=new int[1024];
int mid=(arr.length-1)/2;
        BinarySearch ob=new BinarySearch();
        int i=0;
        try {
            FileInputStream fstream=new FileInputStream("File 1.txt");
            DataInputStream in=new DataInputStream(fstream);
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String s;
            while((s=br.readLine())!=null)
            {
             arr[i]=Integer.parseInt(s);
                i++;
            }
            Arrays.sort(arr);
            Scanner sc=new Scanner(System.in);
            int value=arr[mid];
            
            long start=System.nanoTime();
            
            int p=ob.BinarySearch(arr,value);
            
            long end=System.nanoTime();
            System.out.println((end-start));    
            
            System.out.println("number found at : "+p);
        }
        catch(Exception e) {
            System.out.println("error:"+e.getMessage());
        }

}

int BinarySearch(int[] arr, int key) {
            int n=arr.length;
            int low=0;
            int high=n-1;               
    while(low<=high)
    {
        int mid=(low+high)/2;
        if(key==arr[mid])
            return mid;
        else if(key<arr[mid])
            high=mid-1;
        else if(key>arr[mid])
            low=mid+1;
            
    }
            
        return -1;
    }
}

import java.util.Arrays;
import java.util.Scanner;

class ArrayinPendulumArrangement {	
   public static int[] swap(int origPos, int newPos, int[] array){
      origPos = 1;
      newPos = 4;
      int temp = array[origPos];
      array[origPos] = array[newPos];      
      array[newPos] = temp;
      System.out.println(Arrays.toString(array));
      return array;
   }
   public static void main(String args[]){
      Scanner sc = new Scanner(System.in);   
      System.out.println("Enter the required size of the array (odd) :");
      int size = sc.nextInt();
      int [] myArray = new int[size];

      System.out.println("Enter elements of the array :");
      for(int i = 0; i< size; i++){
         myArray[i] = sc.nextInt(); 
      }
      //Sort the given array
      Arrays.sort(myArray);
      System.out.println("Contents of the Array"+Arrays.toString(myArray));
      
      int temp = myArray[0];
      int mid = (size-1)/2;
      int k =1;
      int[] result = new int[size];
      
      for(int i = 1; i<=mid; i++){
          result[mid+i] = myArray[k++];
          result[mid-i] = myArray[k++];
      }      
      result[mid] = temp;      
      System.out.println("Pendulum arrangement "+Arrays.toString(result));
      sc.close();
   }
}

/**
*
* @author Paul Parisot (https://github.com/tosirap)
*
*/

class OddEven{
  /**
  * This method implements the Generic Odd Even Sort
  * @param arr The array to be sorted
  * Sorts the array in increasing order
  **/

  public static <T extends Comparable<T>> void oE(T[] arr) {
    boolean sorted = false;
    while(!sorted){
      sorted = true;
      for(int i = 1; i < arr.length-1; i += 2){ //Odd
        if(arr[i].compareTo(arr[i+1]) > 0){
          T temp= arr[i];  //swap arr[i] and  arr[i+1]
          arr[i] = arr[i+1];
          arr[i+1] = temp;
          sorted = false;
        }
      }
      for(int i = 0; i < arr.length-1; i += 2){ //Even
        if(arr[i].compareTo(arr[i+1]) > 0){
          T temp = arr[i];  //swap arr[i] and  arr[i+1]
          arr[i] = arr[i+1];
          arr[i+1] = temp;
          sorted = false;
        }
      }
    }
  }

  public static void main(String[] args) {
    // Integer Input
    int[] arr1 = {4,23,6,78,1,54,231,9,12};
    Integer[] array = new Integer[arr1.length];
    for (int i = 0; i < arr1.length; i++){
      array[i] = arr1[i];
    }

    oE(array);

    // Output => 1 4 6 9 12  23  54  78  231
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i]+"\t");
    }
    System.out.println();

    // String Input
    String[] array1 = {"c", "a", "e", "b","d"};
    oE(array1);

    //Output =>  a b c d e
    for(int i = 0; i  <array1.length; i++){
      System.out.print(array1[i]+"\t");
    }
  }
}

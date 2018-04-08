/**
*
* @author Paul Parisot (https://github.com/tosirap)
*
*/

public class CombSort{
  /**
  * This method implements the Generic comb Sort
  * @param arr The array to be sorted
  * Sorts the array in increasing order
  **/

  public static <T extends Comparable<T>> void cS(T[] arr){
    int intervalle = arr.length; //initialisation of intervalle
    boolean echange = true; //initialisation at true;

    while(intervalle > 1 || echange){
      intervalle /= 1.3;
      if(intervalle < 1){
        intervalle = 1;
      }

      int i = 0;
      echange = false;

      while(i < (arr.length-intervalle)){
        if(arr[i].compareTo(arr[i+intervalle]) > 0){
          T temp = arr[i];
          arr[i] = arr[i+intervalle];
          arr[i+intervalle] = temp;
          echange = true;
        }
        i++;
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

    cS(array);

    // Output => 1 4 6 9 12  23  54  78  231
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i]+"\t");
    }
    System.out.println();

    // String Input
    String[] array1 = {"c", "a", "e", "b","d"};

    cS(array1);

    //Output =>  a b c d e
    for(int i = 0; i < array1.length; i++){
      System.out.print(array1[i]+"\t");
    }
  }
}

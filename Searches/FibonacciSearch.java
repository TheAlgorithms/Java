package Searches;

public class FibonacciSearch implements SearchAlgorithm {

  /**
   * Fibonacci Search algorithm implements 
   *
   * @param array List to be searched
   * @param value Key being searched for
   * @return Location of the key
   */

  @Override
  public <T extends Comparable<T>> int find(T[] array, T value) {
        int fibMMm2 = 0;
        int fibMMm1 = 1;
        int fibM = fibMMm2 + fibMMm1; 
        int length = array.length;
  
        while (fibM < length) 
        { 
            fibMMm2 = fibMMm1; 
            fibMMm1 = fibM; 
            fibM = fibMMm2 + fibMMm1; 
        } 
  
        int offset = -1; 

        while (fibM > 1) 
        {
            int i = Math.min(offset+fibMMm2, length-1); 

            if (value.compareTo(array[i]) < 0) 
            { 
                fibM = fibMMm1; 
                fibMMm1 = fibMMm2; 
                fibMMm2 = fibM - fibMMm1; 
                offset = i; 
            } 
  
            else if (value.compareTo(array[i]) > 0) 
            { 
                fibM = fibMMm2; 
                fibMMm1 = fibMMm1 - fibMMm2; 
                fibMMm2 = fibM - fibMMm1; 
            } 

            else return i; 
        } 
  
        if(fibMMm1 == 1 && array[offset+1].compareTo(value) == 0) 
            return offset+1; 
  
        return -1; 
  }

  public static void main(String[] args) {
    FibonacciSearch fibonacciSearch = new FibonacciSearch();
    Integer[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i = 0; i < array.length; i++) {
      assert fibonacciSearch.find(array, i) == i;
    }
    assert fibonacciSearch.find(array, -1) == -1;
    assert fibonacciSearch.find(array, 11) == -1;
  }
}

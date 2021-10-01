/*
This class is used to implement the Randomized quick sort algorithm in Java.
Written by - Amrit Raj
*/
class QuickSort{
  /*
  This function is used to partation the sort space..
  Arguments - int array of elements to sort, int begining index, int ending
  Index..
  Return - Nothing..
  */
  public void sort(int arr[], int beg, int end){
    //The end case to leave out of recurssion..
    if(beg >= end){
      return;
    }

    //Partation index ind..
    int ind = partation(arr, beg, end);
    //Calling the left sub - sort space..
    sort(arr, beg, ind-1);
    //Calling the right sub - sort space..
    sort(arr, ind+1, end);
  }
  /*
  This method is used to find the partation index based on the assumption
  that last element is the pivot..
  Arguments - int array for which the index is to be found, int beginning of
  the array, int end - Last index of the passed array..
  Returns - an int denoting the point of paration..
  */
  public int partation(int arr[], int beg, int end){
  //If the array has only one element..
  if(beg == end){
    return end;
  }
  else{
    //Finding the random index in range..
    int tempInd = rand(beg, end);
    //Swapping the element at the random index with the last element..
    swap(arr, tempInd, end);
    //Making the new last element as the pivot..
    int pivot = arr[end];
    //Current index to swap values..
    int ind = beg - 1;
    //Finding all the elements smaller than the pivot..
    for(int i = beg; i < end; i++){
      if(arr[i] < pivot){
        //Swapping the elements smaller than pivot to left of
        //pivot..
        ind++;
        swap(arr, ind, i);
      }
    }
    //Swapping the pivot to it's correct place
    ind++;

    swap(arr, ind, end);
    return ind;
    }
  }
  /*
  This method generates a random number in a given range
  Arguments - int beg, int end
  Returns - a random int in the range of end - beg + 1
  */
  public int rand(int beg, int end){
      int range = end - beg + 1;
      int show = (int)(Math.random() * range) + beg;
      return show;
    }
    /*
    This method is used as a utility function to swap two numbers in an array..
    Arguments - int array of elements, int index1, int index2
    Returns - Nothing, inplace swapping..
    */
    public void swap(int arr[], int a, int b){
      int temp = arr[a];
      arr[a] = arr[b];
      arr[b] = temp;
    }
}
/*
This class is used to check the QuickSort class.
*/
class Demo{
public static void main(String args[]){
  //A dummy array to be sorted..
  int sampleArray[] = {10, 7, 8, 9, 5, 1, 5};
  QuickSort sorter = new QuickSort();
  //Printing the original array..
  System.out.println("Array before sorting:- ");
  printer(sampleArray, sampleArray.length);
  //Sorting the array using quicksort..
  sorter.sort(sampleArray, 0, sampleArray.length-1);

  //Printing the sorted array..
  System.out.println("Array after sorting:- ");
  printer(sampleArray, sampleArray.length);
}
/*
A utility function to print the array linearly..
Arguments - An array to be printed, and an int with length of array
Return - Nothing..
*/
public static void printer(int arr[], int n){
    StringBuffer br = new StringBuffer();
    for(int i = 0; i < n; i++){
      br.append(arr[i]+" ");
    }
    System.out.println(br);
   }
}

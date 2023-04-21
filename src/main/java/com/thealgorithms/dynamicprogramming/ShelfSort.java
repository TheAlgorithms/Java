//I created a sorting algorithm that uses dimunsional array to sort an array.
//Worst case time complexity is O(n+d^2+g) || d == duplicated values and g == gaps.
//Best case time complexity is O(n)
//this code is belong to R-Alothaim
    //https://github.com/R-Alothaim/ShelfSort


    public class ShelfSort {
      // Merage elements into orginal Array
      static void merge(int arrr[][], int arr[], int lenArray[], int dup[]) {
          int index = 0, hitorgap = 0, indexof = 1, dupIndex = 0, lengthCounter = 0;
          boolean dupflag = false;
          // check if there is duplicated elements
          if (dup.length > 0) {
              dupflag = true;
          }
          // loop until all elements are sorted
          while (index < arr.length) {
              //check if the lengthcounter has reached the max lenArray[length] and if so increase the length and reset the lengthCounter
              if (lengthCounter >= lenArray[indexof]) {
                  indexof = indexof + 1;
                  lengthCounter = 0;
                  hitorgap = 0;
                  //check if there is an element in the dimonsunal array is vilid or gap
              } else if ((indexof > 1 && arrr[indexof][hitorgap] == 0) || arrr[indexof][hitorgap] == 0 && hitorgap>0 || arrr[indexof][hitorgap] == -1 ) {
                  hitorgap++;
              } else {
                  //check if the length of the element is 1 or 2 and if so sort it
                  if (indexof <= 2) {
                      lengthCounter++;
                      arr[index] = arrr[1][hitorgap];
                      index++;
                      hitorgap++;
                      //check if the length of the element is 3 or more and if so sort it
                  } else {
                      lengthCounter++;
                      arr[index] = arrr[indexof][hitorgap];
                      index++;
                      hitorgap++;
  
                  }
                  //check if there is duplicated elements and if so sort it
                  if (dupflag == true && dup[dupIndex] == arr[index - 1]) {
                      //check if there is duplicated elements and if so sort it
                      while (dup[dupIndex] == arr[index - 1]) {
                          arr[index] = arr[index - 1];
                          index++;
                          lengthCounter++;
                          //itrate the duplicated array
                          if (dupIndex != dup.length - 1) {
                              dupIndex++;
                              //if the duplicated array is finished break the loop and flag the duplicated array as false
                          } else {
                              dupflag = false;
                              break;
                          }
                      }
  
                  }
  
              }
          }
  
      }
  
      static void sort(int arr[], int index) {
          int lengthCounter[] = new int[15], tempdup[] = new int[index], DupCounter = 0,insertionSort=0;
          int[][] DimonsunalArray = new int[11][index];
          DimonsunalArray[1][0]=-1;
          tempdup[0]=2147483590;
          //loop until all elements are sorted into the dimonsunal array
          for (int i = 0; i < arr.length; i++) {
              
              String c = "" + arr[i];
              //check if the length of the element is 1 or 2 and if so sort it
              if (c.length() <= 2) {
                  lengthCounter[1] = lengthCounter[1] + 1;
                  //check if the length of the element is 3 or more and if so sort it
              } else {
                  lengthCounter[c.length()] = lengthCounter[c.length()] + 1;
              }
              //check if the element is duplicated and if so sort it
              if (arr[i] == DimonsunalArray[1][arr[i]]) {
                  DupCounter++;
                  insertionSort=DupCounter;
                  
                  while (true) {     
                     if(insertionSort!=0&&arr[i]<tempdup[insertionSort-1]){
                          tempdup[insertionSort]=tempdup[insertionSort-1];
                          
                          insertionSort--;
                      }else{
                         
                          tempdup[insertionSort]=arr[i];
                          break;
                      }
                  
                  }
              } else {
                  //check if the length of the element is 1 or 2 and if so sort it
                  if (c.length() <= 2) {
                      DimonsunalArray[1][arr[i]] = arr[i];
                      //check if the length of the element is 3 or more and if so sort it
                  } else {
                      int x = Character.getNumericValue(c.charAt(0) - 1);
                      String y = "" + x + c.substring(1);
                      //check if the element is duplicated and if so sort it
                      if (arr[i] == DimonsunalArray[c.length()][Integer.parseInt(y)]) {
                          DupCounter++;
                          insertionSort=DupCounter;
                          
                  while (true) {                    
                      if(insertionSort!=0&&arr[i]<tempdup[insertionSort-1]){
                          tempdup[insertionSort]=tempdup[insertionSort-1];
                          insertionSort--;
                      }else{
                          tempdup[insertionSort]=arr[i];
                          break;
                      }
                  
                          }
                      }else{
                          DimonsunalArray[c.length()][Integer.parseInt(y)] = arr[i];
  
                      }
                  }}}
          int duplicated[] = new int[DupCounter];
          //check if there is duplicated elements and if so sort it
          if (DupCounter >= 1) {
              System.arraycopy(tempdup, 0, duplicated, 0, DupCounter);
          /*            duplicated(duplicated);*/        }
          //go to the merge function
          merge(DimonsunalArray, arr, lengthCounter, duplicated);
      }
  
      static void printArray(int arr[]) {
          //print the sorted array
          for (int i = 0; i < arr.length; i++) {
              System.out.print(arr[i] + " ");
          }
  
          System.out.println();
      }
      
  
      // My algorthim
      public static void main(String args[]) {
          int[] arr = {3,4,3,11,23,4421,2,11,2};//new int[29999999];//max 29999999
  
                  sort(arr, 29999999);
                  
              System.out.println("\nSorted array");
              printArray(arr);
                  
          }
      }
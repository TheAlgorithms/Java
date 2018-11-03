package src.main.java.com.sorts;

import java.util.List;

public class InsertionSortArrayList {

	 /**
	   * This method implements the Generic Insertion Sort 
	   * Sorts the arraylist in increasing order
	   * @param arrList The arraylist to be sorted
	   * @param <T>      Comparable class
	   * @return sorted ArrayList
	   **/	

	 public <T extends Comparable<T>> List<T> sort(List<T> arrList)
	  {
	 int n = arrList.size();
	    for (int i = 1;i < n; i++) {
	    
	    	for (int j = i;i > 0;i--){
	    		T keyLower = arrList.get(j-1);
	    		T keyHigher = arrList.get(j);

	    		if (keyHigher.compareTo(keyLower) < 0) {
	    			arrList.set(j, keyLower);
	    			arrList.set(j - 1, keyHigher);
				} else {
					break;
				}
	    	}     
	    }
	    return arrList;
	  }
	}
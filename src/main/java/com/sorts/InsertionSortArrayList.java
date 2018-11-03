package src.main.java.com.sorts;

import java.util.List;

public class InsertionSortArrayList {

	 /**
	   * This method implements the Generic Insertion Sort 
	   *
	   * @param arrayList The arraylist to be sorted
	   *            Sorts the arraylist in increasing order
	   **/	

	 public <T extends Comparable<T>> List<T> sort(List<T> arrayList)
	  {
	 int n = arrayList.size();
	    for (int i = 1;i < n; i++) {
	    
	    	for (int j = i;i > 0;i--){
	    		T keyLower = arrayList.get(j-1);
	    		T keyHigher = arrayList.get(j);

	    		if (keyHigher.compareTo(keyLower) < 0) {
	    			arrayList.set(j, keyLower);
	    			arrayList.set(j - 1, keyHigher);
				} else {
					break;
				}
	    	}     
	    }
	    return arrayList;
	  }
	}
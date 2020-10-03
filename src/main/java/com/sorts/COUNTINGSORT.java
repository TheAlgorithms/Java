 import java.util.*;
class CountingSort { 
	void countSort(char arr[]) 
	{ 
		int n = arr.length;  
		char temp[] = new char[n];  
		int count[] = new int[256]; 
		Arrays.fill(count,0);
		for(int i=0;i<n;i++){ 
	            count[arr[i]]++; 
                }
		for(int i=1;i<256;i++){
		     count[i] += count[i-1];
		} 
		for(int i=n-1;i>=0;i--) { 
		    temp[count[arr[i]]-1] = arr[i]; 
		    count[arr[i]]++; 
		}  
		for(int i=0;i<n;i++){
		    arr[i] = temp[i];
		}  
	} 
} 

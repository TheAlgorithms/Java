import java.util.*;
class CountingSort { 
	void countSort(char arr[]){ 
		int n = arr.length;  
		char temp[] = new char[n];  
		int freq[] = new int[256]; 
		Arrays.fill(freq,0);
		for(int i=0;i<n;i++){ 
	            freq[arr[i]]++; 
                }
		for(int i=1;i<256;i++){
		     freq[i] += freq[i-1];
		} 
		for(int i=n-1;i>=0;i--) { 
		    temp[freq[arr[i]]-1] = arr[i]; 
		    freq[arr[i]]++; 
		}  
		for(int i=0;i<n;i++){
		    arr[i] = temp[i];
		}  
	} 
} 

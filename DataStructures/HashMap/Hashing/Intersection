package DataStructures.HashMap.Hashing;
/*
* this is algo which implies common mathematical set theory concept 
* called intersection in which result is common values of both the sets 
* here metaphor of sets is HashMap 


Test Case:
		Scanner scn=new Scanner(System.in);
		int len =scn.nextInt();
		int arr[]=new int[len];
		int arr2[]=new int[len];
		
		for(int i=0;i<2*len;i++) {
			
			if(i<len)
				arr[i]=scn.nextInt();
			if(i>=len) {
				arr2[i-len]=scn.nextInt();
			}
		}
		System.out.println(Main(arr,arr2));
		
	

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Intersection {

	public static ArrayList Main(int arr[],int arr2[]) {
		HashMap<Integer,Integer> hmap=new HashMap<>();
		HashMap<Integer,Integer> hmap2=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			if(hmap.containsKey(arr[i])) {
				int val=hmap.get(arr[i]);
				hmap.put(arr[i],val+1);
			}else
				hmap.put(arr[i],1);
			
		}
		ArrayList<Integer> res=new ArrayList<>();
		for(int i=0;i<arr2.length;i++) {
			if(hmap.containsKey(arr2[i])&&hmap.get(arr2[i])>0) {
				int val=hmap.get(arr2[i]);
				hmap.put(arr2[i],val-1);
				res.add(arr2[i]);
			}
			
		}
		return res;
	}
	public Intersection() {
		
	}

	

}

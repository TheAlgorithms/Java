package com.thealgorithms.datastructures.heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortAccFrequency {

	static String frequencySort(String str) {
		Map<Character,Integer> map=new HashMap<>();
		for(char c:str.toCharArray()) {
			
			map.put(c, map.getOrDefault(c   ,0)+1);
		}
for(char c:str.toCharArray()) {
			
		System.out.println(c+" "+ map.get(c));
		}
		PriorityQueue<Character> maxHeap=new PriorityQueue<> ((a,b)->map.get(b)-map.get(a));
		maxHeap.addAll(map.keySet());
		StringBuilder res=new StringBuilder();
		while(!maxHeap.isEmpty()) {
			char c=maxHeap.poll();
			int freq=map.get(c);
			for(int i=0;i<freq;i++) {
				res.append(c);
			}
		}
		return res.toString();
	}
	public static void main(String[] args) {
		System.out.println(frequencySort("aabbsssdd"));
	}

}

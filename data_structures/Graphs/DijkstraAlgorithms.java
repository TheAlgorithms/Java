//this file was written using java 8 Stream
package Graphs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DijkstraAlgorithms {
	
	private static final String INPUT_FILE = "/data_structures/Graphs/djkstraInput.txt";
	
	public static void main(String[] args) {
		//https://imgur.com/qYHTvrr
		//input file djkstra.txt
		//first line is start and end edge
		//remaining lines are graph data
		
		//read data from input file
		List<String> data = null;
		
		try {
			Stream<String> stream = Files.lines(Paths.get(System.getProperty("user.dir") + INPUT_FILE));
			data = stream.collect(Collectors.toCollection(ArrayList::new));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't read input file");
		}
		
		if(data.size() < 2){
			System.out.println("No t enough data");
			return;
		}
		
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<Integer, Map<Integer,Integer>>();
		
		int start = 0;
		int end = 0;
		
		//init data to the graph
		for (int i=0;i<data.size();i++) {
			String[] edges = data.get(i).split(",");
			if(i == 0){
				start = Integer.parseInt(edges[0]);
				end = Integer.parseInt(edges[1]);
				continue;
			}
			int distance = Integer.parseInt(edges[2]);
			for (int j = 0; j < 2; j++) {
				int index = Integer.parseInt(edges[j]);
				if(!graph.containsKey(index)){
					graph.put(index, new HashMap<Integer, Integer>());
				}
				graph.get(index).put(Integer.parseInt(edges[1-j]), distance);
			}
		}
		
		//all set! now setup for the algorithm
		Map<Integer, Integer> pathMap = new HashMap<Integer, Integer>();
		Map<Integer, String> traceMap = new HashMap<Integer, String>();
		pathMap.put(start, 0);
		traceMap.put(start, "" + start);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		while(!queue.isEmpty()){
			int currentEdge = queue.poll();
			
			int currentDistance = pathMap.get(currentEdge);
			if(!graph.containsKey(currentEdge)){
				continue;
			}
			
			Map<Integer, Integer> adjacentEdges = graph.get(currentEdge);
			for(Integer nextEdge : adjacentEdges.keySet()){
				int newDistance = currentDistance + adjacentEdges.get(nextEdge);
				if(pathMap.containsKey(nextEdge)){
					if(newDistance >= pathMap.get(nextEdge)){
						continue;
					}
				}
				pathMap.put(nextEdge, newDistance);
				traceMap.put(nextEdge, traceMap.get(currentEdge) + "," + nextEdge);
				if (nextEdge == end) {
					continue;
				}
				queue.add(nextEdge);
			}
		}		
		
		System.out.println("[Distance: " + pathMap.get(end) + "][Path: " + traceMap.get(end) + "]");
	}
	
	

}

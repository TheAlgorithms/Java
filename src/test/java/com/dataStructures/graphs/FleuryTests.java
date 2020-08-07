package com.dataStructures.graphs;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import com.dataStructures.graphs.Fleury.Graph;

class FleuryTests {
	
	/**
	 * A Eulerian graph has a Eulerian cycle 
	 * (start == terminus vertex)
	 */
	@Test
	void eulerian () {
		/*
		   1 -  3 - 5
		   |    | \/
		   |    | /\
		   2 -  4 - 6 
		 */
		Graph graph = new Graph();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);

		Fleury fleury = new Fleury(graph) ;
		ArrayList<Integer> path = fleury.runFleury();
		System.out.println(">> Test : eulerian");
		fleury.printResults(path);
		Integer[] expected = {1, 2, 4, 3, 5, 4, 6, 3, 1};
		Assert.assertArrayEquals(expected,  path.toArray());
	}
	
	
	/**
	 * A semi Eulerian graph has a Eulerian trail
	 * (start != terminus vertex).
	 */
	@Test
	void semiEulerian () {
		/*   2       6
		    / \     / \
		   1   4 - 5   7
		    \ /     \ /
		     3       8
		*/
		Graph graph = new Graph();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(5, 8);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		
		Fleury fleury = new Fleury(graph) ;
		ArrayList<Integer> path = fleury.runFleury();
		System.out.println(">> Test : semiEulerian");
		fleury.printResults(path);
		Integer[] expected = {5, 6, 7, 8, 5, 4, 2, 1, 3, 4};
		Assert.assertArrayEquals(expected,  path.toArray());
	}
	
	
	/**
	 * Given graph has not Eulerian cycle or trail.
	 */
	@Test
	void nonEulerian () {
		/*
		   1  -   3
		   |\   / |
		   |  5   |
		   |/   \ |
		   2  -   4
		 */
		Graph graph = new Graph();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(1, 5);
		graph.addEdge(3, 5);
		graph.addEdge(2, 5);
		graph.addEdge(4, 5);

		Fleury fleury = new Fleury(graph) ;
		ArrayList<Integer> path = fleury.runFleury();
		System.out.println(">> Test : nonEulerian");
		fleury.printResults(path);
		assertEquals(null, path);
	}
	
	/**
	 * A semi Eulerian graph with parallel edges.
	 */
	@Test
	void semiEulerianMultigraph () {
		/*
		   1  -   2
		    \\  / 
		      3   
		    /   \ 
		   4  -  5
		 */
		Graph graph = new Graph();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);

		Fleury fleury = new Fleury(graph) ;
		ArrayList<Integer> path = fleury.runFleury();
		System.out.println(">> Test : semiEulerianMultigraph");
		fleury.printResults(path);
		Integer[] expected = {3, 1, 2, 3, 4, 5, 3, 1};
		Assert.assertArrayEquals(expected,  path.toArray());
	}

}

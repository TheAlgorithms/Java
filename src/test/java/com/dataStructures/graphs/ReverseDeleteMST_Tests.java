package com.dataStructures.graphs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.dataStructures.graphs.ReverseDeleteMST.Graph;

class ReverseDeleteMST_Tests {

	@Test
	void test1() {
	/*      (1)----8----(2)-----7----(3) 
	       / |           | \          | \
	      4  |           2  \         |  9
	     /   |           |   \        |   \
	   (0)   11   7---- (8)    4     14    (4)
	     \   |   /       |      \     |   /
	      8  |  /        6       \    |  10
	       \ | /         |        \   | /
	        (7)----1----(6)----2----(5)  
	 */
		
		Graph graph = new Graph(); 
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 7, 8);
		graph.addEdge(1, 7, 11);
		graph.addEdge(1, 2, 8);
		graph.addEdge(2, 3, 7);
		graph.addEdge(2, 5, 4);
		graph.addEdge(2, 8, 2);
		graph.addEdge(3, 4, 9);
		graph.addEdge(3, 5, 14);
		graph.addEdge(4, 5, 10);
		graph.addEdge(5, 6, 2);
		graph.addEdge(6, 7, 1);
		graph.addEdge(6, 8, 6);
		graph.addEdge(7, 8, 7);
		
		new ReverseDeleteMST(graph);
		assertEquals(37, graph.printGraph());
	}
	
	@Test
	void test2() {
	/* Wheel
	       -----(2)   
	      /     |  \   
	     1      2   5
	    /       |    \
	   (6)--7--(1)-3-(3)
	    \       | \   |
	     5      3  4  6 
	      \     |   \ |
	       ----(5)-3-(4)
	 */
		
		Graph graph = new Graph(); 
		graph.addEdge(1, 2, 2);
		graph.addEdge(1, 3, 3);
		graph.addEdge(1, 4, 4);
		graph.addEdge(1, 5, 3);
		graph.addEdge(1, 6, 7);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 4, 6);
		graph.addEdge(4, 5, 3);
		graph.addEdge(5, 6, 5);
		graph.addEdge(6, 2, 1);
		
		new ReverseDeleteMST(graph);
		assertEquals(12, graph.printGraph());
	}
	
	@Test
	void test3() {
	/* Star = mst
	          (2)   
	           |     
	           4   
	           |    
	  (6)--7--(1)--2--(3)
	           | \   
	           3   4   
	           |    \ 
	          (5)   (4)
	 */
		
		Graph graph = new Graph(); 
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 4, 4);
		graph.addEdge(1, 5, 3);
		graph.addEdge(1, 6, 7);

		new ReverseDeleteMST(graph);
		assertEquals(20, graph.printGraph());
	}
	
	@Test
	void test4() {
	/* 
	           (1)---3---(2)--2--(3)
	            |      /  | \     |
	            2    4    2   4   1
	            |  /      |     \ |
	    (7)--1--(6)      (5)--2--(4)
	 */
		
		Graph graph = new Graph(); 
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 6, 2);
		graph.addEdge(2, 3, 2);
		graph.addEdge(2, 4, 4);
		graph.addEdge(2, 5, 2);
		graph.addEdge(2, 6, 4);
		graph.addEdge(3, 4, 1);
		graph.addEdge(4, 5, 3);
		graph.addEdge(6, 7, 1);
		
		new ReverseDeleteMST(graph);
		assertEquals(11, graph.printGraph());
	}
}

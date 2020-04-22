package com.search;

import java.util.Random;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

/**
 * Unit test for simple App.
 */
public class BFSTest extends TestCase {
	int numOfVertices = 10;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public BFSTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(BFSTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testBFS() {

		DefaultDirectedGraph<vertex, Double> graph = new DefaultDirectedGraph<vertex, Double>(
				Double.class);
		vertex v = null;
		for (int i = 0; i < this.numOfVertices; i++) {
			v = new vertex("v" + i);
			graph.addVertex(v);
		}
		
		// use last vertex as start point of search
		vertex lastVertex = v;

		Set<vertex> vertices = graph.vertexSet();

		// add edges to graph
		Random rand = new Random();
		Double nextEdge = 0.0;
		for (vertex start : vertices)
			for (vertex end : vertices)
				if (!start.equals(end) && !graph.containsEdge(start, end)) {
					nextEdge = rand.nextDouble();
					if (nextEdge > 0.9)
						graph.addEdge(start, end, nextEdge);
				}

		System.out.println("Vertices: " + vertices.size());
		System.out.println("Edges: " + graph.edgeSet().size());

		BreadthFirstIterator<vertex, Double> bfsIter = new BreadthFirstIterator<vertex, Double>(
				graph, lastVertex);

		BFS<Double> bfs = new BFS<Double>(graph);
		vertex[] bfsResult = bfs.breadthFirstTree(lastVertex);

		for (int i = 0; i < this.numOfVertices && bfsIter.hasNext(); i++) {
			System.out.println(bfsResult[i] + " " + bfsIter.next());
		}

	}
}

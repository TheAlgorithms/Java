package com.graphs;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Hello world!
 * 
 */
public class Dijkstra {
	DefaultDirectedWeightedGraph<Integer, DefaultEdge> dag;
	Integer startVertex, endVertex;
	Hashtable<Integer, Double> distance;
	PriorityQueue<Integer> queue;

	public Dijkstra(DefaultDirectedWeightedGraph<Integer, DefaultEdge> dag,
			Integer startVertex, Integer endVertex) {
		super();
		this.dag = dag;
		this.startVertex = startVertex;
		this.endVertex = endVertex;
	}

	public double getPathLength() {
		// init single source
		initSingleSource();

		// make priority queue
		queue.addAll(dag.vertexSet());

		// priority first search
		while (!queue.isEmpty()) {
			Integer vertexU = queue.poll();
			if (distance.get(vertexU) == Double.MAX_VALUE)
				break;

			for (Integer vertexV : dag.vertexSet()) {
				if (dag.containsEdge(vertexU, vertexV))
					relax(vertexU, vertexV,
							dag.getEdgeWeight(dag.getEdge(vertexU, vertexV)));
			}

		}
		// return distance
		return distance.get(endVertex);
	}

	private void relax(Integer vertexU, Integer vertexV, double edgeWeight) {

		if (distance.get(vertexU) + edgeWeight < distance.get(vertexV))
			distance.put(vertexV, distance.get(vertexU) + edgeWeight);

	}

	private void initSingleSource() {
		distance = new Hashtable<Integer, Double>();
		// init the distance table
		for (Integer v : dag.vertexSet())
			if (v == startVertex)
				distance.put(v, 0.0);
			else
				distance.put(v, Double.MAX_VALUE);
		EntryComparator compare = new EntryComparator();

		queue = new PriorityQueue<Integer>(dag.vertexSet().size(), compare);
	}

	class EntryComparator implements Comparator<Integer> {

		public int compare(Integer arg0, Integer arg1) {
			if (distance.get(arg0) < distance.get(arg1))
				return -1;
			else
				return 1;

		}

	}
}

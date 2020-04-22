package com.search;
import java.awt.Color;
import java.io.*; 
import java.util.*; 

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * Hello world!
 * 
 */
public class BFS<E extends Comparable<E>> {

	private DefaultDirectedGraph<vertex, E> graph;
	private Set<vertex> vertices;
	vertex[] visitedVertices;

	public BFS(DefaultDirectedGraph<vertex, E> graph) {
		//
		this.graph = graph;
		this.vertices = graph.vertexSet();
	}

	public vertex[] breadthFirstTree(vertex source) {
		// init
		for (vertex v : vertices) {
			v.color = Color.WHITE;
			v.distance = Double.MAX_VALUE;
			v.prevVertex = null;
		}
				
		source.color = Color.GRAY;
		source.distance = 0;
		source.prevVertex = null;
		
		// start bfs
		PriorityQueue<vertex> q = new PriorityQueue<vertex>();
		q.offer(source);
		
		while(!q.isEmpty()) {
			vertex u = q.poll();
			for (vertex adj : vertices)
				if (graph.containsEdge(u,adj) && adj.color == Color.WHITE) {
					adj.color = Color.GRAY;
					adj.distance = u.distance + 1;
					adj.prevVertex = u;
					q.offer(adj);
				}
			u.color = Color.BLACK;
		}
		
		this.visitedVertices = new vertex[vertices.size()];
		int i = 0;
		for (vertex v : vertices)
			if (i < vertices.size()) {
				visitedVertices[i] = v;
				i++;
			}

		Arrays.sort(visitedVertices);
		return visitedVertices;
		
	}

}


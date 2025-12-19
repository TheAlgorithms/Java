package com.thealgorithms.graph;

/** Represents an edge in an undirected weighted graph. */
public class Edge implements Comparable<Edge> {

  public final int source;
  public final int destination;
  public final int weight;

  /**
   * Constructs an edge with given source, destination, and weight.
   *
   * @param source the source vertex
   * @param destination the destination vertex
   * @param weight the weight of the edge
   */
  public Edge(final int source, final int destination, final int weight) {
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  /**
   * Compares edges based on their weight.
   *
   * @param other the edge to compare with
   * @return comparison result
   */
  @Override
  public int compareTo(final Edge other) {
    return Integer.compare(this.weight, other.weight);
  }
}

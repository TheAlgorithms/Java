package com.thealgorithms.datastructures.graphs;
public abstract class AbstractEdge {
    protected int from;
    protected int to;
    protected int weight;

    public AbstractEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
}
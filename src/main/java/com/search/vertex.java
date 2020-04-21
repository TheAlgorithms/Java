package com.search;

import java.awt.Color;

public class vertex implements Comparable<vertex> {
    String name;
    Color color;
    vertex prevVertex;
    double distance;

    public vertex(String name) {
        this.name = name;
        this.color = Color.WHITE;
        this.prevVertex = null;
    }

    public int compareTo(vertex other) {
        return this.distance < other.distance ? -1 : 1;
    }

}
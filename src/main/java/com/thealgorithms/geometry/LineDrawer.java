package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.List;

public class LineDrawer {
    private LineDrawingStrategy strategy;

    public LineDrawer(LineDrawingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(LineDrawingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Point> drawLine(int x0, int y0, int x1, int y1) {
        return strategy.findLine(x0, y0, x1, y1);
    }
}

package com.thealgorithms.geometry;

import java.awt.Point;
import java.util.List;

public interface LineDrawingStrategy {
    List<Point> findLine(int x0, int y0, int x1, int y1);
}

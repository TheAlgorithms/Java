package com.thealgorithms.lineclipping.utils;

import java.util.Objects;

/**
 * @author moksedursohan
 * @since 10/4/24
 */
public class Line {

    public Point start;
    public Point end;

    public Line() {
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Line line)) {
            return false;
        }

        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Line from " + start + " to " + end;
    }
}

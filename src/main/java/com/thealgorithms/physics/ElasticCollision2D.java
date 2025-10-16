package com.thealgorithms.physics;

public final class ElasticCollision2D {

    private ElasticCollision2D() {
        // utility class
    }

    public static class Body {
        public double x, y;
        public double vx, vy;
        public double mass, radius;

        public Body(double x, double y, double vx, double vy, double mass, double radius) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.mass = mass;
            this.radius = radius;
        }
    }

    /**
     * Resolve instantaneous elastic collision between two circular bodies.
     *
     * @param a first body
     * @param b second body
     */
    public static void resolveCollision(Body a, Body b) {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double dist = Math.hypot(dx, dy);
        if (dist == 0) return; // overlapping

        double nx = dx / dist;
        double ny = dy / dist;

        // relative velocity along normal
        double rv = (b.vx - a.vx) * nx + (b.vy - a.vy) * ny;
        if (rv > 0) return; // moving apart

        // Correct 2D impulse with masses
        double m1 = a.mass;
        double m2 = b.mass;

        double j = -(1 + 1.0) * rv / (1.0 / m1 + 1.0 / m2);

        // impulse vector along normal
        double impulseX = j * nx;
        double impulseY = j * ny;

        a.vx -= impulseX / m1;
        a.vy -= impulseY / m1;
        b.vx += impulseX / m2;
        b.vy += impulseY / m2;
    }
}

package com.aima.exercise.sdk.d2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConvexPolygon {
    private final List<Point> vertices;
    private final Polygon polygon;

    public ConvexPolygon(List<Point> vertices) {
        this.vertices = vertices;
        polygon = new Polygon();
        vertices.forEach(v -> polygon.addPoint(v.x, v.y));
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegment() {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            segments.add(new Segment(vertices.get(i), vertices.get(i + 1)));
        }
        segments.add(new Segment(vertices.get(vertices.size() - 1), vertices.get(0)));
        return segments;
    }

    // Check if point is inside or on the edge of the polygon.
    public boolean contains(Point p) {
        return polygon.contains(p.x, p.y);
    }
}

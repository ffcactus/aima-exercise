package com.aima.search.exercise9;

import java.util.List;

public class ConvexPolygon {
    private final List<Point> vertices;

    public ConvexPolygon(List<Point> vertices) {
        this.vertices = vertices;
    }

    public List<Point> getVertices() {
        return vertices;
    }
}

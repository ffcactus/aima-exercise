package com.aima.search.exercise9;

public class Line {
    private final Point from;
    private final Point to;

    public Line(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
}

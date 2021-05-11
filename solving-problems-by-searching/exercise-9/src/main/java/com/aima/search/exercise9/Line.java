package com.aima.search.exercise9;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Line{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return from.equals(line.from) && to.equals(line.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}

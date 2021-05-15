package com.aima.exercise.sdk.d2;

import java.util.Objects;

public class Segment {
    public final Point from;
    public final Point to;

    public Segment(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment line = (Segment) o;
        return from.equals(line.from) && to.equals(line.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}

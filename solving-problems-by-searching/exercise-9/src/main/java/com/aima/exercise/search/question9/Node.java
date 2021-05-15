package com.aima.exercise.search.question9;


import com.aima.exercise.sdk.d2.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Node represents a {@link Point} and other {@link Point}s it can reach.
 */
public class Node {
    private final Point point;
    private final List<Point> children;

    public Node(Point point) {
        this.point = point;
        this.children = new ArrayList<>();
    }

    public Node(Point point, List<Point> children) {
        this.point = point;
        this.children = children;
    }


    public Point getPoint() {
        return point;
    }

    public List<Point> getChildren() {
        return children;
    }

    public void addChild(Point child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "Node{" +
                "point=" + point +
                ", children=" + children +
                '}';
    }
}

package com.aima.search.exercise9;


import java.util.HashSet;
import java.util.Set;

/**
 * Node represents a {@l
 * ink Point} and other {@link Point}s it can reach.
 */
public class Node {
    private final Point point;
    private final Set<Point> children;

    public Node(Point point) {
        this.point = point;
        this.children = new HashSet<>();
    }

    public Node(Point point, Set<Point> children) {
        this.point = point;
        this.children = children;
    }


    public Point getPoint() {
        return point;
    }

    public Set<Point> getChildren() {
        return children;
    }

    public void addChild(Point child) {
        this.children.add(child);
    }
}

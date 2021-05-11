package com.aima.search.exercise9;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Node represents a {@l
 * ink Point} and other {@link Point}s it can reach.
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

package com.aima.exercise.search.question9;

import com.aima.exercise.sdk.d2.ConvexPolygon;
import com.aima.exercise.sdk.d2.D2Utils;
import com.aima.exercise.sdk.d2.Point;
import com.aima.exercise.sdk.d2.Segment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise9 extends JFrame {
    private final List<ConvexPolygon> convexPolygons;
    private final StateSpace stateSpace;
    private final Point from;
    private final Point to;
    private Solution solution;

    public Exercise9(Point from, Point to, List<ConvexPolygon> convexPolygons) {
        super();
        init();
        this.convexPolygons = convexPolygons;
        this.from = from;
        this.to = to;
        this.stateSpace = calculateStateSpace(from, to, convexPolygons);

        var searcher = new BfsSearcher();
        solution = searcher.search(getFromState(), getToState(), stateSpace);
    }

    private void init() {
        setTitle("Search exercise 9");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private State getFromState() {
        return stateSpace.getStates().stream().filter(s -> s.getNode().getPoint().equals(from)).findFirst().orElseThrow();
    }

    private State getToState() {
        return stateSpace.getStates().stream().filter(s -> s.getNode().getPoint().equals(to)).findFirst().orElseThrow();
    }

    private StateSpace calculateStateSpace(Point from, Point to, List<ConvexPolygon> convexPolygons) {
        var redundant = new HashMap<Segment, Boolean>();
        List<Node> nodes = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        if (from != null) {
            points.add(from);
        }
        if (to != null) {
            points.add(to);
        }
        points.addAll(
                convexPolygons.stream().flatMap(
                        e -> e.getVertices().stream()
                ).collect(Collectors.toSet())
        );
        for (var point : points) {
            nodes.add(new Node(point));
        }
        for (var fromNode : nodes) {
            // Every node is a state.
            var fromPoint = fromNode.getPoint();
            for (var toNode : nodes) {
                var toPoint = toNode.getPoint();
                if (fromPoint.equals(toPoint)) {
                    continue;
                }
                var intersect = false;
                var s = new Segment(fromPoint, toPoint);
                if (redundant.containsKey(s)) {
                    intersect = redundant.get(s);
                    if (intersect) {
                        break;
                    }
                }
                for (var convexPolygon : convexPolygons) {
                    if (lineAcrossPolygon(s, convexPolygon)) {
                        intersect = true;
                        break;
                    }
                }
                redundant.put(s, intersect);
                redundant.put(new Segment(s.from, s.to), intersect);
                if (!intersect) {
                    fromNode.addChild(toNode);
                }
            }
        }
        return new StateSpace(nodes.stream().map(State::new).collect(Collectors.toList()));
    }

    public boolean lineAcrossPolygon(Segment s, ConvexPolygon p) {
        var segments = p.getSegment();
        int touches = 0;
        for (var segment : segments) {
            switch (D2Utils.segmentIntersection(s, segment)) {
                case Typical:
                    return true;
                case Overlap:
                    return false;
                case Touch:
                    touches++;
                default:
                    break;
            }
        }

        // In this exercise, points are always the vertices of the polygon.
        if (touches == 4) {
            return true;
        }
        var containFrom = p.getVertices().contains(s.from);
        var containTo = p.getVertices().contains(s.to);
        return containFrom && containTo;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintPolygons(g2d);
        paintFromTo(g2d);
        paintSolution(g2d);
    }

    private void paintPolygons(Graphics2D g2d) {
        convexPolygons.forEach((polygon) -> {
            var vertices = polygon.getVertices();

            var xs = vertices.stream().map(vertex -> vertex.x).mapToInt(o -> o).toArray();
            var ys = vertices.stream().map(vertex -> vertex.y).mapToInt(o -> o).toArray();
            g2d.setColor(Color.orange);
            g2d.fillPolygon(xs, ys, xs.length);
            g2d.setColor(Color.black);
        });
    }

    private void paintFromTo(Graphics2D g2d) {
        var previousColor = g2d.getColor();
        g2d.setColor(Color.green);
        g2d.fillOval(from.x - 2, from.y - 2, 5, 5);
        g2d.fillOval(to.x - 2, to.y - 2, 5, 5);
        g2d.setColor(previousColor);
    }

    private void paintSolution(Graphics2D g2d) {
        if (solution == null) {
            g2d.drawString("No solution", 100, 100);
            return;
        }
        var previousColor = g2d.getColor();
        g2d.setColor(Color.red);
        var state = solution.getState();
        while (state.getFrom() != null) {
            var fromP = state.getNode().getPoint();
            var toP = state.getFrom().getNode().getPoint();
            g2d.drawLine(fromP.x, fromP.y, toP.x, toP.y);
            state = state.getFrom();
        }
        g2d.setColor(previousColor);
    }

    public static ConvexPolygon createRectangle(Point p, int r) {
        return new ConvexPolygon(List.of(
                p,
                new Point(p.x + r, p.y),
                new Point(p.x + r, p.y + r),
                new Point(p.x, p.y + r)
        ));
    }

    public static List<ConvexPolygon> createPolygons1() {
        var polygons = new ArrayList<ConvexPolygon>();
        var margin = 100;
        var r = 100;
        for (var i = 0; i < 10; i++) {
            polygons.add(Exercise9.createRectangle(new Point(100 + i * (r + margin), 100), r));
        }

        for (var i = 0; i < 9; i++) {
            polygons.add(Exercise9.createRectangle(new Point(200 + i * (r + margin), 300), r));
        }

        for (var i = 0; i < 10; i++) {
            polygons.add(Exercise9.createRectangle(new Point(100 + i * (r + margin), 500), r));
        }

        for (var i = 0; i < 9; i++) {
            polygons.add(Exercise9.createRectangle(new Point(200 + i * (r + margin), 700), r));
        }

        for (var i = 0; i < 10; i++) {
            polygons.add(Exercise9.createRectangle(new Point(100 + i * (r + margin), 900), r));
        }

        for (var i = 0; i < 9; i++) {
            polygons.add(Exercise9.createRectangle(new Point(200 + i * (r + margin), 1100), r));
        }

        polygons.add(new ConvexPolygon(List.of(new Point(650, 225), new Point(1450, 225), new Point(1450, 275), new Point(650, 275))));
        polygons.add(new ConvexPolygon(List.of(new Point(650, 425), new Point(1450, 425), new Point(1450, 475), new Point(650, 475))));
        polygons.add(new ConvexPolygon(List.of(new Point(650, 625), new Point(1450, 625), new Point(1450, 675), new Point(650, 675))));
        polygons.add(new ConvexPolygon(List.of(new Point(650, 825), new Point(1450, 825), new Point(1450, 875), new Point(650, 875))));
        polygons.add(new ConvexPolygon(List.of(new Point(650, 1025), new Point(1450, 1025), new Point(1450, 1075), new Point(650, 1075))));
        return polygons;
    }

    public static List<ConvexPolygon> createPolygons2() {
        return List.of(new ConvexPolygon(List.of(new Point(650, 225), new Point(1450, 225), new Point(1450, 1075), new Point(650, 1075))));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Point from = new Point(1050, 50);
            Point to = new Point(1050, 1250);
            Exercise9 exercise = new Exercise9(from, to, createPolygons1());
            exercise.setVisible(true);
        });
    }
}

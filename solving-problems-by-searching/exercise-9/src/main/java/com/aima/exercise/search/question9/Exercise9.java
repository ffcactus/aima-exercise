package com.aima.exercise.search.question9;

import com.aima.exercise.sdk.d2.ConvexPolygon;
import com.aima.exercise.sdk.d2.D2Utils;
import com.aima.exercise.sdk.d2.Point;
import com.aima.exercise.sdk.d2.Segment;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise9 extends JFrame {
    private final Set<Node> nodes = new HashSet<>();
    private final Point from;
    private final Point to;
    private final List<ConvexPolygon> convexPolygons;

    public Exercise9(Point from, Point to, List<ConvexPolygon> convexPolygons) {
        super();
        this.from = from;
        this.to = to;
        this.convexPolygons = convexPolygons;
        init();
    }

    private void init() {
        setTitle("Search exercise 9");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void paintNodes(Graphics2D g2d) {
        nodes.forEach(node -> {
            var point = node.getPoint();
            node.getChildren().forEach(child -> g2d.drawLine(point.x, point.y, child.x, child.y));
        });
    }

    private void calculateStateSpace() {
        var redundant = new HashMap<Segment, Boolean>();
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
            // Every node is a state.
            var node = new Node(point);
            // Find the valid children to the node. A valid child is the one that if we draw a line from the node to the child,
            // the line does not across any edges to all the polygons.
            for (var childPoint : points) {
                if (point.equals(childPoint)) {
                    continue;
                }
                var intersect = false;
                var s = new Segment(point, childPoint);
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
                    node.addChild(childPoint);
                }
            }
            nodes.add(node);
        }
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
        calculateStateSpace();
        paintPolygons(g2d);
        paintNodes(g2d);
    }

    public static ConvexPolygon createRectangle(Point p, int r) {
        return new ConvexPolygon(List.of(
                p,
                new Point(p.x + r, p.y),
                new Point(p.x + r, p.y + r),
                new Point(p.x, p.y + r)
        ));
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Point from = new Point(800, 100);
            Point to = new Point(800, 900);

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

            Exercise9 exercise = new Exercise9(null, null, polygons);
            exercise.setVisible(true);
        });
    }
}

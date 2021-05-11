package com.aima.search.exercise9;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private void paintPolygons(Graphics2D g2d) {
        convexPolygons.stream().forEach((polygon) -> {
            var vertices = polygon.getVertices();

            var xs = vertices.stream().map(vertex -> vertex.x).mapToInt(o -> (int)o).toArray();
            var ys = vertices.stream().map(vertex -> vertex.y).mapToInt(o -> (int)o).toArray();
            g2d.drawPolygon(xs, ys, xs.length);
        });
    }

    private void paintNodes(Graphics2D g2d) {
        nodes.stream().forEach(node -> {
            var point = node.getPoint();
            System.out.println(node);
            g2d.drawString(point.toString(), point.x, point.y);
            node.getChildren().stream().forEach(child -> {
                g2d.drawLine(point.x, point.y, child.x, child.y);
            });
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintPolygons(g2d);
        List<Point> points = new ArrayList<>();
        points.add(from);
        points.add(to);
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
                var line = new Line(point, childPoint);
                for (var convexPolygon : convexPolygons) {
                    if (!D2Utils.lineIntersectPolygon(line, convexPolygon, false)) {
                        node.addChild(childPoint);
                    }
                }
            }
            nodes.add(node);
        }
        paintNodes(g2d);
    }

    private void init() {
        setTitle("Search exercise 9");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Point from = new Point(500, 100);
            Point to = new Point(500, 900);

            ConvexPolygon polygon = new ConvexPolygon(List.of(
                    new Point(250, 250),
                    new Point(750, 250),
                    new Point(750, 750),
                    new Point(250, 750)));
            Exercise9 exercise = new Exercise9(from, to, List.of(polygon));
            exercise.setVisible(true);
        });
    }
}

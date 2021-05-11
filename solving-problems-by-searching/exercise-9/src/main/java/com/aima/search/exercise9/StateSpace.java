package com.aima.search.exercise9;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StateSpace {
    private final Set<Node> nodes;

    public StateSpace(Point from, Point to, List<ConvexPolygon> convexPolygons) {
        this.nodes = new HashSet<>();
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
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}

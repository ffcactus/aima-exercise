package com.aima.search.exercise9;

import java.util.ArrayList;
import java.util.List;

public class D2Utils {

    /**
     * Given three colinear points p, q, r, the function checks if point q lies on line segment 'pr'
     */
    public static boolean onSegment(Point p, Point q, Point r)
    {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    public static Orientation orientation(Point p, Point q, Point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return Orientation.Colinear; // colinear

        return (val > 0)? Orientation.Clock: Orientation.CounterClock; // clock or counterclock wise
    }

    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2, boolean includeSpecialCase)
    {
        // Find the four orientations needed for general and
        // special cases
        Orientation o1 = orientation(p1, q1, p2);
        Orientation o2 = orientation(p1, q1, q2);
        Orientation o3 = orientation(p2, q2, p1);
        Orientation o4 = orientation(p2, q2, q1);

        if (!includeSpecialCase) {
            // General case
            return (o1 != o2 && o3 != o4) &&
                    (o1 != Orientation.Colinear) &&
                    (o2 != Orientation.Colinear) &&
                    (o3 != Orientation.Colinear) &&
                    (o4 != Orientation.Colinear);
        }


        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == Orientation.Colinear && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == Orientation.Colinear && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == Orientation.Colinear && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == Orientation.Colinear && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    public static boolean doIntersect(Line l1, Line l2, boolean includeSpecialCase) {
        return doIntersect(l1.getFrom(), l1.getTo(), l2.getFrom(), l2.getTo(), includeSpecialCase);
    }

    public static boolean lineIntersectPolygon(Line line, ConvexPolygon convexPolygon, boolean includeSpecialCase) {
        var vertices = convexPolygon.getVertices();
        List<Line> polygonLines = new ArrayList<Line>();
        for (var i = 0; i < vertices.size() - 1; i++) {
            polygonLines.add(new Line(vertices.get(i), vertices.get(i+1)));
        }
        polygonLines.add(new Line(vertices.get(vertices.size() -1), vertices.get(0)));
        for (var polygonLine : polygonLines) {
            if (doIntersect(line, polygonLine, includeSpecialCase)) {
                return true;
            }
        }
        var toIndex = vertices.indexOf(line.getTo());
        var fromIndex = vertices.indexOf(line.getFrom());
        // check if the line is not a edge but connects 2 vertices.
        return toIndex != -1 && fromIndex != -1 && Math.abs(toIndex - fromIndex) > 1;
    }
}

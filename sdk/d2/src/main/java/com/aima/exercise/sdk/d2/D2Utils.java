package com.aima.exercise.sdk.d2;

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

        if (val == 0) return Orientation.Collinear; // colinear

        return (val > 0)? Orientation.Clock: Orientation.CounterClock; // clock or counterclock wise
    }

    // Check if p is on Segment.
    public static boolean pointOnSegment(Point p, Segment s) {
        return onSegment(s.from, s.to, p);
    }

    public static SegmentIntersection segmentIntersection(Segment s1, Segment s2) {
        var p1 = s1.from;
        var q1 = s1.to;
        var p2 = s2.from;
        var q2 = s2.to;
        // Find the four orientations needed for general and
        // special cases
        Orientation o1 = orientation(p1, q1, p2);
        Orientation o2 = orientation(p1, q1, q2);
        Orientation o3 = orientation(p2, q2, p1);
        Orientation o4 = orientation(p2, q2, q1);
        // typical intersection or only one point touch.
        if (o1 != o2 && o3 != o4) {
            var p2collinear = o1 == Orientation.Collinear;
            var q2collinear = o2 == Orientation.Collinear;
            var p1collinear = o3 == Orientation.Collinear;
            var q1collinear = o4 == Orientation.Collinear;
            if ((p2collinear ^ q2collinear) || (p1collinear ^ q1collinear)) {
                return SegmentIntersection.Touch;
            } else {
                return SegmentIntersection.Typical;
            }
        }
        if ((o1 != o2) ^ (o3 != o4)) {
            return SegmentIntersection.Separate;
        }

        if (o1 == Orientation.Collinear && o3 == Orientation.Collinear) {
            int a, b, c, d;
            if (p1.x == q1.x) {
                a = Math.min(p1.y, q1.y);
                b = Math.max(p1.y, q1.y);
                c = Math.min(p2.y, q2.y);
                d = Math.max(p2.y, q2.y);
            } else {
                a = Math.min(p1.x, q1.x);
                b = Math.max(p1.x, q1.x);
                c = Math.min(p2.x, q2.x);
                d = Math.max(p2.x, q2.x);
            }
            if (b == c || a == d) {
                return SegmentIntersection.Touch;
            }
            if (c > b || a > d) {
                return SegmentIntersection.Separate;
            }
            return SegmentIntersection.Overlap;
        }
        return SegmentIntersection.Separate;
    }

}

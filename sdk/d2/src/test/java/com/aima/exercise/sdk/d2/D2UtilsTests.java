package com.aima.exercise.sdk.d2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class D2UtilsTests {

    @Test
    void testSegmentIntersection_Overlap() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 20));

        var s2 = new Segment(new Point(10, 10), new Point(20, 20));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 0), new Point(15, 15));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(15, 15), new Point(30, 30));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 0), new Point(30, 30));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Overlap_Horizontal() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 10));

        var s2 = new Segment(new Point(10, 10), new Point(20, 10));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 10), new Point(15, 10));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(15, 10), new Point(30, 10));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 10), new Point(30, 10));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Overlap_Vertical() {
        var s1 = new Segment(new Point(10, 10), new Point(10, 20));

        var s2 = new Segment(new Point(10, 10), new Point(10, 20));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(10, 0), new Point(10, 15));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(10, 15), new Point(10, 30));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(10, 0), new Point(10, 30));
        Assertions.assertEquals(SegmentIntersection.Overlap, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Touch() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 20));

        var s2 = new Segment(new Point(20, 20), new Point(30, 30));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 0), new Point(10, 10));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(20, 20), new Point(30, 20));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Touch_Horizontal() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 10));

        var s2 = new Segment(new Point(20, 10), new Point(30, 10));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(0, 10), new Point(10, 10));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Touch_Vertical() {
        var s1 = new Segment(new Point(10, 10), new Point(10, 20));

        var s2 = new Segment(new Point(10, 20), new Point(10, 30));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));

        s2 = new Segment(new Point(10, 0), new Point(10, 10));
        Assertions.assertEquals(SegmentIntersection.Touch, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Separate() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 20));

        var s2 = new Segment(new Point(30, 30), new Point(40, 40));
        Assertions.assertEquals(SegmentIntersection.Separate, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Separate_Horizontal() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 10));

        var s2 = new Segment(new Point(30, 10), new Point(40, 10));
        Assertions.assertEquals(SegmentIntersection.Separate, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Separate_Vertical() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 10));

        var s2 = new Segment(new Point(30, 10), new Point(40, 10));
        Assertions.assertEquals(SegmentIntersection.Separate, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Typical() {
        var s1 = new Segment(new Point(10, 10), new Point(20, 20));

        var s2 = new Segment(new Point(20, 10), new Point(10, 20));
        Assertions.assertEquals(SegmentIntersection.Typical, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Typical_HorizontalVertical() {
        var s1 = new Segment(new Point(0, 10), new Point(20, 10));

        var s2 = new Segment(new Point(10, 0), new Point(10, 20));
        Assertions.assertEquals(SegmentIntersection.Typical, D2Utils.segmentIntersection(s1, s2));
    }

    @Test
    void testSegmentIntersection_Runtime() {
        // Segment{from=Point{x=500, y=100}, to=Point{x=250, y=250}} and Segment{from=Point{x=750, y=250}, to=Point{x=750, y=750}}
        var s1 = new Segment(new Point(500, 100), new Point(250, 250));
        var s2 = new Segment(new Point(750, 250), new Point(750, 750));
        Assertions.assertEquals(SegmentIntersection.Separate, D2Utils.segmentIntersection(s1, s2));
    }
}

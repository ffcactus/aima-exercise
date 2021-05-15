package com.aima.exercise.sdk.d2;

// This enum define the intersection type between two lines.
public enum SegmentIntersection {

    // The typical intersection.
    Typical,

    // The two line overlaps.
    Overlap,

    // One and of the line's end point is on the other line, and only this endpoint is on the other line.
    Touch,

    // There is no point overlap on these two lines.
    Separate,
}

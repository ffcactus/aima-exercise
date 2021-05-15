package com.aima.exercise.sdk.d2;

// This enum defines the possible position between a point and a segment.
public enum Orientation {
    // The point is on the segment or on the line of the segment.
    Collinear,

    // The start, end and the point is clockwise.
    Clock,

    // The start, end and the point is counterclockwise.
    CounterClock,
}

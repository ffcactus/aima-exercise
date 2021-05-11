package com.aima.search.exercise9test;

import com.aima.search.exercise9.ConvexPolygon;
import com.aima.search.exercise9.Point;
import com.aima.search.exercise9.StateSpace;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StateSpaceTests {
    @Test
    void StateSpaceCase1() {
        Point from = new Point(0, 0);
        Point to = new Point(0, 100);

        ConvexPolygon polygon = new ConvexPolygon(List.of(
                new Point(25, 25),
                new Point(75, 25),
                new Point(25, 75),
                new Point(75, 75)));
        var stateSpace = new StateSpace(from, to, List.of(polygon));

    }
}

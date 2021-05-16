package com.aima.exercise.search.question9;

import com.aima.exercise.sdk.d2.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {
    // private final Node node;
    private final List<State> nextStates = new ArrayList<>();
    private State from;
    private final Point point;

    public State(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public void addNextState(State next) {
        nextStates.add(next);
    }

    public List<State> getNextStates() {
        return nextStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(point, state.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return point.toString();
    }
}

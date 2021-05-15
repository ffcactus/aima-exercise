package com.aima.exercise.search.question9;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class State {
    private final Node node;
    private State from;

    public State(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public List<Action> getActions() {
        return node.getChildren().stream().map(State::new).map(Action::new).collect(Collectors.toList());
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(node, state.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

    @Override
    public String toString() {
        return node.toString();
    }
}

package com.aima.exercise.search.question9;

public class Action {
    private final State state;

    public Action(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}

package com.aima.exercise.search.question9;

public class Step {
    private final State state;
    private final Action action;

    public Step(State state, Action action) {
        this.state = state;
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public Action getAction() {
        return action;
    }
}

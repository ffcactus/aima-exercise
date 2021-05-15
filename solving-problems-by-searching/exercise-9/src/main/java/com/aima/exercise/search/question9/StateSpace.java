package com.aima.exercise.search.question9;

import java.util.List;

public class StateSpace {
    private final List<State> states;
    public StateSpace(List<State> states) {
        this.states = states;
    }

    public List<State> getStates() {
        return states;
    }

}

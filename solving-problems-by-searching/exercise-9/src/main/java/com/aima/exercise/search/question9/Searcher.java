package com.aima.exercise.search.question9;

public interface Searcher {
    Solution search(State begin, State end, StateSpace stateSpace);
}

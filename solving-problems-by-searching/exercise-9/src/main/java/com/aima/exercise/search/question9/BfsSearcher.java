package com.aima.exercise.search.question9;

import java.util.*;

public class BfsSearcher implements Searcher {
    @Override
    public Solution search(State begin, State end, StateSpace stateSpace) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        State current = begin;
        if (begin.equals(end)) {
            return new Solution(current);
        }
        visited.add(current);
        queue.offer(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            var nextStates = current.getNextStates();
            // System.out.println("From " + current + " has actions " + actions);
            for (State nextState : nextStates) {
                if (nextState.equals(end)) {
                    nextState.setFrom(current);
                    return new Solution(nextState);
                }
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.offer(nextState);
                    nextState.setFrom(current);
                }
            }
        }
        return null;
    }
}

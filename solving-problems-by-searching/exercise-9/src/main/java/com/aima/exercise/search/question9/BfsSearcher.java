package com.aima.exercise.search.question9;

import java.util.*;

public class BfsSearcher implements Searcher {
    @Override
    public Solution search(State begin, State end, StateSpace stateSpace) {
        Queue<State> queue = new LinkedList<>();
        Stack<State> path = new Stack<>();
        Set<State> visited = new HashSet<>();
        State current = begin;
        if (begin.equals(end)) {
            return new Solution(current);
        }
        queue.offer(current);
        path.push(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            var actions = current.getActions();
            // System.out.println("From " + current + " has actions " + actions);
            for (Action action : current.getActions()) {
                var nextState = action.getState();
                nextState.setFrom(current);
                path.push(nextState);
                if (nextState.equals(end)) {
                    return new Solution(nextState);
                }
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.offer(nextState);
                }
            }
        }
        return null;
    }
}

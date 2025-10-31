package it.unibo.generics.graph.impl;

import it.unibo.generics.graph.api.Graph;
import java.util.*;

public class GraphImpl<N> implements Graph<N> {
    private final Map<N, Set<N>> adjacencyMap = new HashMap<>();

    @Override
    public void addNode(N node) {
        if(node == null) return;    
        adjacencyMap.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(N source, N target) {
        if(source == null || target == null) return;
        addNode(source);
        addNode(target);
        adjacencyMap.get(source).add(target);
    }

    @Override
    public Set<N> nodeSet() {
        return Set.copyOf(adjacencyMap.keySet());
    }

    @Override
    public Set<N> linkedNodes(N node) {
        return adjacencyMap.getOrDefault(node, Collections.emptySet());
    }
        
    @Override
    public List<N> getPath(N source, N target) {
        if(source == null || target == null) return Collections.emptyList();
        Set<N> visited = new HashSet<>();
        Map<N, N> predecessorMap = new HashMap<>();
        Queue<N> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);

        while(!queue.isEmpty()) {
            N current = queue.poll();
            if(current.equals(target)) {
                return buildPath(predecessorMap, source, target);
            }
            for(N neighbor : linkedNodes(current)) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessorMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

}
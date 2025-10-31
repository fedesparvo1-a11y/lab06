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
    public List<N> getPath(final N source, final N target) {
        if (source == null || target == null) {
            return List.of();
        }
        final Queue<List<N>> queue = new LinkedList<>();
        final Set<N> visited = new HashSet<>();
        queue.add(List.of(source));

        while (!queue.isEmpty()) {
            final List<N> path = queue.remove();
            final N last = path.get(path.size() - 1);
            if (last.equals(target)) {
                return path;
            }
            if (visited.add(last)) {
                for (final N neighbor : adjacencyMap.getOrDefault(last, Set.of())) {
                    final List<N> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return List.of();
    }
}
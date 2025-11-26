package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * An object that implement a directed graph
 */
public class WorldNetGraph {
    Map<Integer, List<Integer>> adj;
    int edgeCount;

    public WorldNetGraph() {
        adj = new HashMap<>();
        edgeCount = 0;
    }

    /**
     * Add a vertex into the graph. If vertex already exists, do nothing.
     * @param v The index of vertex
     */
    public void addVertex(int v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    /**
     * Add an edge from v to w, make sure v and w are existing
     * @param v The start vertex
     * @param w The end vertex
     */
    public void addEdge(int v, int w) {
        addVertex(v);
        addVertex(w);

        adj.get(v).add(w);
        edgeCount++;
    }

    /**
     * Return the neighbours vertex v have and return an empty list if v is not
     * v does not exist.
     * @param v Vertex to find
     * @return The list of neighbours of v
     */
    public List<Integer> getNeighbors(int v) {
        return adj.getOrDefault(v, Collections.emptyList());
    }

    /**
     * Get all vertices in the graph
     * @return a set of vertices
     */
    public Set<Integer> getAllVertices() {
        return adj.keySet();
    }

    /**
     * Get the number of vertices in the graph
     * @return the total number of vertices
     */
    public int getVertexCount() {
        return adj.size();
    }

    /**
     *  Get the number of edges in the graph
     * @return the total edge number
     */
    public int getEdgeCount() {
        return edgeCount;
    }
}

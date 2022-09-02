package algo;

import graph.*;
import priorityQueue.MinHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;

    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST to edgesMST list.
     * */
    @Override
    public void computeMST() {
        // FILL IN CODE
        // Note: must use a MinHeap and be efficient
        int numNodes = numNodes();
        HashSet<Integer> known = new HashSet<>();
        HashSet<Integer> unknown = new HashSet<>();
        known.add(0);
        for (int i = 1; i < numNodes; i++) {
            unknown.add(i);
        }
        MinHeap table = new MinHeap();
        Edge first = getFirstEdge(0);
        HashMap<Integer, Edge> reflectMapId2Edge = new HashMap<>();
        while (first != null) {
            reflectMapId2Edge.put(first.getId2(), first);
            table.insert(first.getId2(), first.getCost());
            first = first.next();
        }
        while (!unknown.isEmpty()) {
            int tempMin = table.removeMin();
            Edge tempMinEdge = reflectMapId2Edge.get(tempMin);
            known.add(tempMinEdge.getId2());
            unknown.remove(tempMinEdge.getId2());
            reflectMapId2Edge.remove(tempMin);
            addMSTEdge(tempMinEdge);
            // update
            Edge newEdge = getFirstEdge(tempMin);
            while (newEdge != null) {
                if (!known.contains(newEdge.getId2())) {
                    if (reflectMapId2Edge.containsKey(newEdge.getId2())) {
                        if (newEdge.getCost() < reflectMapId2Edge.get(newEdge.getId2()).getCost()) {
                            reflectMapId2Edge.put(newEdge.getId2(), newEdge);
                            table.reduceKey(newEdge.getId2(), newEdge.getCost());
                        }
                        newEdge = newEdge.next();
                    } else {
                        reflectMapId2Edge.put(newEdge.getId2(), newEdge);
                        table.insert(newEdge.getId2(), newEdge.getCost());
                        newEdge = newEdge.next();
                    }
                } else {
                    newEdge = newEdge.next();
                }
            }
        }
    }
}

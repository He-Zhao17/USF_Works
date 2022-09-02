package algo;

import graph.*;
import sets.DisjointSets;

import java.util.ArrayList;
import java.util.Comparator;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm {

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * Compute minimum spanning tree for this graph. Add edges of MST to
     * edgesMST list. Should use Kruskal's algorithm and DisjointSets class.
     */
    @Override
    public void computeMST() {
        // FILL IN CODE
        int numNodes = numNodes();
        DisjointSets DSet = new DisjointSets();
        DSet.createSets(numNodes);
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            Edge temp = getFirstEdge(i);
            while (temp != null) {
                if (temp.getId2() > i) {
                    edges.add(temp);
                }
                temp = temp.next();
            }
        }
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.getCost() > o2.getCost()) {
                    return 1;
                } else if (o1.getCost() == o2.getCost()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        int numQEdges = 0;
        while (numQEdges < numNodes - 1) {
            Edge temp = edges.get(0);
            if (DSet.find(temp.getId1()) != DSet.find(temp.getId2())) {
                addMSTEdge(temp);
                DSet.union(temp.getId1(), temp.getId2());
                numQEdges++;
            }
            edges.remove(0);
        }
    }


    public static void main (String[] args) {
        ArrayList<Integer> k = new ArrayList<>();
        k.add(1);
        k.add(2);
        k.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 == o2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        System.out.println(k);
    }

}


package problem1;

import java.util.List;

/** Problem 1:
 * Consider the following problem: we have N people in some organization,
 * and one of these people might secretly be a "spy".
 * If a "spy" exists, then the following conditions must all hold true:
 * 1. The "spy" does NOT trust anybody
 * 2. Everybody else trusts the "spy".
 *
 * Suppose we represent this problem using a directed graph as follows:
 * - N vertices of the graph (indexed from 0 to N-1) represent people
 * - If there is a directed edge from vertex v1 to vertex v2, it means person v1 trusts person v2.
 *
 * Assume that the graph is represented using the adjacency list.
 * Write a function findSpy() that returns the vertex id of the "spy", if it exists,
 * and -1 otherwise. You can assume there is only one "spy" or no spy.
 *
 * The pdf contains an example - I recommend you take a look.
 * The sample graph has been created in the main method so that you could test your code.
 * Please note, though, that your code should be general, and work on any graph.
 *
 * You are allowed to use a regular array of integers of size N
 * (or two arrays, depending on your algorithm).
 * You may not use any other additional data structures except for the input graph and the extra array(s).
 *
 */
class Graph {
    Edge[] graph; // adjacency list

    static class Edge {
        int neighbor;
        Edge next;

        Edge(int neighbor) {
            this.neighbor = neighbor;
            next = null;
        }
    }

    public Graph(int numVertices) {
        graph = new Edge[numVertices];
    }

    public void addEdge(int vertexId, Edge edge) {
        Edge head = graph[vertexId]; // head of the linked list for this  node
        graph[vertexId] = edge;
        if (head != null) {
            edge.next = head;
        }
    }

    public int findSpy() {
        int res = -1;
        // FILL IN CODE
        int[] InCounter = new int[graph.length];
        int[] OutCounter = new int[graph.length];
        int VNum = 0;
        for (int i = 0; i < graph.length; i++) {
            //Edge temp = graph[i];
            for (Edge temp = graph[i]; temp != null; temp = temp.next) {
                if (temp.neighbor > VNum) {
                    VNum = temp.neighbor;
                }
                if (temp.next.neighbor > VNum) {
                    VNum = temp.next.neighbor;
                }
                InCounter[temp.neighbor]++;
                OutCounter[temp.next.neighbor]++;
            }
        }
        for (int i = 0; i < InCounter.length; i++) {
            if (InCounter[i] == VNum + 1 && OutCounter[i] == 0) {
                return i;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        // This is a graph you can test your code on:
        Graph g = new Graph(5);

        // edges going out of vertex 0
        Edge edge01 = new Edge(1);
        Edge edge02 = new Edge(2);
        g.addEdge(0, edge01);
        g.addEdge(0, edge02);

        // edges going out of vertex 1
        Edge edge10 = new Edge(0);
        Edge edge12 = new Edge(2);
        Edge edge13 = new Edge(3);
        g.addEdge(1, edge10);
        g.addEdge(1, edge12);
        g.addEdge(1, edge13);

        // edges going out of vertex 3
        Edge edge30 = new Edge(0);
        Edge edge32 = new Edge(2);
        Edge edge34 = new Edge(4);

        g.addEdge(3, edge30);
        g.addEdge(3, edge32);
        g.addEdge(3, edge34);

        // edge going out of vertex 4
        Edge edge42 = new Edge(2);
        g.addEdge(4, edge42);

        System.out.println(g.findSpy()); // should print 2
    }
}


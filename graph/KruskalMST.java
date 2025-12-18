/**
 * ======================================================
 * KRUSKAL’S ALGORITHM — MST (INTERVIEW REFERENCE NOTES)
 * ======================================================
 *
 * 1) How Kruskal’s algorithm solves MST (core idea)
 *
 * Kruskal’s algorithm builds a Minimum Spanning Tree by
 * looking at EDGES instead of vertices.
 *
 * Core idea:
 * - Take all edges in the graph
 * - Sort them by increasing weight
 * - Keep adding the cheapest edge that does NOT form a cycle
 *
 * The algorithm does not grow from a starting node.
 * It gradually filters edges until only the necessary
 * connections remain.
 *
 * You can think of it as:
 * “Remove all expensive and redundant connections,
 *  keep only the cheapest ones that still connect everything.”
 *
 *
 * ----------------------------------------
 * 2) Is Kruskal greedy? Why and how?
 * ----------------------------------------
 *
 * Yes, Kruskal’s algorithm is GREEDY.
 *
 * Why greedy:
 * - At each step, it chooses the cheapest available edge
 * - It never reconsiders or replaces past choices
 *
 * How greediness is safe:
 * - The CUT PROPERTY guarantees that the cheapest edge
 *   crossing any cut is always safe to include
 * - Kruskal enforces this by skipping edges that form cycles
 *
 * Each accepted edge connects two previously disconnected
 * components, so no greedy choice is wasted.
 *
 *
 * --------------------------
 * 3) Algorithm Steps
 * --------------------------
 *
 * 1. Collect all edges of the graph
 * 2. Sort edges by increasing weight
 * 3. Initialize each vertex as its own component
 * 4. For each edge in sorted order:
 *    - If the edge connects two different components:
 *        - add it to the MST
 *        - merge the components
 *    - Else:
 *        - skip the edge (cycle prevention)
 * 5. Stop when MST has V − 1 edges
 *
 *
 * ----------------------------------------
 * 4) Common Mistakes & Tricks
 * ----------------------------------------
 *
 * Common mistakes:
 * - Forgetting to check for cycles
 * - Using DFS/BFS instead of Union-Find
 * - Applying Kruskal to directed graphs
 * - Forgetting MST requires exactly V − 1 edges
 * - Not detecting disconnected graphs
 *
 * Tricks to remember:
 * - “Every accepted edge must REDUCE number of components”
 * - If union(u, v) returns false → cycle → skip
 * - Stop early once V − 1 edges are selected
 *
 *
 * ----------------------------------------
 * 5) Time & Space Complexity
 * ----------------------------------------
 *
 * Let:
 *   V = number of vertices
 *   E = number of edges
 *
 * Sorting edges:
 *   O(E log E)
 *
 * Union-Find operations:
 *   Almost O(1) per operation (amortized)
 *   Total ≈ O(E)
 *
 * Overall Time Complexity:
 *   O(E log E)
 *
 * Space Complexity:
 * - Edge list: O(E)
 * - Parent & rank arrays: O(V)
 *
 *
 * ----------------------------------------
 * 6) Tricky Concepts & Edge Cases
 * ----------------------------------------
 *
 * - MST is NOT unique if multiple edges have same weight
 * - Negative edge weights are allowed
 * - Disconnected graph → produces a spanning forest
 * - Self-loops are always ignored
 * - Parallel edges: smallest one wins naturally
 *
 * Important distinction:
 * - Kruskal does NOT find shortest paths
 * - It minimizes TOTAL connection cost only
 *
 *
 * ----------------------------------------
 * Final Intuition to Remember
 * ----------------------------------------
 *
 * Kruskal’s algorithm:
 * - does not grow a tree
 * - does not care about start vertex
 * - does not explore the graph
 *
 * It simply asks, repeatedly:
 * “Is this edge cheap and safe?”
 *
 * If yes → keep it.
 * If no → discard it.
 *
 */

import java.util.*;

class Solution {

    /* --------------------------
     * EDGE REPRESENTATION
     * --------------------------
     * Kruskal is EDGE-CENTRIC.
     * Each edge knows:
     * - u : one endpoint
     * - v : other endpoint
     * - w : weight
     */
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    /* --------------------------
     * DISJOINT SET UNION (DSU)
     * --------------------------
     * DSU tracks connected components efficiently.
     *
     * parent[x] = immediate parent of x
     * rank[x]   = approx height of tree rooted at x
     */
    static class DSU {
        int[] parent;
        int[] rank;

        // Initialize DSU: each node is its own parent
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i; // each node is its own leader initially
                rank[i] = 0;
            }
        }

        /**
         * FIND OPERATION
         *
         * What:
         * - Finds the leader (root) of the component x belongs to.
         *
         * Why:
         * - If two nodes have the same leader, they are already connected.
         *
         * How:
         * - Recursively climb parents until root is found.
         * - Apply PATH COMPRESSION to flatten the structure.
         */
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        /**
         * UNION OPERATION
         *
         * What:
         * - Merges two components if they are different.
         *
         * Why:
         * - Used to safely add an edge without forming a cycle.
         *
         * Returns:
         * - true  → merge happened (edge accepted)
         * - false → already connected (edge rejected)
         */
        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            // Same leader → cycle would form
            if (pa == pb) return false;

            // Union by rank to keep tree shallow
            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pb] < rank[pa]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    /**
     * KRUSKAL'S MST FUNCTION
     *
     * V     → number of vertices
     * edges → list of all edges in the graph
     *
     * Returns:
     * - MST cost if MST exists
     * - -1 if graph is disconnected
     */
    static int kruskal(int V, List<Edge> edges) {

        // 1️⃣ Sort all edges by increasing weight
        edges.sort(Comparator.comparingInt(e -> e.w));

        // 2️⃣ Initialize DSU (each node is its own component)
        DSU dsu = new DSU(V);

        int mstCost = 0;
        int edgesUsed = 0;

        // 3️⃣ Process edges in sorted order
        for (Edge e : edges) {

            // Try to merge the components
            if (dsu.union(e.u, e.v)) {
                mstCost += e.w;
                edgesUsed++;

                // MST complete when V - 1 edges are used
                if (edgesUsed == V - 1) break;
            }
            // else: edge rejected because it forms a cycle
        }

        // 4️⃣ Check if MST exists (graph must be connected)
        if (edgesUsed < V - 1) {
            return -1; // disconnected graph
        }

        return mstCost;
    }

    /* --------------------------
     * MAIN (EXAMPLE USAGE)
     * -------------------------- */
    public static void main(String[] args) {

        int V = 4;
        List<Edge> edges = new ArrayList<>();

        // Undirected graph edges
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(2, 3, 4));

        int result = kruskal(V, edges);

        if (result == -1) {
            System.out.println("MST does not exist (graph disconnected)");
        } else {
            System.out.println("MST cost = " + result);
        }
    }
}

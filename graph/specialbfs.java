/**
Problem Description

Given a weighted undirected graph having A nodes, a source node C and destination node D.

Find the shortest distance from C to D and if it is impossible to reach node D from C then return -1.

You are expected to do it in Time Complexity of O(A + M).

Note:

There are no self-loops in the graph.

No multiple edges between two pair of vertices.

The graph may or may not be connected.

Nodes are Numbered from 0 to A-1.

Your solution will run on multiple testcases. If you are using global variables make sure to clear them.



Problem Constraints

1 <= A <= 105

0 <= B[i][0], B[i][1] < A

1 <= B[i][2] <= 2

0 <= C < A

0 <= D < A



Input Format

The first argument given is an integer A, representing the number of nodes.

The second argument given is the matrix B, where B[i][0] and B[i][1] are connected through an edge of weight B[i][2].

The third argument given is an integer C, representing the source node.

The fourth argument is an integer D, representing the destination node.

Note: B[i][2] will be either 1 or 2.

Output Format

Return the shortest distance from C to D. If it is impossible to reach node D from C then return -1.

*/

/**
 * Dummy Node BFS (Shortest Path with weights {1,2})
 *
 * Explaining the algorithm in simple terms:
 *
 * The idea is to convert a weighted graph into an unweighted graph
 * so that normal BFS can be used to find the shortest path.
 *
 * If an edge has weight 1 → keep it as a single edge.
 * If an edge has weight 2 → split it into two edges of weight 1
 * by inserting an extra (dummy) node in between.
 *
 * After this transformation:
 * - All edges have equal weight (1)
 * - BFS guarantees the shortest path
 *
 * The distance in the transformed graph equals the original shortest distance.
 *
 *
 * How it is different from normal BFS:
 *
 * Normal BFS:
 * - Works only when all edges have equal weight
 * - Directly applied on the original graph
 *
 * Dummy BFS:
 * - First transforms the graph
 * - Uses extra nodes to simulate higher weights
 * - Then applies BFS on the transformed graph
 *
 * BFS is not changed — the graph is.
 *
 *
 * How it is different from 0-1 BFS:
 *
 * 0-1 BFS:
 * - Works only when weights are exactly {0,1}
 * - Uses a deque
 * - Does not change the graph
 *
 * Dummy BFS:
 * - Works when weights are {1,2}
 * - Uses a normal queue
 * - Changes the graph structure
 *
 *
 * Why 0-1 BFS works only for 0-1 weights:
 *
 * 0-1 BFS relies on this property:
 * - Adding a 0-weight edge keeps distance the same
 * - Adding a 1-weight edge increases distance by exactly 1
 *
 * The deque ensures:
 * - Nodes with smaller distances are always processed first
 *
 * If weights are 2:
 * - Distance jumps by 2
 * - Deque ordering breaks
 * - BFS guarantee is lost
 *
 * That’s why 0-1 BFS cannot handle weight = 2.
 *
 *
 * Key observations:
 *
 * - BFS correctness depends on uniform edge weights
 * - Dummy nodes make all edge weights uniform
 * - Graph size increases but BFS still remains linear
 * - Distance in transformed graph = original distance
 * - Early exit is safe in BFS after transformation
 *
 *
 * Common mistakes and tricks to remember:
 *
 * Mistake:
 * - Running BFS directly on weighted graph
 * Trick:
 * - Ask: "Are all edges equal weight?" before using BFS
 *
 * Mistake:
 * - Mixing dummy BFS with 0-1 BFS logic
 * Trick:
 * - Dummy BFS → queue
 * - 0-1 BFS → deque
 *
 * Mistake:
 * - Forgetting to increase graph size
 * Trick:
 * - Dummy nodes count ≤ number of weight-2 edges
 *
 * Mistake:
 * - Using -1 initialization incorrectly
 * Trick:
 * - BFS → use -1
 * - Dijkstra / 0-1 BFS → use +∞
 *
 *
 * Complexities (with calculation):
 *
 * Let:
 * - A = number of original nodes
 * - M = number of edges
 *
 * Worst case:
 * - Every edge has weight 2
 * - Dummy nodes added = M
 *
 * Total nodes = A + M
 * Total edges ≈ 2M
 *
 * Time Complexity:
 * O(A + M)
 *
 * Space Complexity:
 * O(A + M)
 *
 *
 * Similar problems:
 *
 * - Shortest path with edge weights {1,2}
 * - Minimum moves in grid with double-cost transitions
 * - Word ladder with intermediate states
 * - Graph problems requiring O(V+E) instead of Dijkstra
 * - Converting weighted problems to unweighted using state expansion
 *
 */


import java.util.*;

class Solution {

    public int solve(int A, int[][] B, int C, int D) {

        // Step 1: build expanded graph with dummy nodes
        List<List<Integer>> graph = buildGraphWithDummyNodes(A, B);

        int totalNodes = graph.size();
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, -1);

        // Step 2: normal BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(C);
        distance[C] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            // early exit is SAFE in BFS
            if (u == D) return distance[u];

            for (int v : graph.get(u)) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    q.offer(v);
                }
            }
        }

        return -1;
    }

    private List<List<Integer>> buildGraphWithDummyNodes(int A, int[][] B) {

        List<List<Integer>> graph = new ArrayList<>();

        // original nodes
        for (int i = 0; i < A; i++) {
            graph.add(new ArrayList<>());
        }

        int nextDummyNode = A;

        for (int[] e : B) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (w == 1) {
                // normal edge
                graph.get(u).add(v);
                graph.get(v).add(u);
            } else {
                // weight = 2 → introduce dummy node
                graph.add(new ArrayList<>()); // create dummy
                int dummy = nextDummyNode++;

                graph.get(u).add(dummy);
                graph.get(dummy).add(u);

                graph.get(dummy).add(v);
                graph.get(v).add(dummy);
            }
        }

        return graph;
    }
}

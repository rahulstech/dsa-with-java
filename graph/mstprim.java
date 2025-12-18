/**
 * ============================
 * MINIMUM SPANNING TREE (MST)
 * ============================
 *
 * 1) What is MST and what is its purpose? (day-to-day intuition)
 *
 * Imagine you are building something that must connect many points:
 * - cities with roads
 * - computers with network cables
 * - offices with fiber lines
 *
 * Each possible connection has a cost.
 *
 * Your goal is NOT:
 * - fastest route between two points
 * - shortest path from a source
 *
 * Your real goal is:
 * → connect ALL points
 * → with the MINIMUM total cost
 * → without any unnecessary extra connections
 *
 * An MST is exactly that:
 * - "Spanning" → reaches every node
 * - "Tree" → no cycles (no redundant loops)
 * - "Minimum" → cheapest possible total cost
 *
 * If a connection can be removed and everything is still connected,
 * then it was wasteful — MST removes all such waste optimally.
 *
 *
 * ------------------------------------------------
 * 2) How Prim’s algorithm solves MST (core idea)
 * ------------------------------------------------
 *
 * Prim’s algorithm builds the MST gradually.
 *
 * Core idea:
 * Start from ANY node.
 * Repeatedly add the cheapest edge that connects
 * the already-connected part to a new node.
 *
 * It grows the MST like expanding a city’s road network:
 * - you don’t redesign everything
 * - you just attach the nearest unconnected place
 *
 *
 * -------------------------------
 * 3) Why and how Prim is GREEDY
 * -------------------------------
 *
 * Prim is greedy because:
 * - At every step, it makes a LOCAL optimal choice
 * - It does not reconsider past decisions
 *
 * The greedy choice:
 * → pick the minimum weight edge that expands the tree
 *
 * Why this works:
 * Because of the CUT PROPERTY (explained below).
 *
 * Greedy here is safe, not reckless.
 *
 *
 * --------------------------------
 * 4) Cut and Cut Property (simple)
 * --------------------------------
 *
 * A "cut" is just a division of nodes into two groups.
 *
 * Example:
 *   {connected nodes} | {not yet connected nodes}
 *
 * Edges that go between these two groups are called
 * "edges crossing the cut".
 *
 * CUT PROPERTY:
 * Among all edges crossing a cut,
 * the cheapest edge is ALWAYS safe to include in an MST.
 *
 * Why?
 * - The two groups must be connected somehow
 * - Choosing a more expensive edge when a cheaper one exists
 *   is guaranteed waste
 *
 * Prim’s algorithm ALWAYS respects this rule.
 *
 *
 * -------------------------------
 * 5) Prim’s Algorithm Steps
 * -------------------------------
 *
 * 1. Pick any starting vertex
 * 2. Mark it as visited
 * 3. Add all its edges to a priority queue (min-heap)
 * 4. While not all vertices are visited:
 *    - pick the minimum weight edge from the queue
 *    - if it leads to an unvisited vertex:
 *        - include the edge in MST
 *        - mark the vertex visited
 *        - add its outgoing edges to the queue
 *
 * Key invariant:
 * The priority queue always holds edges crossing the cut
 * (visited → unvisited).
 *
 *
 * -----------------------------------------
 * 6) Common mistakes & tricks to avoid them
 * -----------------------------------------
 *
 * Mistakes:
 * - Forgetting MST works only on UNDIRECTED graphs
 * - Confusing Prim with Dijkstra (similar structure, different goal)
 * - Not checking visited → cycles sneak in
 * - Assuming MST exists in disconnected graphs
 *
 * Tricks:
 * - Ask one question before adding an edge:
 *   "Does this edge bring a NEW vertex into the tree?"
 *
 * - Remember:
 *   Dijkstra → shortest path from source
 *   Prim → cheapest way to connect everything
 *
 *
 * -----------------------------
 * 7) Time & Space Complexity
 * -----------------------------
 *
 * Using adjacency list + PriorityQueue:
 *
 * Each edge can be pushed into the queue once:
 *   O(E log E) ≈ O(E log V)
 *
 * Visiting each vertex once:
 *   O(V)
 *
 * Total Time:
 *   O(E log V)
 *
 * Space:
 * - Graph storage: O(V + E)
 * - Priority queue: O(E)
 *
 *
 * ----------------------------------------
 * 8) Tricky concepts related to MST & Prim
 * ----------------------------------------
 *
 * - MST is NOT unique if multiple edges have same weight
 * - Negative edge weights are allowed
 * - MST does NOT define shortest paths
 * - Disconnected graph → MST does NOT exist (gives forest)
 * - Starting node does NOT affect total MST cost
 *
 * Mental model to keep forever:
 * "MST is the cheapest way to connect everything,
 *  and Prim is a disciplined way to grow that connection."
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    void solve(List<List<int[]>> graph, int source) {
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[0],b[0])
        );
        int mst = 0;
        int visitedCount = 0;

        visited[source] = true;
        visitedCount++;
        for (int[] e : graph.get(source)) {
            pq.offer(e);
        }
        
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int w = top[0];
            int u = top[1];
            if (visited[u]) continue;
            visited[u] = true;
            visitedCount++;
            mst += w;
            for (int[] e : graph.get(u)) {
                // NOTE: must check unvisited otherwise cycle will exist in MST
                if (!visited[e[1]]) {
                    pq.offer(e);
                }
            }
        }
        if (visitedCount < graph.size()) {
            System.out.println("invlid graph");
        }
        else {
            System.out.println(mst);
        }
        
    }

    public static void main(String[] args) {
        // List<List<int[]>> graph = Arrays.asList(
        //     Arrays.asList(
        //         new int[]{1,1}, new int[]{4,2}
        //     ),

        //     Arrays.asList(
        //         new int[]{1,0}, new int[]{2,3}
        //     ),

        //     Arrays.asList(
        //         new int[]{4,0}, new int[]{3,3}
        //     ),

        //     Arrays.asList(
        //         new int[]{2,1}, new int[]{3,2}
        //     )
        // );
        // int source = 0;

        // 0 --(10)-- 1
        // |  \       |
        // (1) (-5)  (2)
        // |     \    |
        // 2 --(3)-- 3    4 (isolated)
        List<List<int[]>> graph = Arrays.asList(
            Arrays.asList(
                new int[]{10,1}, new int[]{1,2}, new int[]{-5,3}
            ),

            Arrays.asList(
                new int[]{10,0}, new int[]{2,3}
            ),

            Arrays.asList(
                new int[]{1,0}, new int[]{3,3}
            ),

            Arrays.asList(
                new int[]{-5,0}, new int[]{2,1}, new int[]{3,2}
            ),

            Arrays.asList()
        );
        int source = 0;
        new Solution().solve(graph, source);
    }
}
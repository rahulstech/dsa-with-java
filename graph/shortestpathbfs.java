/**
 * What is Shortest Path BFS (Unweighted Graph)
 *
 * It is an algorithm to find the shortest distance from a source node
 * to all other nodes in a graph where every edge has the same cost
 * (usually cost = 1).
 *
 * BFS works because it explores the graph level by level:
 * first all nodes at distance 1, then distance 2, then distance 3, etc.
 * The first time a node is reached, that path is guaranteed to be the shortest.
 *
 * Applicable to:
 * - Unweighted graphs
 * - Graphs where all edges have equal weight
 * - Directed or undirected graphs
 *
 *
 * What are the main constraints and limitations
 *
 * - DOES NOT work for weighted graphs (even if weights are small like 1 and 2)
 * - Cannot handle negative or positive weights
 * - If edge weights differ, BFS may lock in a suboptimal path
 *
 * When NOT to use:
 * - If edge weights vary → use Dijkstra or 0–1 BFS
 * - If negative weights exist → use Bellman-Ford
 *
 *
 * Steps:
 *
 * 1. Initialize distance array
 *    - Set all distances to -1 (or INF)
 *    - Set distance[source] = 0
 *
 *    Why:
 *    - -1 clearly marks unvisited nodes
 *    - Distance represents shortest edges count from source
 *
 * 2. Push source into a queue
 *
 *    Why:
 *    - BFS processes nodes in increasing distance order
 *
 * 3. While queue is not empty
 *    - Pop front node u
 *    - For every neighbor v of u:
 *        - If v is unvisited (distance[v] == -1):
 *            - distance[v] = distance[u] + 1
 *            - push v into queue
 *
 *    Why:
 *    - First visit to a node is guaranteed to be the shortest path
 *
 * 4. End
 *    - distance[] now contains shortest distance from source
 *    - Unreachable nodes remain -1
 *
 *
 * Common mistakes and tricks to avoid them
 *
 * 1. Using BFS for weighted graphs
 *    Mistake:
 *    - Applying BFS when edge weights are not equal
 *
 *    Trick:
 *    - Always ask: “Are all edge weights the same?”
 *
 *
 * 2. Using visited[] and distance[] incorrectly
 *    Mistake:
 *    - Marking visited too early or mixing visited with distance updates
 *
 *    Trick:
 *    - Use distance[] as both distance and visited marker
 *      (distance != -1 means visited)
 *
 *
 * 3. Forgetting disconnected components
 *    Mistake:
 *    - Assuming all nodes are reachable
 *
 *    Trick:
 *    - Leave unreachable nodes as -1
 *
 *
 * 4. Re-visiting nodes
 *    Mistake:
 *    - Updating a node’s distance more than once
 *
 *    Trick:
 *    - Only update when distance[node] == -1
 *
 *
 * Complexities (with calculation)
 *
 * Time Complexity: O(V + E)
 *
 * Why:
 * - Each vertex is enqueued and dequeued at most once → O(V)
 * - Each edge is checked exactly once (directed) or twice (undirected) → O(E)
 *
 * Total = O(V + E)
 *
 *
 * Space Complexity: O(V)
 *
 * Why:
 * - Distance array → O(V)
 * - Queue in worst case holds all vertices → O(V)
 * - Graph storage (adjacency list) → O(V + E) (usually not counted in algorithm space)
 *
 */

import java.util.*;

class Solution { 

    
    void solve(List<List<Integer>> graph, int source) {
        int[] distances = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        Arrays.fill(distances, -1);
        distances[source] = 0;

        queue.offer(source);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int n : graph.get(v)) {
                if (distances[n] == -1) {
                    distances[n] = distances[v]+1;
                    queue.offer(n);
                }
            }
        }
        System.out.println(Arrays.toString(distances));
    }

    public static void main(String[] args) {

        // List<List<Integer>> graph = Arrays.asList(
        //     Arrays.asList(1),
        //     Arrays.asList(2),
        //     Arrays.asList(3),
        //     Arrays.asList()
        // );
        // int source = 0;

        List<List<Integer>> graph = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(2),
            Arrays.asList(3),
            Arrays.asList(4),
            Arrays.asList(0)
        );
        int source = 0;

        new Solution().solve(graph, source);

    }
}
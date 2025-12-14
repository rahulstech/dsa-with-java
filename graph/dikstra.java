/**
 * Dijkstra's Algorithm (Single Source Shortest Path)
 *
 * What is Dijkstra's Algorithm?
 * Dijkstra’s algorithm finds the shortest distance from a single source
 * node to all other nodes in a weighted graph.
 * It works by progressively finalizing the shortest distance to nodes,
 * starting from the source.
 *
 * The algorithm guarantees correctness only when all edge weights
 * are non-negative.
 *
 * Why is it greedy?
 * - At each step, it selects the unprocessed node with the smallest
 *   known distance from the source.
 * - This choice is greedy because once a node is selected, its shortest
 *   distance is final and will never be improved later.
 * - This works because all edge weights are non-negative, so paths only
 *   get longer as we move forward.
 *
 * Limitations:
 * - Does NOT work with negative edge weights.
 * - Fails on graphs with negative cycles.
 * - Cannot detect negative cycles.
 * - Less efficient than BFS on unweighted graphs.
 *
 * Steps of the algorithm:
 * 1. Initialize distance array:
 *    - distance[source] = 0
 *    - distance[all other nodes] = +∞
 *
 * 2. Use a priority queue (min-heap) storing:
 *    - (currentDistance, node)
 *
 * 3. While the priority queue is not empty:
 *    - Extract the node with the smallest distance.
 *    - If this distance is outdated (greater than stored distance),
 *      ignore it.
 *    - For each outgoing edge (u → v) with weight w:
 *        - Try relaxation:
 *          if distance[u] + w < distance[v]
 *          update distance[v] and push (distance[v], v) into the heap.
 *
 * 4. After the loop:
 *    - distance[] contains the shortest paths from source.
 *
 * Common mistakes:
 * - Using Dijkstra on graphs with negative weights.
 * - Forgetting to ignore outdated entries in the priority queue.
 * - Initializing distances with 0 instead of +∞.
 * - Confusing BFS logic with Dijkstra logic.
 * - Trying to "update" elements inside the priority queue
 *   instead of pushing a new entry.
 *
 * Complexities (with calculation):
 * Let V = number of vertices, E = number of edges.
 *
 * - Each node can be inserted into the priority queue multiple times,
 *   but at most once per relaxation: O(E log V)
 * - Each edge is relaxed once: O(E)
 *
 * Total Time Complexity:
 *   O(E log V)
 *
 * Space Complexity:
 * - Distance array: O(V)
 * - Priority queue: O(V)
 * - Graph storage: O(V + E)
 *
 * Total Space Complexity:
 *   O(V + E)
 */

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    // [weight, node]
    List<List<int[]>> weightedGraph;
    int[] distances;

    // [distance, node]
    PriorityQueue<int[]> pq;

    void solve(List<List<int[]>> graph, int source) {
        this.weightedGraph = graph;
        distances = new int[graph.size()];
        pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[0], b[0]) // priority = current node distance from source
        );

        // initially all nodes are unreachable therefore distances are +ve infinite.
        // initializaton distances with -ve value is dengarous. other the
        // relaxation calculation ( dis(u) + cost(u,v) < dis(v)) will be always error
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        pq.add(new int[]{0,source});
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int disU = top[0];
            int u = top[1];
            if (distances[u] != disU) {
                // distance of the u node updated, but old node still exists in heap
                // so ignore it
                continue;
            }
            List<int[]> edgesWithWeight = weightedGraph.get(u);
            for(int[] weightAndNode : edgesWithWeight) {
                int cost = weightAndNode[0]; // cost(u,v)
                int v = weightAndNode[1]; // v
                int relaxation = disU + cost; // dis(u) + cost(u,v)
                if (relaxation < distances[v]) {
                    // if shorter distance found for v, 
                    // then update the distance of v and push into the heap
                    distances[v] = relaxation;
                    pq.add(new int[]{relaxation, v});
                }
            }
        }

        System.out.println(Arrays.toString(distances));
    }

    public static void main(String[] args) {
        // [distance, node]
        // List<List<int[]>> graph = Arrays.asList(
        //     // 1
        //     Arrays.asList(
        //         new int[]{2,1},
        //         new int[]{4,2}
        //     ),

        //     // 2
        //     Arrays.asList(
        //         new int[]{2,2},
        //         new int[]{1,3}
        //     ),

        //     // 3
        //     Arrays.asList(
        //         new int[]{4,0},
        //         new int[]{2,1},
        //         new int[]{2,4}
        //     ),

        //     // 4
        //     Arrays.asList(
        //         new int[]{1,1},
        //         new int[]{1,4},
        //         new int[]{5,5}
        //     ),

        //     // 5
        //     Arrays.asList(
        //         new int[]{2,2},
        //         new int[]{1,3},
        //         new int[]{4,5}
        //     ),

        //     // 6
        //     Arrays.asList(
        //         new int[]{5,3},
        //         new int[]{4,4}
        //     )
        // );
        // int source = 0;

        // List<List<int[]>> graph = Arrays.asList(
        //     // 0
        //     Arrays.asList(
        //         new int[]{10, 1},
        //         new int[]{1, 2}
        //     ),
        //     // 1
        //     Arrays.asList(
        //         new int[]{1, 3}
        //     ),
        //     // 2
        //     Arrays.asList(
        //         new int[]{1, 3}
        //     ),
        //     // 3
        //     Arrays.asList()
        // );
        // int source = 0;

        List<List<int[]>> graph = Arrays.asList(
            // 0
            Arrays.asList(
                new int[]{9, 4},
                new int[]{7, 3},
                new int[]{1, 1},
                new int[]{1, 5}
            ),

            // 1
            Arrays.asList(
                new int[]{1, 2}
            ),

            // 2
            Arrays.asList(
                new int[]{1, 5},
                new int[]{5, 4}
            ),

            // 3
            Arrays.asList(
                new int[]{6, 4}
            ),

            // 4
            Arrays.asList(
                new int[]{7, 5}
            ),

            // 5
            Arrays.asList()
        );
        int source = 4;

        new Solution().solve(graph, source);
    }
}
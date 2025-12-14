/**
 * What is 0–1 BFS Shortest Path Algorithm
 *
 * 0–1 BFS is a shortest path algorithm used to find the minimum distance
 * from a source node to all other nodes in a graph where edge weights are
 * restricted to only 0 or 1.
 *
 * It is an optimization over Dijkstra’s algorithm that replaces the
 * priority queue with a deque, allowing the algorithm to run in linear time.
 *
 * The key idea:
 * - Edges with weight 0 should be explored immediately
 * - Edges with weight 1 should be explored after all current 0-cost paths
 *
 *
 * What are the constraints
 *
 * - Edge weights must be only 0 or 1
 * - No negative weights
 * - Works for both directed and undirected graphs
 *
 * If weights are:
 * - All 1 → use BFS
 * - 0 or 1 → use 0–1 BFS
 * - Any non-negative → use Dijkstra
 * - Negative → use Bellman-Ford
 *
 *
 * How it differs from BFS (unweighted)
 *
 * BFS:
 * - Assumes every edge has equal cost (usually 1)
 * - Uses a normal queue
 * - Distance increases strictly by levels
 *
 * 0–1 BFS:
 * - Allows two different edge costs: 0 and 1
 * - Uses a deque instead of a queue
 * - Distance does not strictly follow levels
 *
 *
 * How it differs from Dijkstra’s Algorithm
 *
 * Dijkstra:
 * - Works with arbitrary non-negative weights
 * - Uses a priority queue (min-heap)
 * - Time complexity: O(E log V)
 *
 * 0–1 BFS:
 * - Works only with weights 0 or 1
 * - Uses a deque instead of a heap
 * - Time complexity: O(V + E)
 *
 * Conceptually:
 * 0–1 BFS is Dijkstra optimized for weights {0,1}
 *
 *
 * Steps:
 *
 * 1. Initialize distance array
 *    - Set all distances to +∞
 *    - Set distance[source] = 0
 *
 *    Why:
 *    - Allows relaxation-based shortest path logic
 *
 * 2. Initialize a deque
 *    - Push source node to the front
 *
 *    Why:
 *    - Source has the smallest possible distance
 *
 * 3. While deque is not empty:
 *    - Pop node u from the front
 *    - For each edge (u → v) with weight w:
 *        - If distance[u] + w < distance[v]:
 *            - Update distance[v]
 *            - If w == 0 → push v to front
 *            - If w == 1 → push v to back
 *
 *    Why:
 *    - Maintains increasing distance order without a priority queue
 *
 * 4. End
 *    - Distance array now contains shortest distances
 *
 *
 * Common mistakes and tricks to remember
 *
 * 1. Using BFS for weighted graphs
 *    Mistake:
 *    - Applying BFS when edges have weight 0
 *
 *    Trick:
 *    - If any edge has weight 0, BFS is invalid
 *
 *
 * 2. Using a priority queue instead of a deque
 *    Mistake:
 *    - Turning the solution into Dijkstra accidentally
 *
 *    Trick:
 *    - Remember: 0 goes to front, 1 goes to back
 *
 *
 * 3. Using visited[] array
 *    Mistake:
 *    - Marking nodes visited too early
 *
 *    Trick:
 *    - Use distance[] to control relaxations
 *
 *
 * 4. Forgetting stale entries
 *    Mistake:
 *    - Processing outdated nodes from deque
 *
 *    Trick:
 *    - Optional check: skip if current distance > stored distance
 *
 *
 * 5. Applying 0–1 BFS to weights > 1
 *    Mistake:
 *    - Assuming small weights still work
 *
 *    Trick:
 *    - Only weights exactly 0 or 1 are allowed
 *
 *
 * Complexities (with calculation)
 *
 * Time Complexity: O(V + E)
 *
 * Why:
 * - Each vertex is pushed into deque at most a limited number of times
 * - Each edge is relaxed at most once for an improving distance
 *
 * Space Complexity: O(V + E)
 *
 * Why:
 * - Distance array → O(V)
 * - Deque → O(V)
 * - Graph storage → O(V + E)
 *
 *
 * Tricky conceptual interview questions
 *
 * 1. Why does 0–1 BFS work without a priority queue?
 *    - Because weights are restricted to 0 or 1, deque ordering preserves
 *      increasing distance order.
 *
 * 2. Can 0–1 BFS handle negative weights?
 *    - No. Any negative weight breaks shortest path guarantees.
 *
 * 3. When does BFS become Dijkstra?
 *    - BFS is Dijkstra where all edge weights are equal.
 *
 * 4. When does Dijkstra become 0–1 BFS?
 *    - When weights are limited to 0 or 1.
 *
 * 5. What algorithm works for weights up to K?
 *    - Dial’s Algorithm.
 *
 */
import java.util.*;

class Solution {

    static final int WEIGHT_ONE = 1;
    static final int WEIGHT_ZERO = 0;
    
    void solve(List<List<int[]>> graph, int source, int dest) {
        int[] distances = new int[graph.size()];
        Deque<int[]> dq = new LinkedList<>();

        // IMP: initialize will +Inf (like Dikstra) not -1 (like BFS unweighted)
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        dq.addFirst(new int[]{0,source});
        while(!dq.isEmpty()) {
            int[] first = dq.pollFirst();
            int d = first[0];
            int v = first[1];
            if (d == dest) break;
            if (d != distances[v]) {
                continue;
            }
            for (int[] weightAndNode : graph.get(v)) {
                int w = weightAndNode[0];
                int n = weightAndNode[1];
                if (distances[v]+w < distances[n]) {
                    distances[n] = distances[v]+w;
                    int[] entry = new int[]{distances[n],n};
                    if (w == WEIGHT_ONE) {
                        dq.addLast(entry);
                    }
                    else {
                        dq.addFirst(entry);
                    }
                }
            }
        }

        System.out.println(Arrays.toString(distances));
    }

    public static void main(String[] args) {  
        // List<List<int[]>> graph = Arrays.asList(
        //     // [weight, node]

        //     // 0
        //     Arrays.asList(
        //         new int[]{0,1},
        //         new int[]{1,2}
        //     ),

        //     // 1
        //     Arrays.asList(
        //         new int[]{0,2},
        //         new int[]{1,3}
        //     ),

        //     // 2
        //     Arrays.asList(
        //         new int[]{0,3}
        //     ),

        //     // 3
        //     Arrays.asList()
        // );
        // int source = 0;

        // List<List<int[]>> graph = Arrays.asList(
        //     // [weight, node]

        //     // 0
        //     Arrays.asList(
        //         new int[]{0,1},
        //         new int[]{1,2}
        //     ),

        //     // 1
        //     Arrays.asList(
        //         new int[]{1,2}
        //     ),

        //     // 2
        //     Arrays.asList()
        // );
        // int source = 0;

        // 0 → 1 (1)
        // 0 → 2 (0)
        // 2 → 1 (0)
        // 1 → 3 (1)
        // 2 → 3 (1)
        List<List<int[]>> graph = Arrays.asList(
            // [weight, node]

            // 0
            Arrays.asList(
                new int[]{1,1},
                new int[]{0,2}
            ),

            // 1
            Arrays.asList(
                new int[]{1,3}
            ),

            // 2
            Arrays.asList(
                new int[]{0,1},
                new int[]{1,3}
            ),

            // 3
            Arrays.asList()
        );
        int source = 0;
        int dest = 2;


        new Solution().solve(graph, source,dest);
    }
}
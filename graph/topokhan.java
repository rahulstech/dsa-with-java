/**
 * Kahn's Algorithm for Topological Sorting
 *
 * What is Kahn's Algorithm?
 * Kahn's algorithm is a BFS-based greedy algorithm used to produce a
 * topological ordering of a Directed Acyclic Graph (DAG).
 * It repeatedly removes nodes with zero incoming edges (indegree = 0)
 * and updates the graph until all nodes are processed or a cycle is detected.
 *
 * In what types of graph is it applicable?
 * - Directed graphs only
 * - Graph must be acyclic (DAG) to produce a valid ordering
 * - Works on disconnected graphs as well
 * - Not applicable to undirected graphs
 *
 * Why is it greedy?
 * - At every step, the algorithm chooses a node with indegree = 0,
 *   meaning all its dependencies are already satisfied.
 * - When multiple such nodes exist, choosing the smallest node
 *   (using a min-heap) ensures the lexicographically smallest result.
 * - Once a node is chosen, the decision is never revisited.
 *
 * Steps of the algorithm:
 * 1. Compute indegree of every node.
 * 2. Insert all nodes with indegree = 0 into a queue
 *    (use PriorityQueue for lexicographically smallest order).
 * 3. While the queue is not empty:
 *    - Remove a node from the queue and add it to the result.
 *    - For each outgoing edge from this node:
 *        - Reduce indegree of the neighbor.
 *        - If indegree becomes 0, add neighbor to the queue.
 * 4. If total processed nodes < total nodes, a cycle exists.
 *
 * Common mistakes:
 * - Using DFS instead of Kahn's algorithm for lexicographically smallest order.
 * - Forgetting to decrement indegree of neighbors.
 * - Using a normal queue instead of a priority queue when lexicographic order is required.
 * - Assuming input is always a DAG and skipping cycle detection.
 * - Treating topological sort as per-component instead of global.
 *
 * Complexity (with calculation):
 * Let V = number of vertices, E = number of edges.
 *
 * - Building indegree array: O(E)
 * - Each node is pushed and popped once from the queue: O(V log V)
 *   (log V due to priority queue)
 * - Each edge is processed exactly once: O(E)
 *
 * Total Time Complexity:
 *   O(E + V log V)
 *
 * Space Complexity:
 * - Graph storage: O(V + E)
 * - Indegree array: O(V)
 * - Priority queue: O(V)
 *
 * Total Space Complexity:
 *   O(V + E)
 */

import java.util.*;

class Solution {

    void solve(List<List<Integer>> graph) {
        int[] indegree = buildInDegree(graph);
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i=0; i<indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            int n = queue.poll();
            // following lines are unnecessary because 
            // only vertices with indegree 0 are present 
            // in the heap, therefore indegree of n is always 0
            // i can directly add this to the final ans
            // if (indegree[n] == 0) {
            //     ans.add(n);
            // }
            ans.add(n);
            for (int e : graph.get(n)) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    queue.add(e);
                }
            }
        }

        if (ans.size() < graph.size()) {
            System.out.println("cycle exists");
        }
        else {
            System.out.println(ans);
        }
    }

    int[] buildInDegree(List<List<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        for (List<Integer> edges : graph) {
            for (int e : edges) {
                indegree[e]++;
            }
        }
        return indegree;
    }

    public static void main(String[] args) {
        // List<List<Integer>> graph = Arrays.asList(
        //     Arrays.asList(1,2),
        //     Arrays.asList(3),
        //     Arrays.asList(),
        //     Arrays.asList()
        // );

        // List<List<Integer>> graph = Arrays.asList(
        //     Arrays.asList(2),
        //     Arrays.asList(2),
        //     Arrays.asList(3),
        //     Arrays.asList()
        // );

        List<List<Integer>> graph = Arrays.asList(
            Arrays.asList(1),   // 0 → 1
            Arrays.asList(2),   // 1 → 2
            Arrays.asList(3),   // 2 → 3
            Arrays.asList(1)    // 3 → 1  (cycle here)
        );


        new Solution().solve(graph);
    }
}
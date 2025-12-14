/**
 * Topological Sort (Directed Graph)
 *
 * Definition:
 * A topological ordering of a directed graph is a linear ordering of vertices
 * such that for every directed edge u → v, u appears before v in the ordering.
 *
 * Preconditions:
 * - Graph must be a Directed Acyclic Graph (DAG)
 * - If a cycle exists, topological sorting is not possible
 *
 * Algorithms:
 *
 * 1. DFS (Postorder-based)
 *    - Perform DFS from every unvisited node
 *    - Detect cycles using a recursion stack (inStack[])
 *    - Add a node to result after all its neighbors are processed
 *    - Reverse the postorder traversal to obtain topological order
 *
 * 2. Kahn’s Algorithm (BFS-based)
 *    - Compute indegree of each node
 *    - Push all nodes with indegree 0 into a queue
 *    - Repeatedly remove nodes and reduce indegree of neighbors
 *    - If all nodes are processed, topo sort exists; otherwise, a cycle exists
 *
 * Cycle Detection:
 * - DFS approach: detect back-edge using recursion stack
 * - Kahn’s approach: if processed nodes < total nodes, a cycle exists
 *
 * Time Complexity:
 * - O(V + E)
 *
 * Space Complexity:
 * - O(V + E) for adjacency list
 * - O(V) for visited, recursion stack, and result list
 */

import java.util.*;

// Post Order DFS
class Solution {

    List<List<Integer>> graph;
    boolean[] visited;
    boolean[] inStack;
    List<Integer> topo;

    void solve(List<List<Integer>> graph) {
        this.graph = graph;
        int n = graph.size();

        visited = new boolean[n];
        inStack = new boolean[n];
        topo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i)) {
                    System.out.println("graph contains cycle");
                    return;
                }
                else {
                    // NOTE: if a depends on b then in postorder b appears first then b
                    // but in topological sort i want the dependent (here b) first then
                    // the dependency (here a). therefore the REVERSE is important. 
                    Collections.reverse(topo);
                    System.out.println(topo);
                }
            }
        }
    }

    boolean dfs(int v) {
        visited[v] = true;
        inStack[v] = true;
        for (int nxt : graph.get(v)) {
            if (!visited[nxt]) {
                if (dfs(nxt)) return true;
            } else if (inStack[nxt]) {
                return true;
            }
        }

        inStack[v] = false;
        topo.add(v); // postorder
        return false;
    }

    public static void main(String[] args) {
        // 0 → 1
        // 0 → 2
        // 1 → 3
        // 2 → 3
        // 2 → 4
        // 3 → 5
        // 4 → 5
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 2));   // 0
        graph.add(Arrays.asList(3));      // 1
        graph.add(Arrays.asList(3, 4));   // 2
        graph.add(Arrays.asList(5));      // 3
        graph.add(Arrays.asList(5));      // 4
        graph.add(Arrays.asList());       // 5

        new Solution().solve(graph);
    }
}

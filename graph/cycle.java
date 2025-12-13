import java.util.Arrays;
import java.util.List;


/**
 * Cycle detection in undirected graph
 */

class Solution {

    boolean[] visited;
    List<List<Integer>> graph;

    void solve(List<List<Integer>> graph) {
        this.graph = graph;
        visited = new boolean[graph.size()];
        int start = 3;

        boolean ans = dfs(start,-1);
        System.out.println(ans);
    }

    boolean dfs(int vertex, int parent) {
        visited[vertex] = true;
        for (int e : graph.get(vertex)) {
            if (!visited[e]) {
                if (dfs(e,vertex)) {
                    return true;
                }
            }
            else if (e != parent){
               return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        //  1 — 2
        //  |   |
        //  4 — 3
        // List<List<Integer>> graph = Arrays.asList(
        //     Arrays.asList(),
        //     Arrays.asList(4),
        //     Arrays.asList(3),
        //     Arrays.asList(2,4),
        //     Arrays.asList(1,3)
        // );

        // no cycle
        // List<List<Integer>> graph = Arrays.asList(
        //     Arrays.asList(),
        //     Arrays.asList(4),
        //     Arrays.asList(3),
        //     Arrays.asList(2,4),
        //     Arrays.asList(1,3)
        // );

        List<List<Integer>> graph = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(2,3,4),
            Arrays.asList(1,3),
            Arrays.asList(1,2,4,5),
            Arrays.asList(1,3,5),
            Arrays.asList(3,4)
        );


        new Solution().solve(graph);
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// connected components in undirected graph

class Solution {

    int[][] graph;
    boolean[] visited;

    void solve(int[][] graph) {
        this.graph = graph;
        visited = new boolean[graph.length];

        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                List<Integer> path = bfs(i);
                System.err.println(path);
            }
        }
    }

    List<Integer> bfs(int v) {
        ArrayList<Integer> path = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        while(!queue.isEmpty()) {
            int n = queue.poll();
            path.add(n);
            for (int i=0; i<graph.length; i++) {
                if (graph[n][i] == 0) continue;
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {
        // 0 — 1      2 — 3 — 4
        //     \
        //     5
        int[][] graph = new int[][] {
            {0,1,0,0,0,0},
            {1,0,0,0,0,1},
            {0,0,0,1,0,0},
            {0,0,1,0,1,0},
            {0,0,0,1,0,0},
            {0,1,0,0,0,0}
        };
        new Solution().solve(graph);
    }
}
/**
 * Cycle detection in directed graph
 */

import java.util.*;

class Solution {

    int[][] graph;
    boolean[] visited;
    boolean[] stack;

    void solve(int[][] graph, int start) {
        this.graph = graph;
        visited = new boolean[graph.length];
        stack = new boolean[graph.length];

        boolean ans = dfs(start);
        System.out.println("has cycle: "+ans);
    }

    boolean dfs(int v) {
        // Mark node visited
        visited[v] = true;

        // Mark node in recursion stack
        stack[v] = true;

        // Explore neighbors
        for (int i=0; i<graph.length; i++) {
            if ( graph[v][i] == 0) continue;
            if (!visited[i]){
                if (dfs(i)) {
                    return true;
                }
            }
            // If you hit a node already in the stack → you looped
            else if (stack[i]) {
                return true;
            }
        }

        // Remove node from stack when done
        stack[v] = false;

        return false;
    }

    public static void  main(String[] args) {
        // int[][] graph = new int[][] {
        //     new int[]{0,1,0},
        //     new int[]{0,0,1},
        //     new int[]{0,0,0}
        // };

        // [  [1, 2] 
        // [4, 1] 
        // [2, 4] 
        // [3, 4] 
        // [5, 2] 
        // [1, 3] ]

        // int[][] graph = new int[][] {
        //     {1,0,1,0,0},
        //     {0,0,0,1,0},
        //     {0,0,0,1,0},
        //     {0,0,0,0,0},
        //     {0,1,0,0,0},
        // };

        // [  [1, 2]
        // [2, 3] 
        // [3, 4] 
        // [4, 5] ]
        int[][] graph = new int[][] {
            {0,1,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,0},
            {0,0,0,0,1},
            {0,0,0,0,0},
        };

        new Solution().solve(graph,0);
    }
}
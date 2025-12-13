// Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
// Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
// NOTE:
// The cycle must contain atleast two nodes.
// There are no self-loops in the graph.
// There are no multiple edges between two nodes.
// The graph may or may not be connected.
// Nodes are numbered from 1 to A.
// Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
//
// Problem Constraints
// 2 <= A <= 105
// 1 <= M <= min(200000,A*(A-1))
// 1 <= B[i][0], B[i][1] <= A


import java.util.*;

public class Solution {

    List<List<Integer>> graph;
    boolean[] visited;
    boolean[] stack;

    public int solve(int A, int[][] B) {
        graph = buildALGraph(A,B);
        visited = new boolean[A];
        stack = new boolean[A];
        // start from all unvisited node because graph may be disconnected
        // and i need to check all components of the graph
        for (int i=0; i<A; i++) {
            if (!visited[i]) {
                if (dfs(i)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    boolean dfs(int v) {
        visited[v] = true;
        stack[v] = true;
        for(int i : graph.get(v)) {
            if (!visited[i]) {
                if (dfs(i)) {
                    return true;
                }
            }
            else if (stack[i]) {
                return true;
            }
        }
        stack[v] = false;
        return false;
    }

    List<List<Integer>> buildALGraph(int A, int[][] B) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<A; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < B.length; i++) {
            int s = B[i][0]-1;
            int e = B[i][1]-1;
            graph.get(s).add(e);
        }
        return graph;
    }

    public static void main(String[] args) {
        // cycle present here
        // int A = 5;
        // int[][] B = new int[][] {
        //     {1,2},
        //     {4,1},
        //     {2,4},
        //     {3,4},
        //     {5,2},
        //     {1,3}
        // };

        // no cycle
        int A = 5;
        int[][] B = new int[][]{ 
            { 1, 2 },
            { 2, 3 }, 
            { 3, 4 }, 
            { 4, 5 }
        };
        int ans = new Solution().solve(A, B);
        System.out.println(ans);
    }
}

import java.util.*;

/**
 * Time Complexity = O(V+E)
 * Space Complexity = O(V)
 * Explaing: SC visited set and in worst case dfs(int) will be called along lognest path will all nodes
 */
class Solution {

    ArrayList<Integer> ans;
    HashSet<Integer> visited;
    List<List<Integer>> graph;

    List<Integer> solve(List<List<Integer>> graph, int start) {
        this.graph = graph;
        visited = new HashSet<>();
        ans = new ArrayList<>();

        visited.add(start);
        dfs(start);

        return ans;
    }

    void dfs(int v) {
        for(int e : graph.get(v)) {
            if (!visited.contains(e)) {
                visited.add(e);
                dfs(e);
            }
        }
        ans.add(v);
    }


    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(2));
        graph.add(Arrays.asList(3, 4));
        graph.add(Arrays.asList());
        graph.add(Arrays.asList(0));
        
        List<Integer> ans = new Solution().solve(graph, 2);
        System.out.println(ans);
    }
}
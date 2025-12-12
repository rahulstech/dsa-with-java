import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(Arrays.asList(1, 3));   // neighbors of 0
        graph.add(Arrays.asList(2));      // neighbors of 1
        graph.add(Arrays.asList(3, 4));   // neighbors of 2
        graph.add(Arrays.asList());       // neighbors of 3
        graph.add(Arrays.asList(0));      // neighbors of 4

        int source = 0;
        queue.offer(source);
        visited.add(source); // NOTE: mark visited when enqueued

        while(!queue.isEmpty()) {
            int v = queue.poll();
            ans.add(v);
            List<Integer> edges = graph.get(v);
            for (int e : edges) {
                if (!visited.contains(e)) {
                    visited.add(e);
                    queue.offer(e);
                }
            }
        }

        System.out.println(ans);
    }
}
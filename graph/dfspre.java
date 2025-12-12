import java.util.*;

class Solution {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> preOrder = new ArrayList<>();

        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 3));
        graph.add(Arrays.asList(2));
        graph.add(Arrays.asList(3, 4));
        graph.add(Arrays.asList());
        graph.add(Arrays.asList(0));

        // List<List<Integer>> graph = new ArrayList<>();
        // graph.add(Arrays.asList(1, 2));   // 0
        // graph.add(Arrays.asList(3));      // 1
        // graph.add(Arrays.asList(3));      // 2
        // graph.add(Arrays.asList());       // 3

        stack.push(2);
        visited.add(2);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            preOrder.add(v);
            for (int e : graph.get(v)) {
                if (!visited.contains(e)) {
                    visited.add(e);   // mark early
                    stack.push(e);    // dive deeper
                }
            }
        }

        System.out.println("pre-order "+preOrder);
    }
}

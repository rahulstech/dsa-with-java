import java.util.*;

class Solution {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> postOrder = new ArrayList<>();

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
            int v = stack.peek();

            boolean foundChild = false;
            for (int e : graph.get(v)) {
                if (!visited.contains(e)) {
                    visited.add(e);   // mark early
                    stack.push(e);    // dive deeper
                    foundChild = true;
                    break;
                }
            }

            if (!foundChild) {
                // post-order: add the node only when there is no unvisited child
                postOrder.add(stack.pop()); 
            }
        }

        System.out.println("post-order "+postOrder);
    }
}

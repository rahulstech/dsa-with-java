import java.util.*;

class Node {

    int arrival;
    int duration;
    int index;

    Node(int arrival, int duration, int index) {
        this.arrival = arrival;
        this.duration = duration;
        this.index = index;
    }
}

class Solution {

    // job[0] = arrival
    // job[1] = duration
    List<Integer> solve(int[][] jobs) {
        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {

            @Override
            public int compare(Node l, Node r) {
                return Integer.compare(l.duration, r.duration);
            }
        });
        int i=0;
        for (int[] job : jobs) {
            heap.add(new Node(job[0],job[1],i));
            i++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(!heap.isEmpty()) {
            Node n = heap.poll();
            ans.add(n.index);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] jobs = {
            new int[]{0, 3},
            new int[]{1, 9},
            new int[]{2, 6}
        };
        List<Integer> ans = new Solution().solve(jobs);
        System.out.println(ans);
    }
}
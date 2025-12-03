import java.util.*;

class Solution {

    public static void main(String[] args) {
        
        int[] array = new int[]{3, 2, 1, 5, 6, 4};
        int k = 3;
        
        // kth largest and first k large numbers
        
        // priority queue is by default a min-heap
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int x : array) {
            q.add(x);
            if (q.size() > k) {
                q.poll();
            }
        }
        System.out.println("K th largest element: "+q.peek());
        System.out.println("First K large elements: "+q);


        // using a custom comparator to reverse the item comparison.
        // now this PriorityQueue will act as max-heap
        PriorityQueue<Integer> q2 = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer left, Integer right) {
                return right-left;
            }
        });
        for (int x : array) {
            q2.add(x);
            if (q2.size() > k) {
                q2.poll();
            }
        }
        System.out.println("K th smallest element: "+q2.peek());
        System.out.println("First K small elements: "+q2);
    }
}
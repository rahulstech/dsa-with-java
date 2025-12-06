import java.util.*;


/**
 * INCOMPLETE
 */



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

    void solve(int[][] jobs) {

        PriorityQueue<Node> arrivalHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.arrival, b.arrival)
        );

        PriorityQueue<Node> durationHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.duration, b.duration)
        );

        int id = 0;
        for (int[] job : jobs) {
            arrivalHeap.add(new Node(job[0], job[1], id++));
        }

        ArrayList<Integer> order = new ArrayList<>();

        int currentTime = 0;
        int totalWaiting = 0;

        while (!arrivalHeap.isEmpty() || !durationHeap.isEmpty()) {

            // move all jobs that have arrived by now
            while (!arrivalHeap.isEmpty() && arrivalHeap.peek().arrival <= currentTime) {
                durationHeap.add(arrivalHeap.poll());
            }

            // if no ready jobs, jump to next arrival
            if (durationHeap.isEmpty()) {
                Node next = arrivalHeap.poll();
                currentTime = next.arrival;    // jump time
                durationHeap.add(next);        // now it's ready
                continue;
            }

            // pick job with shortest duration
            Node job = durationHeap.poll();

            int startTime = currentTime;
            int waitTime = startTime - job.arrival;
            totalWaiting += waitTime;

            currentTime += job.duration;       // run the job
            order.add(job.index);
        }

        System.out.println("Execution order: " + order);
        System.out.println("Total waiting time: " + totalWaiting);
        System.out.println("Final finish time: " + currentTime);
    }

    public static void main(String[] args) {
        // int[][] jobs = {
        //     {0, 3},
        //     {1, 9},
        //     {2, 6}
        // };
        int[][] jobs = new int[][]{
            new int[]{0,8},   // J0
            new int[]{1,4},   // J1
            new int[]{2,9},   // J2
            new int[]{3,5},   // J3
            new int[]{10,2},   // J4   <-- late, very short
            new int[]{10,14},  // J5
            new int[]{17,1},   // J6   <-- very tiny, arrives after long task
            new int[]{30,3}    // J7   <-- arrives after CPU idle again
        };
        new Solution().solve(jobs);
    }
}

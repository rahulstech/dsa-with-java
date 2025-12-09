/**
 * There are N jobs to be done, but you can do only one job at a time.
 * Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.
 * Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.
 * Return the maximum number of jobs you can finish.
 * 
 * 1 <= N <= 105
 * 1 <= A[i] < B[i] <= 109
 * 
 */

/**
 * Solution:
 * To do maximum jobs, choose jobs that ends earlier. Why?
 * 
 * let say
 * 
 * job1 = start=1 end=10
 * job2 = start=2 end=4
 * job3 = start=4 end=5
 * job4 = start=11 end=13
 * 
 * now if i choose job1 first. according to the problem constraint while a job is running i can not run
 * another job untill it ends. job1 ends at 10 therefore can not run job2 and job3 as those starts at 2 and 4.
 * after job1 only job4 can run. hence if i start with job1 then only 2 job are done.
 * on the other hand if i choose job2 first, then job2 ends at 4, then job3 start and it ends at 5 and then job4 completes.
 * in this case 3 jobs are completed.
 * 
 * greedy algorithm is used to choose the earlier ends job first. and it leads to optimized solution.
 * 
 * Time Complexity: O(n log n)
 * 
 * Space Complexity: O(n) // to store the (start,end) paris
 */

/**
 * Greedy Algorithm:
 * - optimize locally with the assumtion that it will lead to global optimized solution.
 * - once a decision is made, no retry or going back to previous step is allowed, 
 *   greedy is always moving forward
 */


import java.util.*;

class Solution {
    public static void main(String[] args) {
        // int[] A = new int[]{1,3,0,5,8,5};
        // int[] B = new int[]{2,4,6,7,9,9};
        
        int[] A = new int[]{5, 1, 3, 0, 5, 8};
        int[] B = new int[]{9, 2, 4, 6, 7, 9};
        
        // Test Case Failed
        // int[] A = new int[]{-5, -4, -3, 0};
        // int[] B = new int[]{-3, -1, 2, 3};
        
        // int[] A = new int[]{0, 1, 2, 8, 9, 10};
        // int[] B = new int[]{100, 3, 4, 9, 10, 11};
        
        // Test Case Failed
        // int[] A = new int[]{1, 2, 3, 3};
        // int[] B = new int[]{1, 3, 3, 5};
        
        // int[] A = new int[]{1, 2, 3, 4};
        // int[] B = new int[]{2, 3, 4, 5};
        
        // int[] A = new int[]{1, 1, 1, 1};
        // int[] B = new int[]{2, 3, 4, 5};
        
        // int[] A = new int[]{1, 3, 0, 5};
        // int[] B = new int[]{4, 5, 6, 7};
        
        // PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            
        //     public int compare(int[] a, int[] b) {
        //         return  Integer.compare(a[1],b[1]);
        //     }
        // });

        int[][] jobs = new int[B.length][2];
        for(int i=0; i<B.length; i++) {
            jobs[i] = new int[]{A[i],B[i]};
        }

        Arrays.sort(jobs, (a,b) -> {
            return Integer.compare(a[1],b[1]);
        });
        
        int[] prev = new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE};
        int total = 0;
        for(int i=0; i<jobs.length; i++) {
            int[] next = jobs[i];
            // NOTE: choosing < or <= is based on problem statement.
            // if the problem allowes for example prevJob ends at 3 and next job starts at 3 i.e. overlapping
            // then use <=, otherwise use < .
            if (prev[1] <= next[0]) {
                System.out.println("take ("+next[0]+","+next[1]+")");
                prev = next;
                total++;
            }
            else {
                System.out.println("skip ("+next[0]+","+next[1]+")");
            }
        }
        
        System.out.println("total = "+total);
    }
}
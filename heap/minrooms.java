/*
You are given an array B of meeting time intervals where each interval is represented as [start, end] (with start < end). 
You need to find the minimum number of conference rooms required to host all the meetings.

Problem Constraints

A == B.length
1 <= B.length <= 104
0 <= start < end <= 106


Input Format

First Argument is an Integer A, denoting the number of meetings.
Second Argument is a 2-D integer Array B of Size Ax2, representing the start and end timing of the meetings.


Output Format

Return a single integer representing the minimum number of conference rooms required.


Example Input

Input 1:
A = 3
B = [ [0, 30],
      [5, 10],
      [15, 20] ]
Output 1:
2

Input 2:
A = 1
B = [ [0, 1] ]
Output 2:
1

Hint: calculate the maximum required room at any time. this is the answer. 

*/

import java.util.PriorityQueue;

class Solution {

    static class Node {

        int t;
        boolean starting;

        Node(int t, boolean starting) {
            this.t = t;
            this.starting = starting;
        }
    }

    public int solve(int A, int[][] B) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
            (a,b) -> {
                if (a.t == b.t) {
                    if (a.starting == b.starting) return 0;
                    // process the end event first 
                    return a.starting ? 1 : -1;
                }
                return Integer.compare(a.t, b.t);
            }
        );

        for(int[] b : B) {
            minHeap.offer(new Node(b[0], true));
            minHeap.offer(new Node(b[1], false));
        }

        int maxRooms = 0;
        int rooms = 0;
        while(!minHeap.isEmpty()) {
            Node n = minHeap.poll();
            if (n.starting) {
                rooms++;
            }
            else {
                rooms--;
            }
            maxRooms = Math.max(rooms, maxRooms);
        }
        return maxRooms;
    }

    public static void main(String[] args) {
        int A = 24;
        int[][] B = new int[][]{
            {734, 789},
            {330, 356},
            {721, 735},
            {334, 400},
            {846, 933},
            {524, 536},
            {201, 205},
            {377, 400},
            {347, 444},
            {550, 646},
            {484, 574},
            {770, 855},
            {499, 572},
            {24, 40},
            {665, 730},
            {96, 118},
            {585, 619},
            {262, 301},
            {624, 651},
            {653, 702},
            {120, 177},
            {738, 769},
            {325, 347},
            {729, 762}
        };
        int ans = new Solution().solve(A,B);
        System.out.println(ans);
    }
}

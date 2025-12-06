import java.util.PriorityQueue;

/**
 * You are given an array A of integers that represent the lengths of ropes. 
 * You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.
 * Find and return the minimum cost to connect these ropes into one rope.
 * 
 * The Calculation:
 * array = [1,2,3,4,5,6]
 * cost of connecting ropes 1 and 2 = 1+2 = 3
 * similarly 3 and 4 = 7
 *           5 and 6 = 11
 * now we have ropes 7, 3 and 11. need to connect those too.
 *           7 and 3 = 10 
 *           10 and 11 = 21
 * so total cost 3 + 7 + 11 + 10 + 21 = 52
 * 
 * Now it's a sloution though not optimized.
 * 
 * The main trick behind this is, add only the lowest and 2nd lowest lengths only. for example: 
 * 1+2 = 3 ropes [3,3,4,5,6]
 * 3+3 = 6 ropes [4,5,6]
 * 4+5 = 9 ropes [6,9]
 * 6+9 = 15 ropes [15]
 * 
 * It may look like sorting. but if i look closey then in each step only the lowest and second lowest
 * numbers matter. other numbers my order in anyways which does not matter at that step. therefore
 * min-heap is the right datastructure for this purpose
*/

class Solution {

    public static void main(String[] args) {
        int[] A = new int[]{5, 17, 100, 11};
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int a : A) {
            heap.add(a);
        }

        int total = 0;
        while(heap.size() > 1) {
            int x = heap.poll();
            int y = heap.poll();
            int s = x+y;
            total += s;
            heap.add(s);
        }
        System.out.println("total cost = "+total);
    }
}
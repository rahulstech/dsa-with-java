// Misha loves eating candies. She has been given N boxes of Candies.
// She decides that every time she will choose a box having the minimum number of candies, eat half of the candies and put the remaining candies in the other box that has the minimum number of candies.
// Misha does not like a box if it has the number of candies greater than B so she won't eat from that box. Can you find how many candies she will eat?
// NOTE 1: If a box has an odd number of candies then Misha will eat the floor(odd / 2).
// NOTE 2: The same box will not be chosen again

import java.util.PriorityQueue;

class Solution {

    public int solve(int[] A, int B) {
        // min-heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for (int a : A) {
            heap.add(a);
        }

        int total = 0;
        while(heap.size() > 1) {
            int x = heap.poll();
            if (x > B) {
                // any box after will defenitely have same or more than x candies
                // therefore those box are not accepted. i can stop confidently here.
                break;
            }
            int y = heap.poll();
            int a = (int) Math.floor(x/2);
            y += x - a;
            total += a;
            heap.add(y);
        }

        int x = heap.poll();
        if (x <= B) {
            total += (int) Math.floor(x/2);
        }

        return total;
    }

    public static void main(String[] args) {
        int[] A = new int[]{497,937,528,417,493,76,204,473,955,542,435,583,787,497,595,596,574,256,106,391,330,362,610,751};
        int B = 289;
        int total = new Solution().solve(A,B);
        System.out.println("total = "+total);
    }
}


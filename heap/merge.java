import java.util.*;

class Node {

    int value;
    int[] src;
    int index;

    Node(int value, int[] src, int index) {
        this.value = value;
        this.src = src;
        this.index = index;
    }
}

/**
 * Merge k "SORTED" lists / arrays into one "SORTED" list / array
 * 
 * TC = O(n log k)
 * SC = O(k)
 */
class Solution {

    List<Integer> merge(int[][] arrays, int k) {
        /**
         * 1. pick first elements from each arrays
         * 2. add those into min-heap
         * 3. pop the root so it's min of all the elements in heap
         * 4. put this into ans
         * 5. pick next element form the SAME ARRAY and push to the heap
         * 6. repeat step 3 to 5 untill heap is empty
         * 
         * finally we got the k merged sorted list
         * 
         * TC calculation:
         * let say there n elements on including all the k arrays. 
         * it will push all n elements. heap tc for inserting one element = O(log k)
         * so pushing n elements tc = O(n log k)
         * each elements will be popped and tc for popping one element is O(1) and
         * tc for popping n elements is O(n)
         * hence total tc = O(n + n log k) = O(n log k)
         * 
         * mergeing arrays two at a time will take the tc = O(nk) >> O(n log k)
         * 
         * since a heap of height k is used so sc = O(k)
         */


        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node l, Node r) {
                return l.value - r.value;
            }
        });

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<k; i++) {
            int[] array = arrays[i];
            heap.add(new Node(array[0],array,0));
        }

        while(!heap.isEmpty()) {
            Node root = heap.poll();
            int[] src = root.src;
            int index = root.index;
            ans.add(root.value);
            if (++index < src.length) {
                Node next = new Node(src[index],src,index);
                heap.add(next);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            new int[]{1, 4, 7},
            new int[]{2, 5, 8},
            new int[]{3, 6, 9}
        };
        int k = 3;
        List<Integer> ans = new Solution().merge(arrays,k);
        System.out.println(ans);
    }
}
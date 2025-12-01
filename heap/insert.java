import java.util.Arrays;

/**
 * Time Complexity: O(log(base 2)(n))
 * Space Complexity: O(1)
 * 
 * The algorithm do bubble up for heap insert for min-heap. 
 * means it adds the new element at lowest most level.
 * then gradually goes upto the root. so in the worst case scenario it will go upto h level. h is height of tree.
 * now in binary tree
 * level 0 = 1 node
 * level 1 = 2 nodes
 * level 2 = 4 nodes
 * level h = 2^h nodes
 * so total nodes n = 2^(h+1)-1 equivaent to 2^(h+1)
 * hence h = log(base 2)(n)
 * now algo do a swap when ever min-heap condition fails i.e. whenever parent > child then do swap.
 * time complexity of each swap is constant i.e. O(1)
 * 
 * hence final time complexity of min-heap insert is O(log(base 2)(n))
 * 
 * since algo does not use any extra space therefore space complexity is constant.
 * NOTE: that thoug algo do an array code to insert the new element. but after this the old array is of no use.
 *       therfore we can say no extra space used.
 * 
 */
class HeapInsert {

    public static void main(String[] args) {
        int[] heap = new int[]{5, 12, 8, 20, 15, 10};
        HeapInsert hi = new HeapInsert();
        heap = hi.heapInsert(heap,6);
        heap = hi.heapInsert(heap,3);
    }

    int[] heapInsert(int[] heap, int ele) {
        // heap = min-heap
        int[] dest = new int[heap.length+1];
        System.arraycopy(heap,0,dest,0,heap.length);
        int i = heap.length;

        // add the new element at the last
        dest[i] = ele;


        // NOTE: why i > 0? because root element has not parent. so no need to move up.
        // in reality when i=0 the parent = (0-1)/ = -1 / 2 = 0, not -1. therefore the it will loop infinitely.
        while(i > 0 && dest[parent(i)] > dest[i]) {
            swap(dest, parent(i),i);
            i = parent(i);
        }

        System.out.println(Arrays.toString(heap));
        return dest;
    }

    int parent(int i) {
        // parent index if ith child = (i-1)/2
        // left child of ith parent = 2i+1
        // right child of ith parent = 2i+2
        return (i-1)/2;
    }

    void swap(int[] heap, int x, int y) {
        int a = heap[x];
        heap[x] = heap[y];
        heap[y] = a;
    }
}
import java.util.Arrays;

/**
 * Time Complexity O(log base 2 (n))
 * Space Complexity O(1)
 * 
 * Note: it only removes the root element not any element. 
 */
class HeapDelete {

    public static void main(String[] args) {
        int[] heap = new int[]{3, 5, 6, 12, 15, 10, 8};
        HeapDelete hd = new HeapDelete();
        heap = hd.heapDeleteMin(heap);
        heap = hd.heapDeleteMin(heap);
    }

    int[] heapDeleteMin(int[] heap) {
        // heap = min-heap
        int deleted = heap[0];

        int n = heap.length-1;
        heap[0] = heap[n];

        int i = 0;
        while(true) {
            
            int il = 2*i+1;
            int ir = 2*i+2;

            if (il >= n) break; // no children

            int smallest = il; // suppose left child is the smallest

            // if has right child then check it is smaller than the left child
            if (ir < n && heap[ir] < heap[il]) {
                smallest = ir;
            }

            // if parent is <= smallest child so heap is now properly order,
            if (heap[i] <= heap[smallest]) break;

            // otherwise swap parent with smallest child, and continue from the smallest child
            swap(heap,i,smallest);
            i = smallest;
        }
        int[] tmp = new int[heap.length-1];
        System.arraycopy(heap,0,tmp,0,tmp.length);
        System.out.println(Arrays.toString(heap));
        System.out.println("deleted element: "+deleted);
        return heap;
    }

    void swap(int[] heap, int x, int y) {
        int a = heap[x];
        heap[x] = heap[y];
        heap[y] = a;
    }
}
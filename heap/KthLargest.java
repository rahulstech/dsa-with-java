import java.util.Arrays;


/**
 * Algorithm of the Kth Largest Number or K Large Numbers:
 * 
 * From N numbers find the kth largest number. So use min heap of size K.
 * Root element is the kth largest number and the heap represents the K larger numbers.
 * 
 * For Kth Min Number or K min numbers use max heap
 * 
 * Time Complexity O(n log k)
 * Space Complexity O(K) -> for useing a heap (array) of size K
 */
class MinHeapSoution {

    // min-heap
    int[] heap;
    int size;

    MinHeapSoution(int k) {
        heap = new int[k];
        size = 0;
    }
    
    /**
     * when heap is not full, add new element at the end of heap then bubble-up.
     * when heap is full then check if the new element is better i.e. greater than
     * root in case of min heap 
     * why?
     * the alorigm use min heap therfore any number below root is definitely larger than the root.
     * now if new element is less than the root then obiously it will never fall under K Largest numbers neighter will the kth largest number.
     * therefore algorithm ignores it. however it the new element is greater than root then definitely if will be in K largest numbers
     * and may be the kth largest.
     * 
     * why replaced the root? 
     * heap is already full i.e. upto this point in array algorithm already found k elements now it found an element greater than root.
     * so obviouly root element no longer be the part of K largest elements. but the new element may not be the kth largest element. therefore
     * the heapify is performed.
     * 
     * Time Complexity:
     * When heap is not full then TC for bubble up = O(log K)
     * When heap is full then TC for heapify = O(log k)
     * Therefore TC for on push call is O(log n)
     * But push is called for each array elements i.e. n times
     * Heance total TC for algo = O(n log K)
     */
    void push(int x) {
        // if heap is full pop the min element
        if (size == heap.length) {
            if (x > heap[0]) {
                heap[0] = x;
                heapify(0,size);
            }
            return;
        }

        // insert at last
        heap[size] = x;

        // bubble up
        int i=size;
        size = size+1;
        while (i>0 && heap[parent(i)] > heap[i]) {
            swap(parent(i),i);
            i = parent(i);
        }
    }

    int peek() {
        return heap[0];
    }

    int pop() {
        int root = heap[0];
        heap[0] = heap[size-1];
        size = size-1;
        heapify(0,size);
        return root;
    }

    void heapify(int i, int size) {
        while(true) {
            int il = leftChild(i);
            int ir = rightChild(i);

            if (il >= size) break;

            int smallest = il;
            if (ir < size && heap[ir] < heap[il]) {
                smallest = ir;
            }

            if (heap[i] <= heap[smallest]) break;

            swap(i,smallest);
            i = smallest;
        }
    }

    int parent(int i) {
        return (i-1)/2;
    }

    int leftChild(int i) {
        return 2*i+1;
    }

    int rightChild(int i) {
        return 2*i+2;
    }

    void swap(int x, int y) {
        int a = heap[x];
        heap[x] = heap[y];
        heap[y] = a;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 2, 1, 5, 6, 4};
        int k = 3;

        MinHeapSoution sol = new MinHeapSoution(k);
        for (int x : array) {
            sol.push(x);
        }

        System.out.println("Kth Largest Element: "+sol.peek());
        System.out.println("K Largest Elements: "+Arrays.toString(sol.heap));
    }
}

/**
 * Find Kth Smallest and First K Small Elements
 */
class MaxHeapSolution {

    int[] heap;
    int size;
    
    MaxHeapSolution(int k) {
        heap = new int[k];
        size = 0;
    }

    void push(int x) {
        if (heap.length == size) {
            if (x < heap[0]) {
                heap[0] = x;
                heapify(0);
            }
            return;    
        }

        // append new element
        heap[size++] = x;

        // bubble up
        int i=size-1;
        while (i > 0 && heap[parent(i)] < heap[i]) {
            swap(parent(i),i);
            i = parent(i);
        }
    }

    int peek() {
        return heap[0];
    }

    int pop() {
        int root = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return root;
    }

    int parent(int i) {
        return (i-1)/2;
    }

    void swap(int x, int y) {
        int a = heap[x];
        heap[x] = heap[y];
        heap[y] = a;
    }

    void heapify(int i) {
        while(true) {
            int il = 2*i+1;
            int ir = 2*i+2;
            if (il >= size) break;

            int largest = il;
            if (ir < size && heap[ir] > heap[il]) {
                largest = ir;
            }

            if (heap[i] >= heap[largest]) break;
            swap(i,largest);
            i = largest;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 2, 10, 8, 3, 19, 7, 6};
        int k = 4;
        MaxHeapSolution sol = new MaxHeapSolution(k);
        for (int x : array) {
            sol.push(x);
        }
        System.out.println("kth small element: "+sol.peek());
        System.out.println("k small elements: "+Arrays.toString(sol.heap));
    }
}
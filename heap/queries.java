import java.util.*;

class Solution {
    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> res = new ArrayList<>();
        heap = new int[0];
        size = 0;
        for (ArrayList<Integer> row : A) {
            int P = row.get(0);
            if (2 == P) {
                push(row.get(0));
            }
            else if (1 == P) {
                res.add(pop());
            }
        }
        return res;
    }

    int increment = 10;
    int[] heap;
    int size;

    void push(int e) {
        ensureCapacity(size+1);

        // add last 
        heap[size++] = e;

        // bubble up
        int i=size-1;
        while(i > 0 && heap[parent(i)] > heap[i]) {
            swap(parent(i),i);
            i = parent(i);
        }
        System.out.println(Arrays.toString(heap));
    }

    int pop() {
        if (size == 0) return -1;

        int root = heap[0];
        heap[0] = heap[--size];
        int i = 0;
        while (true) {
            int il = 2*i+1;
            int ir = 2*i+2;
            if (il >= size) break;
            int smallest = il;
            if (ir < size && heap[ir] < heap[il]) {
                smallest = ir;
            }
            if (heap[i] <= heap[smallest]) break;
            swap(i,smallest);
            i = smallest;
        }
        System.out.println("pop="+root);
        return root;
    }

    int parent(int i) {
        return (i-1)/2;
    }

    void swap(int j, int k) {
        int a = heap[j];
        heap[j] = heap[k];
        heap[k] = a;
    }

    void ensureCapacity(int requiredCapacity) {
        if (heap.length < requiredCapacity) {
            int[] tmp = new int[increment+heap.length];
            System.arraycopy(heap,0,tmp,0,size);
            heap = tmp;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(2, 3)),
            // new ArrayList<>(Arrays.asList(1, -1)),
            // new ArrayList<>(Arrays.asList(1, -1)),
            // new ArrayList<>(Arrays.asList(1, -1)),
            // new ArrayList<>(Arrays.asList(1, -1)),
            new ArrayList<>(Arrays.asList(2, 9)),
            new ArrayList<>(Arrays.asList(2, 17)),
            // new ArrayList<>(Arrays.asList(1, -1)),
            new ArrayList<>(Arrays.asList(2, 13))
        ));
        System.out.println(sol.solve(A));
    }
}
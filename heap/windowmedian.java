import java.util.*;

class Pair {
    int value;
    int heap; // 0 = smaller 1 = larger
    Pair(int value, int heap) {
        this.value = value;
        this.heap = heap;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof Pair) {
            Pair that = (Pair) o;
            return that.value == this.value && that.heap == this.heap;
        }
        return false;
    }
}

class Solution {

    PriorityQueue<Integer> larger;
    PriorityQueue<Integer> smaller;
    HashMap<Pair,Integer> delayed;
    int sizeSmaller, sizeLarger;

    void solve(int[] array, int k) {
        larger = new PriorityQueue<>();
        smaller = new PriorityQueue<>(
            (a,b) -> Integer.compare(b, a)
        );
        delayed = new HashMap<>();
        sizeSmaller = 0;
        sizeLarger = 0;

        ArrayList<Integer> ans = new ArrayList<>();

        // prepare the first window
        for (int i=0; i<k; i++) {
            add(array[i]);
        } 

        // median for first window
        ans.add(getMedian());

        // mark the last window start as removed
        int j=0;
        while (j+k <= array.length) {
            remove();
            mark(array[j]);
            add(array[j+k-1]);
            int median = getMedian();
            ans.add(median);
            j++;
        }
        

        System.out.println("array="+Arrays.toString(array));
        System.out.println("medians: "+ans);

    }

    int getMedian() {
        if (larger.size() > smaller.size()) {
            return larger.peek();
        }
        else {
            return smaller.peek();
        }
    }

    void mark(int num) {
        if (!smaller.isEmpty() && num < smaller.peek()) {
            Pair p = new Pair(num, 0);
            delayed.put(p, delayed.getOrDefault(p, 0)+1);
            sizeSmaller--;
        }
        else if (!larger.isEmpty() && num < larger.peek()) {
            Pair p = new Pair(num, 1);
            delayed.put(p, delayed.getOrDefault(p, 0)+1);
            sizeLarger--;
        }

        // rebalance
        balance();
    }

    void add(int num) {
        // add
        if (!smaller.isEmpty() && num <= smaller.peek()) {
            smaller.add(num);
            sizeSmaller++;
        }
        else {
            larger.add(num);
            sizeLarger++;
        }

        // balance
        balance();
    }

    void balance() {
        if (smaller.size() > larger.size()+1) {
            larger.add(smaller.poll());
            sizeLarger++;
            sizeSmaller--;
        }
        else if (larger.size() > smaller.size()+1) {
            smaller.add(larger.poll());
            sizeSmaller++;
            sizeLarger--;
        }
    }

    void remove() {
        while(!smaller.isEmpty()) {
            int top = smaller.peek();
            Pair pair = new Pair(top,0);
            int count = delayed.getOrDefault(pair,0);
            if (count > 0) {
                smaller.poll();
                delayed.put(pair, count-1);
            }
            else {
                break;
            }
        }
        while(!larger.isEmpty()) {
            int top = larger.peek();
            Pair pair = new Pair(top,1);
            int count = delayed.getOrDefault(pair,0);
            if (count > 0) {
                larger.poll();
                delayed.put(pair, count-1);
            }
            else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,5,6,3,2,4,8,9};
        int k = 3;
        new Solution().solve(array,k);
    }
}
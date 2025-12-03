import java.util.*;

class median {

    public static void main(String[] args) {
        int[] A = new int[]{5,15,1,3,8,7};


        /**
         * Running Median: numbers keeps arriving now calculate the median all available number.
         * Median = the middle element of sorted numbers
         *          N is the no of elements. now 
         *          N is odd the median is N/2 th element in the sorted numbers
         *          N is even the median is (N/2 - 1) th element in the sorted numbers
         * 
         * Solutions:
         * Use two heaps min and max. of the available numbers: 
         * - min heap stores all the larger numbers
         * - max heap stores all the small numbers
         * 
         * after each insert the balance size. balance size means always main size differnce = 1.
         * why?
         * in case of odd number of elements: the heaps contains numbers smaller than media and larger than median.
         * therfore exactly one of the heap will also contain the media itself. 
         * 
         * in case of even number of elements: both the heaps now contains equals elements and roots are at the middle
         * of the data stream and most nearest to the media. so median is calculated as the average of the roots.
         */
        PriorityQueue<Integer> large = new PriorityQueue<>();
        PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<>(){

            @Override
            public int compare(Integer l, Integer r) {
                return r-l;
            }
        });

        for (int x : A) {
            // insert
            if (small.isEmpty() || x < small.peek()) {
                small.add(x);
            }
            else {
                large.add(x);
            }

            // balance size
            if (small.size() > large.size()+1) {
                large.add(small.poll());
            }
            else if (large.size() > small.size()+1) {
                small.add(large.poll());
            }

            // calculate median
            int median = 0;
            if (small.size() > large.size()) {
                median = small.peek();
            }
            else if (large.size() > small.size()) {
                median = large.peek();
            }
            else {
                median = (small.peek()+large.peek())/2;
            }

            System.out.println(median);
        }
    }
}
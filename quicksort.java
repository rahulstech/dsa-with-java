import java.util.Arrays;

class QuickSort {

    static void quicksort(int[] A, int start, int end) {
        if (start>=end) return;

        int i = start+1, j=end;
        int p = A[start];
        while(i<=j) {
            while(i <= end && A[i] <= p) {
                i++;
            }
            while(A[j] > p && j > start) {
                j--;
            }

            if (i < j) {
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
            }
        }
        A[start] = A[j];
        A[j] = p;

        quicksort(A, start, j-1);
        quicksort(A,j+1, end);
    }

    public static void main(String[] args) {
        // int[] A = new int[]{54,26,73,17,77,31,44,55,20};

        int[] A = {
            23, 5, 87, 12, 34, 99, 3, 76, 45, 67,
            54, 21, 11, 98, 43, 65, 1, 88, 32, 19,
            77, 56, 4, 91, 29, 38, 66, 83, 83, 74,
            58, 41, 6, 97, 15, 49, 81, 14, 35, 62,
            7, 93, 18, 27, 68, 84, 22, 53, 39, 50,
            23, 5, 87, 12, 34, 99, 3, 76, 45, 67,
            54, 21, 11, 98, 43, 65, 1, 88, 32, 19
        };

        System.out.println("Before Sort: "+Arrays.toString(A));
        quicksort(A,0,A.length-1);
        System.out.println("After Sort: "+Arrays.toString(A));

    }
}
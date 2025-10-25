import java.util.ArrayList;

class Solution {

    public static void main(String[] args) {
        int[] arr1 = {2,5,8,11,11,11,14,17,20,20,20,20,20,23,26,26};
        int[] arr2 = {4,7,9,10,11,11,15,16,18,19,20,20,21,26,26};

        System.out.println("Common elements: ");
        printCommonElements(arr1, arr2);
    }
    public static void printCommonElements(int[] A, int[] B) {
        int p = 0, q = 0, n;
        ArrayList<Integer> commonElements = new ArrayList<>();
        // System.out.println("A.length: " + A.length + ", B.length: " + B.length);
        while (p<A.length && q<B.length) {
            // System.out.println("p: " + p + ", q: " + q);
            if (A[p] < B[q]) {
                p++;
            }
            else if (A[p] > B[q]) {
                q++;
            }
            else {
                n = A[p];
                while (p<A.length && q<B.length && n==A[p] && n==B[q]) {
                    commonElements.add(n);
                    p++;
                    q++;
                }
                while (p<A.length && n==A[p]) {
                    p++;
                }
                while (q<B.length && n==B[q]) {
                    q++; 
                }
            }
        }
        System.out.println(commonElements);
    }
}
import java.util.Stack;
import java.util.Arrays;

class NearestSmall {
    static int[] prevSmaller(int[] A) {
        int n = A.length;
        Stack<int[]> s = new Stack<>();
        int[] result = new int[n];
        result[0] = -1;
        s.push(new int[]{A[0],0});
        for(int i=1; i<n; i++) {
            int a = A[i];
            while(!s.isEmpty()) {
                if (s.peek()[0] > a) {
                    s.pop();
                }
                else {
                    break;
                }
            }
            if (s.isEmpty()) {
                result[i] = -1;
            }
            else {
                result[i] = s.peek()[0];
            }
            s.push(new int[]{a, i});
        }
        return result;
    }

    public static void main(String[] args) {
        // int[] A = {4, 5, 2, 10, 8};
        int[] A = {3, 2, 1};
        int[] idcs = prevSmaller(A);
        System.out.println(Arrays.toString(idcs));
    }
}

import java.util.Arrays;
import java.util.Stack;

class SubarrayMinMaxSum {
    static int solve(int[] A) {
        int[] prevMin = getPreviousMin(A);
        int[] prevMax = getPreviousMax(A);
        int[] nextMin = getNextMin(A);
        int[] nextMax = getNextMax(A);
        int n = A.length;

        long sum = 0;
        for (int i=0; i<n; i++) {
            int a = A[i];

            // min
            int lmin = prevMin[i];
            int rmin = nextMin[i];

            // max
            int lmax = prevMax[i];
            int rmax = nextMax[i];

            long samax = (i-lmax[i]) * (rmax[i] - i);
            long samin = (i-lmin[i]) * (rmin[i] - i);
            sum += (samax - samin) * a;
        }

        return (int) (sum%1000000007);
    }

    static int[] getPreviousMin(int[] A) {
        int n = A.length;
        Stack<int[]> s = new Stack<>();
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            int a = A[i];
            while(!s.isEmpty() && s.peek()[0] >= a) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = -1;
            }
            else {
                result[i] = s.peek()[1];
            }
            s.push(new int[]{a,i});
        }
        return result;
    }

    static int[] getPreviousMax(int[] A) {
        int n = A.length;
        Stack<int[]> s = new Stack<>();
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            int a = A[i];
            while(!s.isEmpty() && s.peek()[0] <= a) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = -1;
            }
            else {
                result[i] = s.peek()[1];
            }
            s.push(new int[]{a,i});
        }
        return result;
    }

    static int[] getNextMin(int[] A) {
        int n = A.length;
        Stack<int[]> s = new Stack<>();
        int[] result = new int[n];
        for(int i=n-1; i>=0; i--) {
            int a = A[i];
            while(!s.isEmpty() && s.peek()[0] >= a) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = n;
            }
            else {
                result[i] = s.peek()[1];
            }
            s.push(new int[]{a,i});
        }
        return result;
    }

    static int[] getNextMax(int[] A) {
        int n = A.length;
        Stack<int[]> s = new Stack<>();
        int[] result = new int[n];
        for(int i=n-1; i>=0; i--) {
            int a = A[i];
            while(!s.isEmpty() && s.peek()[0] <= a) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = n;
            }
            else {
                result[i] = s.peek()[1];
            }
            s.push(new int[]{a,i});
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {992387,932142,971117,934674,988917,967890,948508,970347};
        System.out.println("A: "+Arrays.toString(A));

        int[] prevMin = getPreviousMin(A);
        int[] prevMax = getPreviousMax(A);
        int[] nextMin = getNextMin(A);
        int[] nextMax = getNextMax(A);

        System.out.println("prevMin: "+Arrays.toString(prevMin));
        System.out.println("prevMax: "+Arrays.toString(prevMax));
        System.out.println("nextMin: "+Arrays.toString(nextMin));
        System.out.println("nextMax: "+Arrays.toString(nextMax));
    }
}

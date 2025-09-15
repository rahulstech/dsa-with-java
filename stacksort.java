import java.util.Stack;
import java.util.Arrays;

class StackSort {

    static int[] sort(int[] A) {
        Stack<Integer> main = new Stack<>();
        Stack<Integer> helper = new Stack<>();
        for(int a : A) {
            while(!main.isEmpty() && main.peek() < a) {
                int m = main.pop();
                helper.push(m);
            }
            main.push(a);
            while(!helper.isEmpty()) {
                main.push(helper.pop());
            }
        }
        int n = A.length;
        int[] S = new int[n];
        for(int i=0; i<n; i++) {
            S[i] = main.pop();
        }
        return S;
    }

    public static void main(String[] args) {
        int[] A = new int[]{5,4,3,2,1};
        int[] S = sort(A);

        System.out.println("originall: "+Arrays.toString(A));
        System.out.println("sorted: "+Arrays.toString(S));
    }

}
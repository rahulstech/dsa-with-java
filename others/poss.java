import java.util.Stack;
import java.util.Arrays;
import java.util.List;

class Possession {

    static int solve(int A, int B, List<Integer> C) {
        Stack<Integer> s = new Stack<>();
        s.add(B);
        for (int c : C) {
            if (c == 0) {
                s.pop();
            }
            else {
                s.add(c);
            }
        }
        return s.peek();
    }

    public static void main(String[] args) {
        // List<Integer> c = Arrays.asList(
        //     86, 63, 60, 0, 47, 0, 99, 9, 0, 0
        // );
        // int b = 23;
        List<Integer> c = Arrays.asList(2);
        int b = 1;
        int x = solve(c.size(), b, c);
        System.out.println(x);
    }
}
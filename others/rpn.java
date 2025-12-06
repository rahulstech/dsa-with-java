import java.util.List;
import java.util.Stack;
import java.util.Arrays;

class RPN {

    static int evaluate(List<String> A) {
        Stack<Long> s = new Stack<>();
        for(String a : A) {
            if (isOperator(a)) {
                long n = s.pop();
                long m = s.pop();
                long r = calculate(a, m, n);
                s.add(r);
            }
            else {
                long n = Long.valueOf(a);
                s.add(n);
            }
        }
        long r = s.pop();
        return (int) r;
    }

    static boolean isOperator(String t) {
        return "+-*/".indexOf(t) >= 0;
    }

    static long calculate(String op, long m, long n) {
        if (op == "+") {
            return  m+n;
        }
        else if (op == "-") {
            return m-n;
        }
        else if (op == "*") {
            return m*n;
        }
        else {
            return m/n;
        }
    }

    public static void main(String[] args) {
        List<String> exp = Arrays.asList(
            "5","1","2","+","4","*","+", "3", "-"
        );
        int result = evaluate(exp);
        System.out.println(exp+" = "+result);
    }
}
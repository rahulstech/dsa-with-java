import java.util.Stack;

class Pairs {
    public int solve(String A) {
        char[] chars = A.toCharArray();
        Stack<Character> s = new Stack<>();
        for (char c : chars) {
            if (isOpen(c)) {
                s.add(c);
            }
            else if (s.isEmpty()) {
                return 0;
            }
            else {
                char open = s.pop();
                if (!isPair(open, c)) {
                    return 0;
                }
            }
        }
        return s.isEmpty() ? 1 : 0;
    }

    boolean isOpen(char c) {
        return c == '(' || c == '{' || c == '[' ; 
    }

    boolean isPair(char open, char close) {
        return (open == '(' && close == ')')
        || (open == '{' && close == '}')
        || (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        Pairs p = new Pairs();
        String exp = "[({})";
        int r = p.solve(exp);
        System.out.println(exp+" = "+r);
    }
}

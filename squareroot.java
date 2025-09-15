class SquareRoot {

    static int sqrt(int n) {
        return sqrt(n, 0, n);
    }

    static int sqrt(long n, int l, int h) {
        if (l > h ) return h;
        int m = (l+h)/2;
        long x = m*m;
        if (x > n) return sqrt(n, l, m-1);
        if (x < n) return sqrt(n, m+1, h);
        return m;
    }

    public static void main(String[] args) {
        int n = 450;
        int v = sqrt(n);
        System.out.println("sqrt("+n+")="+v);
    }
}
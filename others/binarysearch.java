class BinarySearch {

    static int search(int[] A, int B, int l, int h) {
        if (l > h) return l;
        int m = (l+h)/2;
        int a = A[m];
        if (a < B) return search(A, B, m+1, h);
        if (a > B) return search(A, B, l, m-1);
        return m;
    }

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,18,19,20,21,24};
        int B = 0;
        int i = search(A, B, 0, A.length-1);
        if (i >= A.length) {
            i = -1;
        }
        System.out.println("index of "+B+" = "+i);
    }
}
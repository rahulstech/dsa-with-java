class FindUniqueInArray {

    static int find(int[] arr) {
        int len = arr.length;
        if (arr[0] != arr[1]) {
            return arr[0];
        }
        if (arr[len-2] != arr[len-1]) {
            return arr[len-1];
        }
        int L = 0;
        int H = arr.length-1;
        int m, idx;
        while(L <= H) {
            m = L + (H-L)/2;
            int a = arr[m];
            System.out.println("L="+L+" H="+H+" m="+m);
            // if duplicate element in left then goto right
            if (a == arr[m-1]) {
                idx = m-1;
            }
            // if duplicate element in right then goto left
            else if (a == arr[m+1]) {
                idx = m;
            }
            else {
                return a;
            }

            if(idx%2 == 0) {
                L = m+1;
            }
            else {
                H = m-1;
            }
        }
        return arr[L];
    } 

    public static void main(String... args) {
        // int[] arr = new int[]{8,8,5,5,6,2,2};
        int[] arr = new int[]{3,7,7,6,6,8,8,1,1,9,9};
        int t = find(arr);
        System.out.println("unique element is "+t);
    }
}
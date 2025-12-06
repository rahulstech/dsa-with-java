/**
 * example:
 * 1
 * 11
 * 121
 * 1331
 * 14641
 * 15101051
 * 1615201561
 * ...
 * 
 * algorithm 1:
 * ===========
 * col => 0             1                       3                   4
 * 0   => 1
 * 1   => 1             1
 * 2   => 1 (val[1][1]+val[1][0]=1+1=2)         1
 * 3   => 1 (val[2][1]+val[2][0]=2+1=3)  (val[2][3]+val[2][1]=1+2=3) 1
 * 
 * val[row][0] = val[row][row] = 1
 * val[row][col] = val[row-1][col] + val[row-1][col-1]
 * 
 * TC: O(n^2)
 * SC: O(n^2) // need the matrix for digital sum calculation
 * 
 * algorithm 2:
 * ===========
 * 1=0C0
 * 1=1C0 1=1C1
 * 1=2C0 2=2C1 1=2C1
 * 1=3C0 3=3C1 3=3C2 1=3C3
 * 1=4C0 4=4C1 6=4C2 4=4C3 1=4C4
 * 
 * also observe that: nC(r+1) = nCr * (n-r)/(r+1)
 * 
 * TC: O(n^2)
 * SC: O(1) // we don't need a extra matrix sum digital sum calculation
 * 
 * Note 1: if target is to return the pascal tree then use algorithm 1
 * Note 2: if target is the return the final number or print the tree then use algorithm 2
 */
class PascalTriangle {

    static void PT_algo1(int n) {
        int[][] pt = new int[n][n];
        pt[0][0] = 1;
        for(int i=1; i<n; i++) {
            pt[i][0] = 1;
            for(int j=1; j<i; j++) {
                pt[i][j] = pt[i-1][j] + pt[i-1][j-1];
            }
            pt[i][i] = 1;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print(pt[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void PT_algo2(int n) {

        int val;
        for(int i=0; i<n; i++) {
            val = 1;
            for(int j=0; j<=i; j++) {
                System.out.print(val+" ");
                val = val * (i-j)/(j+1);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 7;
        PT_algo1(n);
        System.out.println();
        PT_algo2(n);
    }
}
/**
 * This the dp tabulation solution 
 */

class Solution {

    void solve(int n) {
        // dp[x] = minimum no of square numbers required to make the number x
        int[] dp = new int[n+1];

        // i can always represent x atleast with 1^2 + 1^2 + 1^2 ... x times
        // so initially set that no. of squares required is the number itself
        // but it's not optimized solution, so i will optimize later
        for (int i=1; i<=n; i++) {
            dp[i] = i;
        }

        // intention here is 
        // find minimum no. of squares required to represent the number i
        // previously i know that i can use i 1^2, otherwise i take bigger square number till i
        // i.e. take 1^2, 2^2, 3^2, ... <= i, so here i used one square number 
        // no for remaining part i.e. i - j^2 just find the minimum no of squares required to represent the number (i - j^2)
        for(int i=1; i<= n; i++) {
            for (int j=1; j*j <= i; j++) {
                // take minimum, because optimal solution may be already found, if so don't update that
                dp[i] = Math.min(

                    // previously calculated minimum no. of squares required for i
                    dp[i], 

                    // considering j*j as square no to represent i. +1 is for that j^2
                    1 
                    // now the remaining part is (i - j^2), 
                    // just find the minimum no. of squares required to represent this number  
                    + dp[i - j*j] 
                );
            }
        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) {
        int n = 6;
        new Solution().solve(n);
    }
}
import java.util.*;

class Solution {

    boolean found = false;
    int[] nums;
    int target;

    public int SubsetSum(int[] A, int B) {
        if (A.length == 0) {
            return B == 0 ? 1 : 0;
        }
        Arrays.sort(A);
        this.nums = A;
        this.found = false;
        this.target = B;
        backtrack(0,0);
        System.out.println(Arrays.toString(A));
        return found ? 1 : 0;
    }

    void backtrack(int cur, int start) {
        if (found) return;
        if (cur == target) {
            found = true;
            return;
        }
        for(int i=start; i<nums.length; i++) {
            System.out.println("start "+start+" cur="+cur+" nums["+i+"]="+nums[i]+" found="+found);
            if (found || cur+nums[i] > target) {
                break;
            }
            backtrack(cur+nums[i], i+1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int ans = sol.SubsetSum(new int[]{-3066,-8190,-2454,-4789,-8929,-1635,-7945,-4880,-2885,
        -8368,5018,-6065,4651,-2087,455,-6268,9505}, -8235);
        System.out.println("ans="+ans);
    }
}

/**
 * N children are standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum number of candies you must give?
 * 
 * 1 <= N <= 105
 * 109 <= A[i] <= 109
 */

/**
 * Solution Explanation (Two-Pass Greedy)
 *
 * This problem has a local constraint in both directions:
 *  - If a child has a higher rating than the left neighbor, they must get more candies.
 *  - If a child has a higher rating than the right neighbor, they must get more candies.
 *
 * A single left-to-right greedy pass cannot satisfy both constraints, because
 * rising sequences on the right side and falling sequences on the left side
 * influence the final candy count. To satisfy both directions while keeping
 * the candy distribution minimal, a two-pass greedy approach is used.
 *
 * What “two-pass greedy” means:
 *  - Solve the problem greedily in one direction (left → right).
 *  - Solve the same constraint in the opposite direction (right → left).
 *  - Merge the results by taking the maximum candies required at each index.
 *
 * Why this works:
 *  1. Each pass enforces one directional rule with minimal required candies.
 *  2. The constraints are local and monotonic (a higher rating needs exactly +1 more).
 *  3. Combining the two passes with `max()` ensures both directional constraints
 *     are satisfied, while still keeping the solution minimal.
 *
 * How to identify when two-pass greedy applies:
 *  - The problem has local neighbor-based rules.
 *  - The rules apply independently from both directions.
 *  - Greedy correction from one direction may violate constraints from the other.
 *  - The constraints are monotonic (greater/less comparisons).
 *  - The final value at each index can be derived as max(left_req, right_req)
 *    or min(left_req, right_req), depending on the problem.
 *
 * Steps used here:
 *  1. Initialize every child with 1 candy.
 *  2. Left → right pass:
 *       If rating[i] > rating[i-1], set candies[i] = candies[i-1] + 1.
 *  3. Right → left pass:
 *       If rating[i] > rating[i+1], set candies[i] = max(candies[i], candies[i+1] + 1).
 *  4. Sum all candies.
 *
 * Similar problems using two-pass greedy:
 *  - Trapping Rain Water (compute left max array and right max array).
 *  - Product of Array Except Self (prefix pass + suffix pass).
 *  - Nearest Greater Element from both sides (two directional scans).
 *  - Mountain array checks / longest hill with left slope and right slope.
 *
 * Time Complexity:  O(N)  
 *     Two linear passes + summation.
 *
 * Space Complexity: O(N)  
 *     Additional array to store candy counts.
 */


import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int[] ratings = new int[] {1, 5, 2, 1};

        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for(int i=1; i<ratings.length; i++) {
            if (ratings[i-1] < ratings[i]) {
                candies[i] = candies[i-1]+1;
            }
        }

        for(int i=ratings.length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }

        int total = 0;
        for (int c : candies) {
            total += c;
        }

        System.out.println("total minimum required candies = "+total);
    }
}
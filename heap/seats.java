// There is a row of seats represented by string A. Assume that it contains N seats adjacent to each other.
// There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.
// An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')
// Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.
// In one jump a person can move to the adjacent seat (if available).
// NOTE: 1. Return your answer modulo 10^7 + 3.
// Constraints
// 1 <= N <= 1000000
// A[i] = 'x' or '.'

/**
 * Solution:
 * 
 * An important note here is the oder of the people can not change in the final arrangement. for example:
 * 
 * Before:
 * ....a..bc...d..
 * 
 * After:
 * .....abcd......
 * 
 * Let say the first person moved from real position a to final position q. so according to the problem statement
 * final positions of
 * 
 * second person q+1
 * thrird person q+2
 * fourth person q+3
 * ...
 * 
 * so the cost of movement for the person
 * first abs( a - q)
 * second abs( b - (q+1))
 * third abs( c - (q+2))
 * ...
 * 
 * assuming that a, b, c etc. are real positions of first, second, third persons respectively.
 * 
 * so the total cost is
 * 
 * COST = sum abs( original_position[i] - (q+i)) over i, i is the index of person, first person i = 0, second person i = 1 ...
 * or sum abs((original_position[i] - i) - q) over i
 * or sum abs(value[i] - q) over i , value[i] = original_pos[i] - i
 * 
 * Now i have to calculate the value: q
 * 
 * In classic math problem:
 * “Which number q minimizes the sum of absolute differences?”
 * Answer: The median of the values.
 * 
 * hence COST will be minimum if q is the median of all value[i]
 * 
 * Complexities:
 * 
 * TC = O( n + k log k)
 * SC = O( k ) 
 * 
 * where n = length of the string
 *       k = number of person
 */



import java.util.ArrayList;
import java.util.Collections;

class Solution {

    static final int MOD = 10000003;

    int solve(String A) {
        if (A.length() == 0) return 0;

        // loops n times TC = O(n) for n kength string
        // diffs holdss differences of k no of people
        ArrayList<Integer> diffs = new ArrayList<>();
        for (int i=0, j=0; i < A.length(); i++) {
            if (A.charAt(i) == 'x') {
                diffs.add(i-j); // NOTE: not the absolution difference
                j++;
            }
        }
        // NOTE: heap is not required here. because Collections.sort and 
        // heap alog for median both takes O(n log n) time

        // sorting TC = O(k log k)
        Collections.sort(diffs);
        int median = diffs.get(diffs.size()/2);

        // loops over k differences TC = O(k) 
        int sum = 0;
        for (int d : diffs) {
            sum = (sum + Math.abs(d-median)) % MOD;
        }

        // total TC = O(n) + O(k log k) + O(k) = O(n + k log k)
        //       SC = O(k)

        return sum;
    }

    public static void main(String[] args) {
        // String  A = "....x..xx...x..";
        String A = "....xxx";
        int ans = new Solution().solve(A);
        System.out.println(ans);
    }
}
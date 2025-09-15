import java.util.Arrays;

class TotalDonation {

static int[] calculateTotalDonation(int beggers, int queries[][]) {
int[] total = new int[beggers];
for (int[] q : queries) {
int s = q[0]-1;
int e = q[1];
int d = q[2];
total[s] += d;
if (e<beggers) {
total[e] -= d;
}
}

for (int i=1; i<beggers; i++) {
total[i] += total[i-1];
}
return total;
}

public static void main(String[] args) {
int beggers = 5;
int[][] queries = new int[][]{new int[]{1,2,10},new int[]{2,3,20},new int[]{2,5,25}};
int[] total = calculateTotalDonation(beggers,queries);
System.out.println("total: "+Arrays.toString(total));
}
}

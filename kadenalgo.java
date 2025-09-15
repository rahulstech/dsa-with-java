class KadenAlgo {

static int getMaxSum(int[] array) {

int n = array.length;
int max_sum = 0;

for (int i=0; i<n; i++) {
int a = array[i];
int sum = max_sum + a;
max_sum = Math.max(a, sum);
}

return max_sum;
}

public static void main(String[] args) {
int[] array = new int[]{-1,4,-6,2,8,-2,3};
int max_sum = getMaxSum(array);
System.out.println("max_sum="+max_sum);
}

}

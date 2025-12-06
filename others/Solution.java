import java.util.Arrays;

class MergeSort {

void printArray(int[] array, int start, int length, String message) {
System.out.print(message+": ");
for(int i=0; i<length; i++) {
System.out.print(array[start+i]+" ");
}
System.out.println();
}

void sort(int[] array, int start, int end) {
int len = end-start+1;

if (len == 1) {
return;
}

// int len1 = len/2;
// int len2 = len-len1;

int mid = (start+end)/2;
int i=start, j=mid+1, r=0;
int[] tmp = new int[len];

sort(array, start, mid);
sort(array, mid+1, end);

// System.out.println("start="+start+" end="+end+" len="+len+" len1="+len1+" len2="+len2+" i="+i+" j="+j);
printArray(array, i, mid-i+1, "subarry1[]");
printArray(array, j, end-mid,"subarray2[]");

while(i<=mid && j<=end) {
int a = array[i];
int b = array[j];
System.out.println("a="+a+" b="+b);

if (a < b) {
tmp[r++] = a;
i++;
}
else if (b < a) {
tmp[r++] = b;
j++;
}
else {
tmp[r++] = a;
tmp[r++] = b;
i++;
j++;
}
}

//System.out.println("main loop exited: i="+i+" j="+j);

while(i<=mid) {
tmp[r++] = array[i];
i++;
}
while(j<=end) {
tmp[r++] = array[j];
j++;
}

//printArray(tmp, 0, len, "tmp array: ");

for(r=0; r<len; r++) {
array[r+start] = tmp[r];
}
}

public static void main(String[] args) {
MergeSort algo = new MergeSort();
int[] array = new int[]{51,10,25,31,37,42,2,7,35,49, 1};

algo.printArray(array,0,array.length,"before sort: ");
algo.sort(array, 0, array.length-1);
algo.printArray(array,0,array.length,"after sort: ");
}
}

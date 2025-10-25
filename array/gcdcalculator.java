import java.util.Scanner;

class GCDCalculator {

static int gcd(int a, int b) {

int min = Math.min(a,b);
int max = Math.max(a,b);
if (min == 0) {
return max;
}
else if (min == 1) {
return 1;
}
else {
return gcd(min, max%min);
}
}

public static void main(String[] args) {
Scanner in = new Scanner(System.in);
System.out.print("a= ");
int a = in.nextInt();
in.nextLine();
System.out.print("b= ");
int b = in.nextInt();
in.nextLine();
int result = gcd(a,b);
System.out.println("gcd("+a+","+b+"): "+result);
}
}

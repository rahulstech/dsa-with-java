import java.util.Scanner;

class PowerFunction {

static int pow(int a, int b, int c) {

if (b == 0) {
return 1;
}
if (b == 1) {
return a;
}

long p = pow(a,b/2,c);
int r = (int) (p*p)%c;
if (b%2 == 0) {
return r;
}
else {
return (int)  (r * a%c)%c;
}
}


public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("a= ");
int a = sc.nextInt();
sc.nextLine();
System.out.print("b= ");
int b = sc.nextInt();
sc.nextLine();
System.out.print("c= ");
int c = sc.nextInt();
sc.nextLine();
int result = pow(a,b,c);
System.out.println(a+"^"+b+"%"+c+"= "+result);
}
}

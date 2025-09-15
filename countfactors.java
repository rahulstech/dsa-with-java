import java.util.Scanner;

class CountFactors {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = 0;
        for (int i=1; i<=Math.sqrt(n); i++) {
            if (n%i == 0) {
                if (i==n/i) {
                    c++;
                }
                else {
                    c+=2;
                }
            }
        }
        System.out.println("no. of factors of "+n+" is "+c);
    }
}
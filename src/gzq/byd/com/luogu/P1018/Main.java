package gzq.byd.com.luogu.P1018;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 6-40
        int k = scanner.nextInt(); // <=6

        String num = scanner.next();

        BigInteger bigInteger = new BigInteger("0");
        if(k==n-1) {
            bigInteger = new BigInteger(String.valueOf(num.charAt(0)));
            for (int i = 1; i < num.length(); i++) {
                bigInteger=bigInteger.multiply(new BigInteger(String.valueOf(num.charAt(i))));
            }
        }

        System.out.println(bigInteger);
    }

}

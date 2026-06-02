package gzq.byd.com.luogu.P1420;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = 1,current = 0,cnt = 0;
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            if(temp==(current+1) || current == 0) {
                cnt++;
            } else {
                max = Math.max(max,cnt);
                cnt = 1;
            }
            current = temp;
        }
        max = Math.max(max,cnt);
        System.out.println(max);
    }
}

package gzq.byd.com.luogu.P1321;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        target = target.trim()+"...";
        int boy =0, girl = 0;
        for (int i = 0; i < target.length()-3; i++) {
            if(target.charAt(i)=='b'||target.charAt(i+1)=='o'||target.charAt(i+2)=='y') boy++;
            if(target.charAt(i)=='g'||target.charAt(i+1)=='i'||target.charAt(i+2)=='r'||target.charAt(i+3)=='l') girl++;
        }
        System.out.println(boy);
        System.out.println(girl);
    }
}

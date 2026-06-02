package gzq.byd.com.luogu.P1320;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        temp = temp.trim();
        int count = temp.length(),kill = count;
        int flag = 0,cnt = 0;
        List<Integer> list = new ArrayList<>();
        while(!temp.isEmpty()) {
            // 连续被打断了
            int index = temp.indexOf(String.valueOf((flag + 1) % 2));
            if(index>-1) {
                // 先继续加到打断位置
                cnt+=index;
                list.add(cnt);
                // 然后归零计算另外一个数
                cnt=0;
                flag = (flag + 1) % 2;
                temp = temp.substring(index);
                continue;
            }
            cnt+=temp.length();
            count--;
            if(count==0) break;
            temp=scanner.nextLine();
            temp=temp.trim();

        }
        if(cnt>0) {
            list.add(cnt);
        }
        System.out.print(kill);
        for (Integer integer : list) {
            System.out.print(" "+integer);
        }
    }

}

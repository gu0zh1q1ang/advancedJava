package advanced.java.commonSense.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestChecked {
    public static void main(String[] args) {
        Thread heiha = new Thread(() -> {
            long current = System.currentTimeMillis();
            try {
                System.out.println("begin");
                test();
                System.out.println("end");
            } catch (Error | InterruptedException e) { // catch里是可以catch Error的，但是什么都做不了
                System.out.println(Thread.currentThread().getName()+"运行了：");
                System.out.println((System.currentTimeMillis() - current) / 1000 + "秒，遇到了" + e.getClass());
            }
        }, "height");

        heiha.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void test ()  throws InterruptedException{
        List<boolean[]> list = new ArrayList<>();
        while(true) {
            Thread.sleep(1000);
            list.add(new boolean[1024*1024]);
        }
    }
}

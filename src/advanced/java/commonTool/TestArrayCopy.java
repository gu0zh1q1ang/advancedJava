package advanced.java.commonTool;

import java.util.Arrays;
class Utils {
    public static void printThreadName() {
        System.out.println(Thread.currentThread().getName());
    }
}
public class TestArrayCopy {
    static int[] nums2 = new int[4];
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        Thread a = new Thread(() -> {
            Utils.printThreadName();
            System.arraycopy(nums, 0, nums2, 0, 3);
        }, "thread-1");
        Thread b = new Thread(() -> {
            Utils.printThreadName();
            System.arraycopy(nums, 0, nums2, 0, 3);
        }, "thread-2");
        Thread c = new Thread(() -> {
            Utils.printThreadName();
            nums2[1] = 99;
        }, "thread-4");
        Thread d = new Thread(() -> {
            Utils.printThreadName();
            System.arraycopy(nums, 0, nums2, 0, 3);
        }, "thread-5");
        Thread e = new Thread(() -> {
            Utils.printThreadName();
            System.arraycopy(nums, 0, nums2, 0, 3);
        }, "thread-6");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println(Arrays.toString(nums2));
    }
}

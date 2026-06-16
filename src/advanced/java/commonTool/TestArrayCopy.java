package advanced.java.commonTool;

import java.util.Arrays;

public class TestArrayCopy {
    public static void main(String[] args) {
        new Thread(()->{

        }, "thread-1");
        final int[] nums = {1,2,3,4,5}, nums2=new int[4];
        System.arraycopy(nums,0,nums2,0,3);
        System.out.println(Arrays.toString(nums2));
    }
}

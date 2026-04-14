package advanced.java.jvm.vms;

import java.lang.reflect.Constructor;

class Father {
    public final void showFinal() {
        System.out.println("father final");
    }
}
public class TestVirtual extends Father {

    private static int num = 10;
    public static void main(String[] args) {
        TestVirtual testVirtual = new TestVirtual();
        try {
            testVirtual.methodCallingAll();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }


    public void methodCallingAll () throws InterruptedException {
        showFinal();
        int num1 = this.num;
        Thread.sleep(1000000);
        super.showFinal();
    }
}

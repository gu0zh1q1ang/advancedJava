package advanced.java.threads.communication;


class Utils {
    public static void printThreadName(String suffix) {
        System.out.println(Thread.currentThread().getName() + " " + suffix);
    }
}

public class TestThreads {

    // 1. 共享变量
    /*private static volatile boolean flag = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Utils.printThreadName("started");
            Utils.printThreadName("ended");
        }, "t1");

        Thread t2 = new Thread(() -> {
            Utils.printThreadName("started");
            try {
                t1.start();
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Utils.printThreadName("ended");
        }, "t2");
        t2.start(); // start放进就绪队列，等待CPU调度

        System.out.println("main thread ended");
    }*/

    // 2. 互斥锁
    // synchronized 互斥悲观锁，不公平锁（不是等待最长的优先），自动释放锁，可重入锁
    // 2.1 修饰实例方法，锁的是当前实例对象，this
    // 修饰代码块，自定义任意对象
    // 修饰静态方法，锁的是当前类Class对象
    private int cnt = 10;

    public void printN() {
        synchronized(this) {
            if (cnt > 0) {
                cnt--;
                System.out.println(Thread.currentThread().getName() + " decreased cnt by 1: " + cnt);
            } else {
                System.out.println(Thread.currentThread().getName() + " failed to decrease cnt."+ cnt);
            }
        }
    }

    public static void main(String[] args) {
        TestThreads testThreads = new TestThreads();
        for (int i = 0; i < 20; i++) {
            new Thread(testThreads::printN, "t"+i).start();
        }
    }

}

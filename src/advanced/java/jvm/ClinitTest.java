package advanced.java.jvm;

public class ClinitTest {


    public static void main(String[] args) {
        Runnable loader = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "started");
                ComingClass cc = new ComingClass();
                System.out.println(Thread.currentThread().getName() + "successfully get ComingClass");
            }
        };

        Thread a = new Thread(loader, "first");
        Thread b = new Thread(loader, "second");

        a.start();
        b.start();
    }

}


class ComingClass {
    static {
        if(true) {
            System.out.println(Thread.currentThread().getName()+"executed clinit");
        }
    }
}

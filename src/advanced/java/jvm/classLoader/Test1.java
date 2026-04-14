package advanced.java.jvm.classLoader;

import java.lang.ref.PhantomReference;
import java.util.concurrent.CopyOnWriteArrayList;

interface Huntable {
   void hunt();
}

class Cat implements Huntable {
    @Override
    public void hunt() {
        System.out.println("猫");
    }
}
public class Test1 {
    private static int num = 10;
    public void eat (Huntable a) {
        a.hunt();
    }
    public static void main(String[] args) throws InterruptedException {
//        Test2.main(new String[]{"2", "4"});
        int a = 10;
        int b = a++ + ++a;
        System.out.println(b);
        Test1 test1 = new Test1();
        System.out.println(test1.num);


    }

    public static int ask () {
        int i = 0;
        int j = 2;
        return i+j;
    }
}

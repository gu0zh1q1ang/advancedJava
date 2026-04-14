package advanced.java.jvm.stringTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StringTest {

    public static void main(String[] args) {
        String ab = "ab";
        String c = new String("a")+new String("b");

        String e = c.intern();
        String d = ab.intern();

        System.out.println(d==e);

        System.out.println(c==e);
        System.out.println(ab==d);
        System.out.println(c==d);

        System.out.println(System.getenv("JAVA_HOME"));
        System.out.println(System.getProperty("java.version"));
    }
}

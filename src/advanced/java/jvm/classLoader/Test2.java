package advanced.java.jvm.classLoader;

public class Test2 {
    static {
        num = 10;
        System.out.println("Test2 loaded");
    };
    public static int num = 20;
    public static void main(String[] args) {
        System.out.println(1);
    }
}

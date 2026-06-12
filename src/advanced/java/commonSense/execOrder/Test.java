package advanced.java.commonSense.execOrder;

class Jinx {
    {
        System.out.println("before constructor");
    }
    public Jinx() {
        System.out.println("jinx constructor");
    }
}

class Father {
    public Father(String name) {

    }
}
public class Test extends Father {

    static {
        System.out.println("static");
    }

    Jinx jinx = new Jinx();

    public Test(String name) {
        super(name);
        System.out.println("constructor itself");
    }

    public static void main(String[] args) {
        new Test("asdf");
    }
}

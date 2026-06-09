package gzq.byd.com.ItfAndAbs;

public class Bird {
    private String name;

    {
        System.out.println("Bird non-static block");
    }

    static {
        System.out.println("Bird static");
    }

    public Bird(String name) {
        System.out.println("Bird constructor with args");
    }

    public Bird() {
        System.out.println("Bird constructor without args");
    }

    protected void heiha() {

    }
}

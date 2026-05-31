package gzq.byd.com.ItfAndAbs;

public class Sparrow extends Bird{
    {
        System.out.println("Sparrow non-static block");
    }

    static {
        System.out.println("Sparrow static");
    }

    public Sparrow(String name) {
        super(name);
        System.out.println();
    }

    public Sparrow(String anotherName, Integer age) {
        this.heiha();
    }
}

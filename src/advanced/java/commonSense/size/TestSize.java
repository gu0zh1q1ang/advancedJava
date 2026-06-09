package advanced.java.commonSense.size;

import java.lang.instrument.Instrumentation;

public class TestSize {
    public static void main(String[] args) {
        // byte 8bit 1B
        System.out.println(Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);

        // short 16bit 2B
        System.out.println(Short.MIN_VALUE + "~" + Short.MAX_VALUE);

        // int 32bit 4B
        System.out.println(Integer.MIN_VALUE + "~" + Integer.MAX_VALUE);

        // long 64bit 8B
        System.out.println(Long.MIN_VALUE + "~" + Long.MAX_VALUE);

        // float 32bit 4B
        System.out.println(Float.MIN_VALUE + "~" + Float.MAX_VALUE);

        // double 64bit 8B
        System.out.println(Double.MIN_VALUE + "~" + Double.MAX_VALUE);

        // char
        System.out.println(Character.MIN_VALUE + "~" + Character.MAX_VALUE);
    }
}

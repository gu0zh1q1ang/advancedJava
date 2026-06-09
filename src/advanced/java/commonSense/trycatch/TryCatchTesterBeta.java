package advanced.java.commonSense.trycatch;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class TryCatchTesterBeta {
//    private static Logger logger = LoggerFactory.getLogger(TryCatchTesterBeta.class);
    private static Logger logger = Logger.getLogger(TryCatchTesterBeta.class);
    public static void main(String[] args) {
//        System.out.println(test());
//        logger.error(test());
        logger.error(Arrays.toString(testQuote()));
//        int b = 5;
//        changint(b);
//        System.out.println(b);
//        System.out.println(Arrays.toString(testQuote()));
    }

    public static void changint(int a){
        a = 3;
    }

    public static String test() {
        String a = "init";

        try {
//            System.out.println(a);
            logger.warn(a);
            a=a+" append body";
            return a;
        } catch (Exception e) {
            a=a+" append catch";
            return a;
        } finally {
            a=a.replace("a","b");
            logger.error("finally "+a);
        }
    }

    public static int[] testQuote(){
        int[] b = new int[]{0};
        try {
//            System.out.println(Arrays.toString(b));
            logger.error(Arrays.toString(b));
            int c = 3/0;
            return b;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            b[0]=4;
            return b;
        } finally {
//            b[0]=3;
            logger.error(Arrays.toString(b));
        }
    }
}

package advanced.java.commonSense.size;

import java.net.ServerSocket;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class TestStatic {
    public static void test() {
        System.out.println("static");
    }
}

class TestChild extends TestStatic {
    public TestChild(int age) {
        this.age = age;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
public class SizeWithByteCode {
    static int i;
    public static void main(String[] args) {
        System.out.println(i);
        TestStatic.test();
        TestChild.test();
        TestStatic aaa = new TestChild(12);
        aaa.test();
        Function<String,Integer> com = (a)-> a.indexOf("gu0zh1q1ang");
        Comparator<Integer> comparator = Comparator.comparingInt(c -> c);
//        comparator.comparing()
        int result = Comparator.comparing(com, comparator).compare("cgu0zh1q1ang", "agu0zh1q1ang");
        System.out.println(result);
        Function<Map<String, Integer>, String> function = (a) -> String.valueOf(a.values().stream().min(Comparator.comparingInt(c -> c)).orElse(result));
        TestChild testChild = new TestChild(11);
        TestChild testChild2 = new TestChild(12);
        int compare = Comparator.comparing(TestChild::getAge).compare(testChild, testChild2);
        System.out.println(compare);
        Map<String, Integer> map = new HashMap<>(5);
        String b = "bbb";
        Supplier<String> supplier = b::toUpperCase;
        String s = supplier.get();
        System.out.println(s);


        Function<TestChild,String> testChildStringFunction = (a)->
                Optional.ofNullable(a)
                        .map(c -> c.getAge() + "1234")
                        .orElse("unknown");

        System.out.println(testChildStringFunction.apply(new TestChild(199)));
        LocalDateTime birthday = LocalDateTime.of(1998, 4, 28, 5, 38, 0);

    }
}

interface Beepable {
    void Beep(Function<Object,String> function);
}

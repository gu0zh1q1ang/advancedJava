/*
 * author guo
 * year 2023
 */
package advanced.java.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TestGeneric<T>{
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(3);
        strings.add("hello");
        strings.add("\n");
        strings.add("world");
        // ArrayList 是fail-fast的，通过remove和add进行操作时

        // 增强for循环, ConcurrentModificationException
        for (String string : strings) {
            if (string.equals("world")) {
                strings.add("added");
            }
        }

        // Iterator循环
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            String temp = iterator.next();
//            if (temp.equals("world")) {
////                strings.remove(temp); // ConcurrentModificationException
//                iterator.remove(); // works
//            }
//        }

//        for (int i = 0; i < strings.size(); i++) {
//            if(strings.get(i).matches("(?i)hello")) {
//                strings.remove(strings.get(i));
//            }
//        }

        System.out.println(strings.stream().collect(Collectors.joining()));
    }

}

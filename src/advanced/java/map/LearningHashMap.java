package advanced.java.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LearningHashMap {

    private static final Logger log = LoggerFactory.getLogger(LearningHashMap.class);
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String,Integer> map = new HashMap<>();
        map.put("string",1);
        map.put("number",2);
        map.put("boolean",3);
        map.put("boolean1",3);
        map.put("boolean2",3);
        map.put("boolean3",3);
        map.put("boolean4",3);
        map.put("boolean5",3);
        map.put("boolean6",3);
        map.put("boolean7",3);
        map.put("boolean8",3);
        map.put("boolean9",3);
        Integer boolean9 = map.put("boolean9", 4);
        log.info("{}",boolean9);
        /* 不保证顺序
         number:2
         boolean:3
         string:1
        */
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
           log.info("{}",stringIntegerEntry.getKey()+":"+stringIntegerEntry.getValue());
        }

        System.out.println(map.size());

        Method capacity = HashMap.class.getDeclaredMethod("capacity");
        capacity.setAccessible(true);

        Object invoke = capacity.invoke(map, null);
        System.out.println(invoke);

        log.info("{}",1<<2);

    }
}

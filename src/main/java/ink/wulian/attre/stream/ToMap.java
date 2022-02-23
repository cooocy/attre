package ink.wulian.attre.stream;

import java.util.*;
import java.util.stream.Collectors;

public class ToMap {

    public static void main(String[] args) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        map.put("A", Map.of("0", "x", "1", "y"));
        map.put("B", Map.of("2", "z"));
        Set<Object> collect = map.values().stream().flatMap(k -> k.values().stream()).collect(Collectors.toSet());
        System.out.println(collect);
        System.out.println(collect.contains("x"));
        System.out.println(collect.contains("y"));
    }

}

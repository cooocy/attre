package ink.wulian.attre.map;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {
        TreeMap<String, Boolean> treeMap = new TreeMap<>();
        treeMap.put("B1", true);
        treeMap.put("B0", true);
        treeMap.put("A1", true);
        treeMap.put("A0", true);
        System.out.println(treeMap);

        assert "A0".equals(treeMap.firstKey());
        assert "B1".equals(treeMap.lastKey());

        // 获取 >= 指定 key 的 entry.
        Map.Entry<String, Boolean> a0 = treeMap.ceilingEntry("A0");
        assert Objects.nonNull(a0) && a0.getKey().equals("A0");

        // 获取 <= 指定 key 的 key.
        String b1 = treeMap.floorKey("B1");
        assert Objects.nonNull(b1) && b1.equals("B1");
    }

}

package ink.wulian.attre.stream;

import java.util.ArrayList;
import java.util.List;

public class Parallel {

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(i);
        }
        List<Integer> parallel = new ArrayList<>();
        ints.stream().parallel().filter(i -> i % 2 == 1).forEachOrdered(parallel::add);
        // .forEach(parallel::add);
        System.out.println(parallel.size());
        List<Integer> collect = ints.stream().parallel().filter(i -> i % 2 == 1).toList();
        System.out.println(collect.size());
    }

}

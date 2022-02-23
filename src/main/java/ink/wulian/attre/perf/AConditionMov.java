package ink.wulian.attre.perf;

import java.util.Random;

/**
 * 对比 "使用控制的条件转移" 和 "使用数据的条件转移"
 */
public class AConditionMov {

    public static void main(String[] args) {
        int rounds = 1000000;
        compareA(rounds);
        compareB(rounds);
    }

    public static void compareA(int rounds) {
        long begin = System.currentTimeMillis();
        int round = 0;
        Random random = new Random();
        int a;
        int b;
        int min = 0;
        while (round < rounds) {
            a = random.nextInt();
            b = random.nextInt();
            min = a;
            if (b < a) {
                min = b;
            }
            round++;
        }
        System.out.println("compareA : rounds(" + rounds + ") : " + (System.currentTimeMillis() - begin));
        System.out.println(min);
    }

    public static void compareB(int rounds) {
        long begin = System.currentTimeMillis();
        int round = 0;
        Random random = new Random();
        int a;
        int b;
        int min = 0;
        while (round < rounds) {
            a = random.nextInt();
            b = random.nextInt();
            min = a < b ? a : b;
            round++;
        }
        System.out.println("compareB : rounds(" + rounds + ") : " + (System.currentTimeMillis() - begin));
        System.out.println(min);
    }

}

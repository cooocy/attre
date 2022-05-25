package ink.wulian.attre.guava;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class StopWatchDemo {

    public static void main(String[] args) throws Exception {
        demo1();
    }

    public static void demo1() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createUnstarted();

        stopwatch.start();
        Thread.sleep(10);
        stopwatch.stop();
        System.out.println("tag1: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        stopwatch.start();
        Thread.sleep(50);
        stopwatch.stop();
        System.out.println("tag2: " + stopwatch);

        stopwatch.start();
        Thread.sleep(200);
        stopwatch.stop();
        System.out.println("tag3: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        System.out.println("end: " + stopwatch);
    }

}

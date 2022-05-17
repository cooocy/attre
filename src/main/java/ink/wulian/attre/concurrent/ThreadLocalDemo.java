package ink.wulian.attre.concurrent;

/**
 * ThreadLocal 的使用方法
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        // 同一个线程的 name、age 值, 存在同一个 ThreadLocalMap 对象中. 即: 每个线程只有一个 ThreadLocalMap 对象.
        // threadLocalMap 中存放了若干个 Entry. entry.referent: threadLocal 对象; entry.value: name、age 等实际数据.
        // Entry extends WeakReference. 即: entry.referent 是对 threadLocal 对象的弱引用.
        ThreadLocal<Integer> ages = new ThreadLocal<>();
        ThreadLocal<String> names = new ThreadLocal<>();

        ages.set(-100);
        names.set("main");

        Thread t1 = new Thread(() -> {
            ages.set(100);
            names.set("t1");
            System.out.println("[t1] " + ages.get());
            System.out.println("[t1] " + names.get());
            ages.remove();
            names.remove();
        });
        t1.start();

        Thread.sleep(1000);

        System.out.println("[main] " + ages.get());
        System.out.println("[main] " + names.get());
        ages.remove();
        names.remove();
    }

}

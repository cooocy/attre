package ink.wulian.attre.lang;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * 强引用和弱引用的区别.
 */
public class ReferenceDemo {

    private static int i10Mb = 10 * 1024 * 1024;

    public static void main2(String[] args) throws InterruptedException {
        byte[] b1 = new byte[i10Mb];
        byte[] b2 = new byte[i10Mb];
        byte[] b3 = new byte[i10Mb];

        HashMap<byte[], Object> map = new HashMap<>();
        map.put(b1, "");
        map.put(b2, "");
        map.put(b3, "");

        b1 = null;
        b2 = null;
        b3 = null;

        while (true) {
            // map 里存放的是强引用, byte 对象不能被回收.
            System.gc();
            Thread.sleep(2000);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        byte[] b1 = new byte[i10Mb];
        byte[] b2 = new byte[i10Mb];
        byte[] b3 = new byte[i10Mb];

        HashMap<WeakReference<byte[]>, Object> map = new HashMap<>();
        map.put(new WeakReference<>(b1), "");
        map.put(new WeakReference<>(b2), "");
        map.put(new WeakReference<>(b3), "");

        b1 = null;
        b2 = null;
        b3 = null;

        while (true) {
            // map 里存放的是弱引用, byte 对象可以被回收.
            System.gc();
            Thread.sleep(2000);
        }

    }

}

package ink.wulian.attre.concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedRefDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        // initialRef = o1, initialStamp = 1
        AtomicStampedReference<Object> ref = new AtomicStampedReference<>(o1, 1);

        // bool0 = true
        // expectedRef = o1, newRef = o2, expectedStamp = 1, newStamp = 2
        assert ref.compareAndSet(o1, o2, 1, 2);

        // bool1 = false
        assert !ref.compareAndSet(o1, o2, 1, 2);

    }

}

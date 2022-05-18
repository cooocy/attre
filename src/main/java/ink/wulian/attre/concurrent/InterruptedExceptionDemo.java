package ink.wulian.attre.concurrent;

public class InterruptedExceptionDemo {

    /**
     * main 线程执行到 t1.interrupt() 后, 't1: isInterrupted.' 会一致输出.
     * 也即 isInterrupted() 注释里说的, 此方法只对 interrupted 标记做读操作.
     */
    public static void main2(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("t1: isInterrupted.");
                }
            }
        });
        t1.start();
        t1.interrupt();
    }

    /**
     * # 输出
     * Thread-0: a, isInterrupted:false
     * main: x, isInterrupted:true
     * Thread-0: b, isInterrupted:false
     * *
     * # 结论
     * isInterrupted 默认 false.
     * interrupt() 后, isInterrupted 为 true.
     * catch InterruptedException 后, isInterrupted 回到 false.
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Thread currentThread = Thread.currentThread();

            // time 1
            System.out.println(currentThread.getName() + ": a, isInterrupted:" + currentThread.isInterrupted());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }


            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                e.printStackTrace();

                // time 3
                System.out.println(currentThread.getName() + ": b, isInterrupted:" + currentThread.isInterrupted());
            }
        });
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        t1.interrupt();

        // time 2
        System.out.println(Thread.currentThread().getName() + ": x, isInterrupted:" + t1.isInterrupted());
    }

}

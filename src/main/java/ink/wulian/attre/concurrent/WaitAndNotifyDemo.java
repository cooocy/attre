package ink.wulian.attre.concurrent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 演示 wait() 和 notifyAll().
 * *
 * # 输出
 * Thread-0: addTask: task-3
 * Thread-3: getTask: task-3
 * Thread-0: addTask: task-4
 * Thread-0: addTask: task-5
 * Thread-1: getTask: task-4
 * Thread-0: addTask: task-6
 * Thread-4: getTask: task-5
 * Thread-0: addTask: task-7
 * *
 * # 结论
 * - 锁池
 * *
 * * 假设线程 A 已经拥有了某个对象（注意：不是类）的锁，而其它线程想要调用此对象的某个 synchronized 方法（or synchronized 块）；由于这些线程在进入对象的synchronized 方法之前必须先获得该对象的锁，但是该对象的锁目前正被线程 A 拥有，所以这些线程就进入了该对象的锁池中。
 * *
 * - 等待池
 * *
 * * 假设线程 A 调用了某个对象的 wait() 方法，线程 A 就会释放该对象的锁，进入该对象的等待池中。
 * *
 * * 等待池中的线程不会去竞争该对象的锁。
 * *
 * - notify()
 * * 被唤醒的的线程会进入该对象的锁池，锁池中的线程会去竞争该对象的锁。
 * * notify() 后只有一个线程会由等待池进入锁池；而 notifyAll() 会将等待池内的所有线程移动到锁池。
 */
public class WaitAndNotifyDemo {

    public static void main(String[] args) {
        SimpleBlockingQueue<String> taskQueue = new SimpleBlockingQueue<>();

        new Thread(() -> {
            int cnt = 0;
            while (true) {
                String task = "task-" + cnt;
                taskQueue.addTask(task);
                cnt++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Runnable runnable = () -> {
            while (true) {
                try {
                    taskQueue.getTask();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class SimpleBlockingQueue<T> {

        private final Queue<T> tasks;

        public SimpleBlockingQueue() {
            this.tasks = new LinkedList<>();
        }

        public synchronized T getTask() throws InterruptedException {
            while (tasks.isEmpty()) {
                wait();
            }
            T task = tasks.remove();
            System.out.println(Thread.currentThread().getName() + ": getTask: " + task);
            return task;
        }

        public synchronized void addTask(T task) {
            tasks.add(task);
            System.out.println(Thread.currentThread().getName() + ": addTask: " + task);
            notifyAll();
        }

    }

}

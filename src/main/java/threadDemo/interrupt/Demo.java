package threadDemo.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName
 * @Description 只是将指定线程的状态调整为了中断类型
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class TestThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TestThreadWithSync implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 20; i < 30; i++) {
                    System.out.print(i + " ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class TestThreadWithLock implements Runnable {

        ReentrantLock reentrantLock = new ReentrantLock();

        @Override
        public void run() {
            reentrantLock.lock();
            try {
                for (int i = 30; i < 40; i++) {
                    // 通过调用 isInterrupted方法去检查本线程的中断标志位，如果线程被设置了中断标志，就【自行】停止线程
                    while (!Thread.currentThread().isInterrupted()){
                        TimeUnit.SECONDS.sleep(2);
                        System.out.print(i + " ");
                    }
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("end");
                reentrantLock.unlock();
            }
        }
    }


    static class ForEverThread implements Runnable {

        @Override
        public void run() {
            System.out.println("开始执行");
            while (true) {

            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        // Thread testThread = new Thread(new TestThread());
        // testThread.start();

        // Thread testThreadWithSync = new Thread(new TestThreadWithSync());
        // testThreadWithSync.start();

        Thread testThreadWithLock = new Thread(new TestThreadWithLock());
        testThreadWithLock.start();

        Thread forEverThread = new Thread(new ForEverThread());
        // forEverThread.start();

        Thread.sleep(2000);

        // forEverThread.interrupt();
        // testThread.interrupt();
        // testThreadWithSync.interrupt();

        // 被打断的线程在抛出异常之后依旧会正常执行任务，只能告知线程，目前需要进入中断状态，然后修改线程的状态为停止状态，
        // 但是接下来的处理流程得由线程自己去决定。
        testThreadWithLock.interrupt();

    }
}

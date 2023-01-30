package threadDemo.extendThread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class Thread1 extends Thread {
        public Thread1() {
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 500; i++) {
                System.out.println(this.isInterrupted());
                if (!this.isInterrupted()){
                    System.out.println(i);
                }else {
                    return;
                }
            }
        }
    }

    static class Thread2 extends Thread {
        Thread1 thread1;

        public Thread2(Thread1 thread1) {
            this.thread1 = thread1;
            start();
        }

        @Override
        public void run() {

            try {
                thread1.join();
            } catch (InterruptedException e) {
                System.out.println("被打断了");
            }
            System.out.println("asdasd");
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        new Thread2(thread1);
        thread1.interrupt();

    }
}

package threadDemo.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class Thread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread1 执行sleep前");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread1 执行sleep后");
        }
    }

    static class Thread2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread2 执行sleep前");

            try {
                TimeUnit.MILLISECONDS.sleep(1);
                TimeUnit.SECONDS.sleep(1);
                TimeUnit.DAYS.sleep(1);
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Thread.yield();
            System.out.println("Thread2 执行sleep后");
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Thread1());
        executorService.execute(new Thread2());
        executorService.shutdown();
    }
}

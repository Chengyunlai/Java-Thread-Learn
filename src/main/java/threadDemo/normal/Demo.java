package threadDemo.normal;

import threadDemo.utils.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description 线程的常规用法
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class Thread1 implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            System.out.println("抢票"); // 80% 核心
            Thread.yield();
            // 日志
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Thread1());
        thread.start();
        System.out.println("主函数");
    }
}

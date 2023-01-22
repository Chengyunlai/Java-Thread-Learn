package threadDemo.factory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo implements ThreadFactory {

    private  int maxThread = 0;
    private  String threadGroupName = null;
    private  String threadNamePrefix = null;
    private  ThreadGroup threadGroup;

    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicInteger threadSeq = new AtomicInteger(0);

    public Demo(int maxThread, String threadGroupName, String threadNamePrefix) {
        this.maxThread = maxThread;
        this.threadNamePrefix = threadNamePrefix;
        this.threadGroupName = threadGroupName;
        this.threadGroup = new ThreadGroup(threadGroupName);
    }

    @Override
    public Thread newThread(Runnable r) {
        // int c = count.incrementAndGet();
        // if (c > maxThread) {
        //     return null;
        // }
        Thread t = new Thread(threadGroup, r, threadNamePrefix + threadSeq.getAndIncrement());
        // Thread.setDaemon(false)设置为用户线程；通过Thread.setDaemon(true)设置为守护线程
        t.setDaemon(false);
        //默认线程优先级
        t.setPriority(Thread.NORM_PRIORITY);

        return t;
    }

    public static Thread getThread(Demo threadFactory){
       Thread t =  threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is task");
                System.out.println(Thread.currentThread().getName());
                try {
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
       return t;
    }
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws Exception {

        Demo threadFactory = new Demo(2, "test-thread-group", "test-thread-");

        Thread t1 = getThread(threadFactory);
        Thread t2 = getThread(threadFactory);
        Thread t3 = getThread(threadFactory);
        t1.start();
        t2.start();
        t3.start();

        countDownLatch.await();
    }
}

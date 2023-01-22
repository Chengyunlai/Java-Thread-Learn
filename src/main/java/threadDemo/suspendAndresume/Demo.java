package threadDemo.suspendAndresume;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description 【不推荐使用】suspend虽然可以暂停线程，但是咱少用：开发者声明了废弃标志。这是因为当线程调用了suspend操作之后，
 * 线程虽然暂停了，但是如果该线程曾经持有过锁并且也未曾主动释放过锁的话，
 * 那么这个处于暂停状态的线程就会一直持有锁，从而可能会导致其他希望获取锁的线程一直处于等待状态。
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class SuspendThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.print(i+" ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("线程暂停3秒");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("暂停结束");
        Thread suspendThread = new Thread(new SuspendThread());
        suspendThread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.print("线程暂停");
        //暂停线程
        suspendThread.suspend();
        TimeUnit.SECONDS.sleep(2);
        //从之前暂停的位置恢复继续执行
        suspendThread.resume();
        System.out.print(" 线程恢复");
    }
}

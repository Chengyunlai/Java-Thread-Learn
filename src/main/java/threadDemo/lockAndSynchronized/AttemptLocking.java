package threadDemo.lockAndSynchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock():" + captured);
        }finally {
            if (captured){
                lock.unlock();
            }
        }
    }
    public  void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2,TimeUnit.SECONDS); // 两秒去锁一次
            while (!captured){
                captured = lock.tryLock(2,TimeUnit.SECONDS); // 反复去锁
                System.out.println("正在尝试");
            }
            System.out.println("拿到锁");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        // al.untimed();
        // al.timed();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                al.lock.lock(); //忘记解锁
                System.out.println("acquired");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    al.lock.unlock();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.yield();
        al.untimed();
        al.timed();
    }
}

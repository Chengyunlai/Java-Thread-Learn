package threadDemo.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ReentrantLockDemo {
    private ReentrantLock reentrantLock = new ReentrantLock();

    private Integer i = 0;
    private Integer failure = 0;

    /**
     * @Description: 测试 非阻塞方式获取锁，这个方式如果没有抢到锁则直接失败，我们修改一下方法
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2023/1/23
     */
    public void tryLockMethod(){
        // 调用的是tryLock方法
        if (reentrantLock.tryLock()){
            try {
                i++;
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }else {

            failure++;
            System.out.println("没有抢到锁: "+failure);
        }
    }

    /**
     * @Description: 测试 锁超时机制（如果没有抢到锁可以等待一会再去抢，可以手动控制这个等待时间）
     * @Param: []
     * @return: void
     * @Author: chengyunlai
     * @Date: 2023/1/23
     */
    public void tryLockMethod2() throws InterruptedException {
        // 调用的是tryLock方法
        if (reentrantLock.tryLock(5, TimeUnit.SECONDS)){
            try {
                i++;
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // 要注意，由于是调用了 tryLock 函数，所以程序不一定会获取锁，
                // 因此在 finally 模块中，如果不确定当前线程依然是持有锁的状态，那么就需要在释放前先调用 isLocked 函数进行判断。
                reentrantLock.unlock();
            }
        }else {

            failure++;
            System.out.println("没有抢到锁: "+failure);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0;i<100;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reentrantLockDemo.tryLockMethod2();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
    }
}

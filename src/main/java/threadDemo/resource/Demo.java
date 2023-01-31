package threadDemo.resource;

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
public  class Demo {
    private  int num = 5;

    public synchronized int sub() {
        --num;
        return num;
    }

    static  class Thread1 extends Thread{
        private Demo demo;

        public Thread1(Demo demo) {
            this.demo = demo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int res = demo.sub();
                if (res >= 0) {
                    System.out.println(currentThread().getName() + "执行了操作-1 还有：" + res);
                } else {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Thread1 (demo));
        }
        executorService.shutdown();
    }
}

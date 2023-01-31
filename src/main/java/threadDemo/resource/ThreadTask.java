package threadDemo.resource;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class ThreadTask extends Thread{
    private Demo demo;

    public ThreadTask(Demo demo) {
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

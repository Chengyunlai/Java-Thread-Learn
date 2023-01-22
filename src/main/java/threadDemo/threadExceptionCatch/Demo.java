package threadDemo.threadExceptionCatch;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is test");
                int i = 10/0;
            }
        });

        // 当线程出现异常的时候，会回调到 UncaughtExceptionHandler中
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            //这里是对Throwable对象进行监控，所以无论是error或者exception都能识别到
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("thread is "+t.getName());
                e.printStackTrace();
            }
        });
        thread.start();
    }
}

package threadDemo.daemon;

/**
 * @ClassName
 * @Description 守护线程是服务用户线程的
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        //  ShutdownHook的钩子函数，用于监听当前JVM是否退出
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("jvm exit success!! ")));

        // testThread是非守护线程
        Thread testThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    System.out.println("thread still running ....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 手动设置为守护线程
        testThread.setDaemon(true);
        testThread.start();
        Thread.sleep(3000);
    }
}
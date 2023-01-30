package threadDemo.exception;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(1/0);
            }catch (Exception e){
                throw e;
            }
        }
    }
    public static void main(String[] args) {
        // Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandleUtils());
            Thread1 thread1 = new Thread1();
            thread1.setUncaughtExceptionHandler(new UncaughtExceptionHandleUtils());
            thread1.start();


    }
}

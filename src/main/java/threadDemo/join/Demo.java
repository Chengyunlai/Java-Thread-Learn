package threadDemo.join;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class Thread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("任务1");
            System.out.println("任务1");
            System.out.println("任务1");
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("任务2");
            System.out.println("任务2");
            System.out.println("任务2");
        }
    }
    public static void main(String[] args) {

    }
}

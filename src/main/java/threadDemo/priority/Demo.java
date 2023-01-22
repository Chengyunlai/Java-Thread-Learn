package threadDemo.priority;

/**
 * @ClassName
 * @Description 线程优先级
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class InnerTask implements Runnable {

        private int i;

        public InnerTask(int i) {
            this.i = i;
        }

        public void run() {
            for(int j=0;j<i;j++){
                System.out.println("ThreadName is " + Thread.currentThread().getName()+" "+j);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InnerTask(10),"task-1");
        t1.setPriority(1);
        Thread t2 = new Thread(new InnerTask(2),"task-2");
        //优先级只能作为一个参考数值，而且具体的线程优先级还和操作系统有关
        t2.setPriority(2);
        Thread t3 = new Thread(new InnerTask(3),"task-3");
        t3.setPriority(3);

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(2000);
    }
}

package threadDemo.normal;

/**
 * @ClassName
 * @Description 线程的常规用法
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {

    /**
     * Thread t = new Thread(new Runnable(){....})
     * t.start();
     */

    /**
     * @Description:Thread实现了Runnable接口，Runnable是一个函数式接口，内部定义了run方法。构造一个Thread对象之后，触发它的start方法，线程内部的run逻辑便会触发执行。
     * @Param: * @Param null:
     * @return: * @return: null
     * @Author: chengyunlai
     * @Date: 2023/1/22
     */
    Thread thread = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("i am running ....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });



    public static void main(String[] args) {
        Demo normalThread01 = new Demo();
        normalThread01.thread.start();
    }
}

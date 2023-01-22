package threadDemo.group;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Description 线程组的使用。使用背景：一个进程中通常会运行着各种各样的线程，面对各种线程的管理，提出了线程分组的设计，在Java中使用ThreadGroup设计。
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    // 负责HTTP连接的线程
    public static List<Thread> genHttpConThread(){
        // 通过ThreadGroup声明的内容就是一个线程组
        ThreadGroup httpThreadGroup = new ThreadGroup("http请求线程组");

        List<Thread> httpThreadGroupList = new ArrayList<>();

        for (int i = 0;i < 2;i++){
            Thread t = new Thread(httpThreadGroup,()->{
                System.out.println("线程名：" + Thread.currentThread().getName()+",所在的线程组：" + Thread.currentThread().getThreadGroup().getName());
            },"http-req-thread-" + i);
            httpThreadGroupList.add(t);
        }

        return httpThreadGroupList;
    }
    // 负责数据库操作的线程
    public static List<Thread> genDbConThread(){
        // 通过ThreadGroup声明的内容就是一个线程组
        ThreadGroup dbThreadGroup = new ThreadGroup("db请求线程组");

        List<Thread> dbThreadGroupList = new ArrayList<>();

        for (int i = 0;i < 2;i++){
            Thread t = new Thread(dbThreadGroup,()->{
                System.out.println("线程名：" + Thread.currentThread().getName()+",所在的线程组：" + Thread.currentThread().getThreadGroup().getName());
            },"db-req-thread-" + i);
            dbThreadGroupList.add(t);
        }

        return dbThreadGroupList;
    }

    public static void startThread(List<Thread> threadList) {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        List<Thread> dbConnThreadList = genDbConThread();
        List<Thread> httpReqThreadList = genHttpConThread();

        startThread(dbConnThreadList);
        startThread(httpReqThreadList);
    }
}

package threadDemo.callable;

import java.util.concurrent.*;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    static class CallableDemo implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("hi");
            return "芜湖起飞";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> result = exec.submit(new CallableDemo());
        boolean done = result.isDone();
        String s = result.get();
        System.out.println(s);
    }
}

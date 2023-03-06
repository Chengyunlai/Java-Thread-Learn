package threadDemo.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class AtomicIntegerTest implements Runnable{
    private AtomicInteger i = new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }

    public void evenIncrement(){
        i.addAndGet(2);
    }
    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("时间到");
                System.exit(0);
            }
        },5000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        exec.execute(atomicIntegerTest);
        while (true){
            int value = atomicIntegerTest.getValue();
            if(value % 2 != 0){
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}

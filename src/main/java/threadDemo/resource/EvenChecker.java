package threadDemo.resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class EvenChecker implements Runnable{
    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int val = generator.next();
            if (val % 2 != 0){
                System.out.println(val + " 不是偶数");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp,int count){
        System.out.println("Press Control-C to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < count;i++){
            executorService.execute(new EvenChecker(gp,i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator,10);
    }
}

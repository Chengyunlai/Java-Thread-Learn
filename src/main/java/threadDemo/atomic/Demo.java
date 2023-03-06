package threadDemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        // AtomicInteger i = new AtomicInteger(10);
        // int i2 = i.addAndGet(1);
        // System.out.println(i2);

        int num = 10;
        num++;
        System.out.println(num);
    }
}

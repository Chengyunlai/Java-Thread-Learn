package threadDemo.syn;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo {
    //车票剩余数目
    private int stockNum;


    public Demo(int stockNum) {
        this.stockNum = stockNum;
    }

    // 判断库存是否还是大于0的
    private boolean isStockEnough(){
        return stockNum>0;
    }

    /**
     * 锁定库存
     *
     * @return 是否锁定成功
     */
    public boolean lockStock(int num) {
        synchronized(this){
            if (!isStockEnough()) {
                return false;
            }
            for (int i = 0; i < num; i++) {
                stockNum--;
            }
            return true;
        }
    }

    public void printStockNum() {
        if(this.stockNum<0){
            System.out.println("库存不足：" + this.stockNum);
        }
    }

    public static void batchTest(int threadNum, int stockNum) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadNum);

        // 构造函数，产品为stockNum
        Demo stockNumSale = new Demo(stockNum);

        //启动threadNum个线程
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //等待，模拟并发
                        begin.await();
                        stockNumSale.lockStock(100);
                        end.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        try {
            begin.countDown();
            // 先让主线程等待
            end.await();
            stockNumSale.printStockNum();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            batchTest(200, 1000);
        }
    }
}

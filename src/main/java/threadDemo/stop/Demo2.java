package threadDemo.stop;

/**
 * @ClassName
 * @Description 通过转账的例子 说明stop结束线程的方式并不好
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class Demo2 {
    static class User {
        int id;
        int balance;

        public User(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }

    public static void doTransfer(User userA, User userB, int money) throws InterruptedException {
        // A账户扣money钱
        subtractUserBalance(userA,money);
        // B账户增加money钱
        addUserBalance(userB,money);
    }

    private static void subtractUserBalance(User user,int money) throws InterruptedException {
        user.balance = user.balance - money;
        //模拟一些网络损耗
        Thread.sleep(1000);
    }

    private static void addUserBalance(User user,int money) throws InterruptedException {
        user.balance = user.balance + money;
        //模拟一些网络损耗
        Thread.sleep(1000);
    }

    static class TransferThread implements Runnable {

        private User userA;
        private User userB;

        public TransferThread(User userA, User userB) {
            this.userA = userA;
            this.userB = userB;
        }

        @Override
        public void run() {
            try {
                doTransfer(userA, userB, 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showBalance(User userA, User userB) {
        System.out.println("用户A余额：" + userA.getBalance() + ",用户B余额：" + userB.getBalance());
    }

    public static void transferTest(boolean isNormal) throws InterruptedException {
        User userA = new User(1001, 100);
        User userB = new User(1001, 100);
        Thread t1 = new Thread(new TransferThread(userA, userB));
        t1.start();
        Thread.sleep(200);
        if (isNormal) {
            Thread.sleep(2000);
        } else {
            t1.stop();
        }
        showBalance(userA, userB);
    }

    public static void main(String[] args) throws InterruptedException {
        transferTest(false);
    }
}

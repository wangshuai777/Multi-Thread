package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshuai on 2018/2/23.
 */
public class LockConThreadDemo {
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private static int count = 0;

    public static void main(String[] args) {
        LockConThreadDemo demo = new LockConThreadDemo();
        demo.conLock();
    }

    private void conLock() {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 0)
                        c1.await();
                    System.out.println(String.format("第%d次打印", i + 1));
                    System.out.println("A");
                    count++;
                    c2.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 1)
                        c2.await();
                    System.out.println("B");
                    count++;
                    c3.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 2)
                        c3.await();
                    System.out.println("C");
                    count++;
                    c1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}

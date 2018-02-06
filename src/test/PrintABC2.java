package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshuai on 2018/2/6.
 */
public class PrintABC2 {

    static int state=0;
    private static Lock lock=new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService service=Executors.newCachedThreadPool();
        service.submit(new PrintABC2.ThreadA());
        service.submit(new PrintABC2.ThreadB());
        service.submit(new PrintABC2.ThreadC());

        service.shutdown();
    }

    public static class ThreadA implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;){
                lock.lock();
                if(state%3==0) {
                    System.out.println("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static class ThreadB implements  Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;){
                lock.lock();
                 if(state%3==1){
                     System.out.println("B");
                     state++;
                     i++;

                 }
                lock.unlock();
            }

        }
    }

    public static class ThreadC implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;){
               lock.lock();
                if(state%3==2){
                    System.out.println("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

}

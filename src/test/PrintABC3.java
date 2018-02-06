package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wangshuai on 2018/2/6.
 */
public class PrintABC3 {
    static int state=0;
    private static Semaphore a=new Semaphore(1);
    private static Semaphore b=new Semaphore(1);
    private static Semaphore c=new Semaphore(1);

    public static void main(String[] args) {
        ExecutorService service=Executors.newCachedThreadPool();
        try {
            b.acquire();
            c.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.submit(new ThreadA());
        service.submit(new ThreadB());
        service.submit(new ThreadC());
        service.shutdown();

    }

    static class ThreadA implements Runnable{
        @Override
        public void run() {
          for(int i=0;i<10;i++){
                  try {
                      a.acquire();
                      System.out.println("A");
                      b.release();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
        }
    }

   static class ThreadB implements Runnable{
       @Override
       public void run() {
           for(int i=0;i<10;i++){
                  try {
                      b.acquire();
                      System.out.println("B");
                      c.release();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
           }
       }
   }

    static  class ThreadC implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                    try {
                        c.acquire();
                        System.out.println("C");
                        a.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 }

        }
    }

}

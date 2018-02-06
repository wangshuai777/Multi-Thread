package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangshuai on 2018/2/6.
 */
public class PrintABC {

    static int state =0;
    private static Object object=new Object();

    public static void main(String[] args) {
        ExecutorService service=Executors.newCachedThreadPool();
        service.submit(new ThreadA());
        service.submit(new ThreadB());
        service.submit(new ThreadC());
        service.shutdown();
    }

        public static class ThreadA implements Runnable{
            @Override
            public void run() {
                  for(int i=0;i<10;i++) {
                      synchronized (object) {
                          while (state % 3 != 0) {
                              try {
                                  object.wait();
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                          }
                          System.out.println("A");
                          state++;
                          object.notifyAll();
                      }
                  }
            }
        }

       public static class ThreadB implements  Runnable{
           @Override
           public void run() {
               for(int i=0;i<10;i++){
                   synchronized (object){
                           while(state%3!=1){
                               try {
                                   object.wait();
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }
                       System.out.println("B");
                       state++;
                       object.notifyAll();
                   }
               }
           }
       }

      public static class ThreadC implements Runnable{
          @Override
          public void run() {
              for(int i=0;i<10;i++){
                    synchronized (object){
                        while (state%3!=2){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("C");
                        state++;
                        object.notifyAll();
                    }
              }

          }
      }




}

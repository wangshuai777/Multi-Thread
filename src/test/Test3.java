package test;

/**
 * Created by wangshuai on 2018/2/2.
 */
public class Test3 {
    private static int state=1;

    public static void main(String[] args) {
       final Object object=new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                  synchronized (object) {
                      if (state != 1) {
                          try {
                              object.wait();
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                      }
                      try {
                          System.out.println("轮到线程一开始执行。。。");
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      state = 2;
                      object.notifyAll();
                  }
                }
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (object){
                        if (state != 2) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            System.out.println("线程二开始执行");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        state = 1;
                        object.notifyAll();
                  }
                }
            }
        }).start();
    }
}

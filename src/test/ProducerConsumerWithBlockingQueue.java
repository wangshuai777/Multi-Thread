package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wangshuai on 2018/2/24.
 */
public class ProducerConsumerWithBlockingQueue {
    public static void main(String[] args) {
        Resource3 resource3=new Resource3();
        Thread t1=new Thread(new ProducerQ(resource3));
        Thread t2=new Thread(new ConsumeQ(resource3));
        t1.start();
        t2.start();
    }
}

class ProducerQ implements Runnable{
   private Resource3 resource3;
    public ProducerQ(Resource3 resource3){
        this.resource3=resource3;
    }
    @Override
    public void run() {
        resource3.produce();
    }
}

class ConsumeQ implements Runnable{
  private Resource3 resource3;
    public ConsumeQ(Resource3 resource3){
        this.resource3=resource3;
    }
    @Override
    public void run() {
       resource3.consume();
    }
}

class Resource3{

    private BlockingQueue queues=new LinkedBlockingQueue(10);

    //生产
    public void produce(){
        try {
            queues.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //消费
    public void consume(){
        try {
            queues.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

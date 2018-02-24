package test;

/**
 * Created by wangshuai on 2018/2/23.
 */
public class ProducerConsumerWithNotify {
    public static void main(String[] args) {
        Resource resource=new Resource();
        Thread producer=new Thread(new Producer(resource));
        Thread consumer=new Thread(new Consumer(resource));
        producer.start();
        consumer.start();
    }



}

class Resource{
    private int num=0;
    private int size=10;

    //消费的方法
    public synchronized void consume(){
        if(num<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            num--;
            notifyAll();
        }
    }
    //生产的方法
    public synchronized void produce(){
            if (num >= size) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                num++;
                notifyAll();
            }
    }
}

class Producer implements Runnable{
    private Resource resource;
    public Producer(Resource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        while(true){
            resource.produce();
        }

    }
}

class Consumer implements  Runnable{
    private Resource resource;
    public Consumer(Resource resource){
        this.resource=resource;
    }
    @Override
    public void run() {
        while(true){
           resource.consume();
        }
    }
}

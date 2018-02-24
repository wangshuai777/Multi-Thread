package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangshuai on 2018/2/23.
 */
public class ProducerConsumerWithLockCondition {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        Condition produ=lock.newCondition();
        Condition consu=lock.newCondition();
        Resource2 resource2=new Resource2(lock,produ,consu);
        Thread t1=new Thread(new Producer2(resource2));
        Thread t2=new Thread(new Consumer2(resource2));
        t1.start();
        t2.start();
    }


}

class Producer2 implements Runnable{
    private Resource2 resource2;
    public  Producer2(Resource2 resource2){
        this.resource2=resource2;
    }

    @Override
    public void run() {
       while(true){
          resource2.produce();
       }
    }
}

class Consumer2 implements Runnable{
    private Resource2 resource2;

    public Consumer2(Resource2 resource2){
      this.resource2=resource2;
    }

    @Override
    public void run() {

    }
}

class Resource2{
    private int num=0;
    private int size=10;
    private Lock lock;
    private Condition producerCon;
    private Condition consumerCon;



    public Resource2(Lock lock,Condition producerCon,Condition consumerCon){
        this.lock=lock;
        this.producerCon=producerCon;
        this.consumerCon=consumerCon;
    }

    public void produce(){
        lock.lock();
        try {
            if (num < size) {
                num++;
                consumerCon.signal();
            } else {
                try {
                    producerCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void consume(){
        lock.lock();
        try {
            if(num>0){
               num--;
                producerCon.signal();
            }else{
                try {
                    consumerCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

}

package com.able.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();
        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("wait signal");
                condition.await();
            } catch (Exception e) {
                log.error("发生错误",e);
            }
            log.info("get signal");
            reentrantLock.unlock();
        }).start();
        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("get lock");
                Thread.sleep(3000);

            } catch (Exception e) {
                log.error("发生错误",e);
            }
            condition.signalAll();
            log.info("send signal");
            reentrantLock.unlock();
        }).start();


    }
}

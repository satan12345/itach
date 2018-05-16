package com.able.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
        log.info("callback is running");
    });
    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
           // TimeUnit.SECONDS.sleep(1);
            executorService.execute(()->{
                try {
                    race(Thread.currentThread().getName());
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
    }

    private static void race(String threadName) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        log.info("{} is ready",threadName);
        cyclicBarrier.await();
        log.info("{} continue;",threadName);
    }

}

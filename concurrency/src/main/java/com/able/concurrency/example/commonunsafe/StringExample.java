package com.able.concurrency.example.commonunsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
@Slf4j
public class StringExample {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer=new StringBuffer();

    public static void main(String[] args) throws  Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
         Semaphore semaphore = new Semaphore(threadTotal);
         CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {

                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        update();

                    } catch (InterruptedException e) {
                        log.error(e.toString());
                    }
                    finally {
                        semaphore.release();
                        countDownLatch.countDown();
                    }
                });

        }
        countDownLatch.await();
        log.info("size={}",stringBuffer.length());
        executorService.shutdown();
    }


    public static void update() {
        stringBuffer.append("1");
    }
}

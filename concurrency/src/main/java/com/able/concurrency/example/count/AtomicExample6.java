package com.able.concurrency.example.count;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtomicExample6 {
    static AtomicBoolean atomicBoolean=new AtomicBoolean(false);
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            try {
                executorService.execute(() -> {

                    try {
                        semaphore.acquire();
                        test();
                    } catch (InterruptedException e) {
                        log.error(e.toString());
                    }
                });
            }finally {
                semaphore.release();
                countDownLatch.countDown();
            }

        }
        countDownLatch.await(10, TimeUnit.SECONDS);

        executorService.shutdown();
    }

    public static void test(){
        if (atomicBoolean.compareAndSet(false,true)) {
            log.info("更新成功,{}",atomicBoolean.get());
        }
    }
}

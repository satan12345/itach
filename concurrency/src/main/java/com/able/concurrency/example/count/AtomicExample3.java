package com.able.concurrency.example.count;

import com.able.concurrency.annotion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@NotThreadSafe
public class AtomicExample3 {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws  Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            try {
                executorService.execute(() -> {

                    try {
                        semaphore.acquire();
                        add();

                    } catch (InterruptedException e) {
                       log.error(e.toString());
                    }
                });
            }finally {
                semaphore.release();
                countDownLatch.countDown();
            }

        }
        countDownLatch.await(10,TimeUnit.SECONDS);
        log.info("count={}",count);
        executorService.shutdown();
    }


    public static void add() {
        count.increment();
    }
}

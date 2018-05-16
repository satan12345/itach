package com.able.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample1 {

    private final static int THREAD_COUNT = 20;

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNT; i++) {
            exec.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    test(name);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(200,TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }
    private static void test(String name) throws Exception{
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("{}",name);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}

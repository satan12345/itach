package com.able.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample2 {

    private final static int THREAD_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNT; i++) {

            exec.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    //尝试获取一个许可
                    if (semaphore.tryAcquire()) {
                        try {
                            test(name);
                        } finally {
                            semaphore.release();
                        }


                    }

                } catch (Exception e) {
                    log.error("exception", e);
                }

            });
        }


        exec.shutdown();
    }

    private static void test(String name) throws Exception {
        TimeUnit.MILLISECONDS.sleep(500);
        log.info("{}", name);
        TimeUnit.MILLISECONDS.sleep(500);
    }
}

package com.able.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SemaphoreExample1 {

    private final static int THREAD_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Semaphore semaphore=new Semaphore(2);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNT; i++) {

            exec.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    semaphore.acquire(2);
                    test(name);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    semaphore.release(2);
                }
            });
        }


        exec.shutdown();
    }
    private static void test(String name) throws Exception{
        TimeUnit.MILLISECONDS.sleep(500);
        log.info("{}",name);
        TimeUnit.MILLISECONDS.sleep(500);
    }
}

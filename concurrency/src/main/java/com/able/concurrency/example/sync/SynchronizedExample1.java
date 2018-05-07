package com.able.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                log.info("test1->{}", i);
            }
        }
    }
    //修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 100; i++) {
            log.info("test2"+j+"->{}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1=new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2=new SynchronizedExample1();
        ExecutorService executorService= Executors.newCachedThreadPool();
       // executorService.execute(()-> synchronizedExample1.test1());
        // executorService.execute(()-> synchronizedExample1.test1());
        executorService.execute(()-> synchronizedExample2.test2(1));
        executorService.execute(()-> synchronizedExample1.test2(2));
    }

}

package com.able.concurrency.example.count;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicExample4 {
    private static AtomicReference<Integer> reference = new AtomicReference<>(0);

    public static void main(String[] args) {
    		reference.compareAndSet(0,2);
    		reference.compareAndSet(0,1);
    		reference.compareAndSet(1,3);
    		reference.compareAndSet(2,4);
    		reference.compareAndSet(3,5);
    		log.info("count:{}",reference);
    }
}

package com.able.concurrency.example.count;

import com.able.concurrency.annotion.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    @lombok.Getter
    public volatile int count = 100;//volatile 且非static

    private static AtomicExample5 atomicExample5=new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(atomicExample5,100,120)) {
            log.info("update success1,{}",atomicExample5.getCount());
        }
        if (updater.compareAndSet(atomicExample5,100,120)) {
            log.info("update success2,{}",atomicExample5.getCount());
        }else {
            log.info("update failed,{}",atomicExample5.getCount());
        }
    }

}

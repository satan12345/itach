package com.able.concurrency.example.count;

import com.able.concurrency.annotion.ThreadSafe;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater=AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    @Getter
    public volatile int count=100;

}

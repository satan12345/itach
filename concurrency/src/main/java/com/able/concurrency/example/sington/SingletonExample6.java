package com.able.concurrency.example.sington;

import com.able.concurrency.annotion.ThreadSafe;


public class SingletonExample6 {
    private SingletonExample6() {
    }

    static {
        instance = new SingletonExample6();
    }

    private static SingletonExample6 instance = null;

    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance);
        System.out.println(instance);
    }
}

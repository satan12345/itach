package com.able.concurrency.example.threadlocal;

public class ThreadLocalHolder {
    private final static ThreadLocal<Long> requestHolder=new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }
    public static void remove(){
        requestHolder.remove();
    }
}

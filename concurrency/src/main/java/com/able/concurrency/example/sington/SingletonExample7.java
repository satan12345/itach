package com.able.concurrency.example.sington;

import com.able.concurrency.annotion.ThreadSafe;

@ThreadSafe
public class SingletonExample7 {

    private SingletonExample7() {

    }
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum  Singleton{
        INSTANCE;
        private SingletonExample7 singleton;
        //JVM保证这个方法只会调用一次
        Singleton(){
            singleton=new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }

}

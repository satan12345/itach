package com.able.concurrency.example.commonunsafe;

import com.able.concurrency.annotion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormateExample3 {


    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");



    public static void main(String[] args) throws  Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {

            final int count=i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);

                } catch (InterruptedException e) {
                    log.error(e.toString());
                }
                finally {
                    semaphore.release();
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();

        executorService.shutdown();
    }


    public static void update(int count) {


        log.info("{},{}",count,LocalDateTime.now().format(dateTimeFormatter));


    }
}

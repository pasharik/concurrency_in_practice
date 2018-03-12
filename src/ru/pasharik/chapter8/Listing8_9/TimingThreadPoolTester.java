package ru.pasharik.chapter8.Listing8_9;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by pasharik on 12/03/18.
 */
public class TimingThreadPoolTester {
    public static void main(String[] args) {
        //System.out.println("Pool tester");
        ExecutorService pool = new TimingThreadPool(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            pool.submit(() -> {
                try {
                    long l = r.nextInt(1000);
                    System.out.println(l);
                    Thread.sleep(l);
                } catch (InterruptedException e) { }
            });
        }
        pool.shutdown();
    }
}

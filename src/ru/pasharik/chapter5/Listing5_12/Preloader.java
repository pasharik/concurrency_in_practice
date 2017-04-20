package ru.pasharik.chapter5.Listing5_12;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by pasharik on 20/04/17.
 */
public class Preloader {
    private final FutureTask<String> future =
            new FutureTask<String>(new Callable<String>() {
                public String call() throws Exception {
                    Thread.sleep(2000);
                    return "Some string";
                }
            });
    private final Thread t = new Thread(future);

    public void start() { t.start(); }

    public String get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Preloader preloader = new Preloader();
        preloader.start();
        Thread.sleep(1500);
        System.out.println("Waiting for result...");
        System.out.println(preloader.get());
    }
}
